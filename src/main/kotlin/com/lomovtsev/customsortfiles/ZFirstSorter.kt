package com.lomovtsev.customsortfiles

import com.intellij.icons.AllIcons
import com.intellij.ide.projectView.impl.nodes.PsiFileNode
import com.intellij.ide.util.treeView.AbstractTreeNode
import com.intellij.ide.util.treeView.smartTree.ActionPresentation
import com.intellij.ide.util.treeView.smartTree.ActionPresentationData
import com.intellij.ide.util.treeView.smartTree.Sorter
import com.intellij.ide.util.treeView.smartTree.SorterUtil

/**
 * TODO: not worked path, remove it!
 */
class ZFirstSorter: Sorter {
    override fun getPresentation(): ActionPresentation {
        return ActionPresentationData(
            "Z First Sort",
            null,
            AllIcons.ObjectBrowser.Sorted
        )
    }

    override fun getName(): String {
        return "Z First Sort"
    }

    override fun getComparator(): Comparator<AbstractTreeNode<*>> {
        return Comparator { n1, n2 ->
            if (isZFirst(n1)) {
                -1
            } else if(isZFirst(n2)) {
                1
            } else {
                val s1 = SorterUtil.getStringPresentation(n1)
                val s2 = SorterUtil.getStringPresentation(n2)
                s1.compareTo(s2, ignoreCase = true)
            }
        }
    }

    override fun isVisible(): Boolean {
        return true
    }
    
    private fun isZFirst(node: AbstractTreeNode<*>): Boolean {
        if (node is PsiFileNode) {
            return "zfirst.txt" == node.value.name
        }
        return false
    }
}
