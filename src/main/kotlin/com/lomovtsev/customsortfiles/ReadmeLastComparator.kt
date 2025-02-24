package com.lomovtsev.customsortfiles

import com.intellij.ide.util.treeView.NodeDescriptor

class ReadmeLastComparator(
    private val comparator: Comparator<NodeDescriptor<*>>,
    private val filename: String = "README.md"
) : java.util.Comparator<NodeDescriptor<*>> {
    override fun compare(
        o1: NodeDescriptor<*>?,
        o2: NodeDescriptor<*>?
    ): Int {
        return if (o1.toString() == filename) {
            1
        } else if (o2.toString() == filename) {
            -1
        } else {
            comparator.compare(o1, o2)
        }

    }
}
