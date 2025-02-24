package com.lomovtsev.customsortfiles

import ai.grazie.utils.LinkedSet
import ai.grazie.utils.toLinkedSet
import com.intellij.ide.projectView.TreeStructureProvider
import com.intellij.ide.projectView.ViewSettings
import com.intellij.ide.projectView.impl.nodes.PsiFileNode
import com.intellij.ide.util.treeView.AbstractTreeNode
import com.intellij.openapi.project.Project

class CustomSortTreeStructureProvider(
    project: Project
) : TreeStructureProvider {
    val fileOrderStorage =  FileOrderStorage.getInstance(project)
    
    val comparatorV2 = CustomGroupByTypeComparator()
    override fun modify(
        parent: AbstractTreeNode<*>,
        children: MutableCollection<AbstractTreeNode<*>>,
        settings: ViewSettings?
    ): MutableCollection<AbstractTreeNode<*>> {
        
        val customPositions = fileOrderStorage.state.customFilePositions
        
        val customs = findCustom(children, customPositions)

        val result = children
            .filter { !customs.contains(it) }
            .sortedWith(comparatorV2)
            .toMutableList()
        
        customs.forEach {
            val name = it.filename()
            val index = customPositions[name]!!
            result.add(index, it)
        }

        return result
    }
    
    private fun findCustom(
        children: Collection<AbstractTreeNode<*>>, 
        customPositions: Map<String, Int>
    ): LinkedSet<AbstractTreeNode<*>> {
        return children.filter { it is PsiFileNode && customPositions.containsKey(it.value.name) }
            .map {  it as PsiFileNode }
            .sortedBy { customPositions[it.value.name] }
            .toLinkedSet()
    }

}

private fun AbstractTreeNode<*>.filename(): String? {
    if  (this is PsiFileNode) {
        return this.value.name
    }
    return null;
}
