package com.lomovtsev.customsortfiles

import com.intellij.ide.projectView.TreeStructureProvider
import com.intellij.ide.projectView.ViewSettings
import com.intellij.ide.projectView.impl.nodes.PsiFileNode
import com.intellij.ide.util.treeView.AbstractTreeNode

class ZFirstTreeStructureProvider : TreeStructureProvider {
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

        val result = ArrayList<AbstractTreeNode<*>>()
        zFirst?.also { result.add(it) }
        children.filter { it != zFirst }
            .forEach { result.add(it) }



        return result
    }
}
