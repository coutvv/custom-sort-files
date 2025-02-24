package com.lomovtsev.customsortfiles

import com.intellij.ide.projectView.impl.nodes.PsiDirectoryNode
import com.intellij.ide.projectView.impl.nodes.PsiFileNode
import com.intellij.ide.util.treeView.AbstractTreeNode
import com.intellij.openapi.util.text.StringUtil

class CustomGroupByTypeComparator: Comparator<AbstractTreeNode<*>> {
    override fun compare(
        o1: AbstractTreeNode<*>?,
        o2: AbstractTreeNode<*>?
    ): Int {
        val n1 = getName(o1)
        val n2 = getName(o2)
        
        if (n1 == null && n2 == null) return 0
        if (n1 == null) return 1
        if (n2 == null) return -1
        if (o1 is PsiDirectoryNode && o2 is PsiFileNode) return -1
        if (o1 is PsiFileNode && o2 is PsiDirectoryNode) return 1
        
        return StringUtil.naturalCompare(n1, n2)
    }
    
    fun getName(node: AbstractTreeNode<*>?): String? {
        return when (node) {
            null -> null
            is PsiFileNode -> {
                node.value.name
            }

            is PsiDirectoryNode -> {
                node.value.name
            }

            else -> {
                null
            }
        }
    }
}
