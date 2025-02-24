package com.lomovtsev.customsortfiles

import com.intellij.ide.projectView.TreeStructureProvider
import com.intellij.ide.projectView.ViewSettings
import com.intellij.ide.projectView.impl.nodes.PsiFileNode
import com.intellij.ide.util.treeView.AbstractTreeNode
import com.intellij.openapi.project.Project

class CustomSortTreeStructureProvider(
    private val project: Project
) : TreeStructureProvider {
    val comparatorV2 = CustomGroupByTypeComparator()
    override fun modify(
        parent: AbstractTreeNode<*>,
        children: MutableCollection<AbstractTreeNode<*>>,
        settings: ViewSettings?
    ): MutableCollection<AbstractTreeNode<*>> {
        val zFirst = children.find { node ->
            if (node is PsiFileNode) {
                return@find node.value.name == "zfirst.txt"
            }
            return@find false
        }

        val result = children.filter { it != zFirst }
            .sortedWith(comparatorV2)
            .toMutableList()
        
        zFirst?.also { 
            result.add(1, it) // TODO: fix it!
        }

        return result
    }

}
