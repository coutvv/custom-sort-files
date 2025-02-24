package com.lomovtsev.customsortfiles

import com.intellij.ide.SelectInTarget
import com.intellij.ide.impl.ProjectViewSelectInPaneTarget
import com.intellij.ide.projectView.impl.ProjectAbstractTreeStructureBase
import com.intellij.ide.projectView.impl.ProjectViewPane
import com.intellij.ide.projectView.impl.ProjectViewTree
import com.intellij.ide.util.treeView.NodeDescriptor
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import java.util.Comparator
import javax.swing.tree.DefaultTreeModel

class ZFirstProjectViewPane(project: Project) : ProjectViewPane(project) {
    companion object {
        const val ID = "ZFirstProjectView"
    }

    private val logger = thisLogger()
    private val filename = "README.md"

    override fun getTitle(): String = "ZFirst View"

    override fun getId(): String = ID
    override fun getWeight(): Int = 50

    override fun createComparator(): Comparator<NodeDescriptor<*>> {
        return Comparator { _, _ -> 0 }
    }

    private fun readmeLastComparator(): Comparator<NodeDescriptor<*>?> {
        val comparator = super.createComparator();
        return Comparator { o1, o2 ->
            logger.warn("sorting comparator for $o1 and $o2")
            if (o1.toString() == filename) {
                return@Comparator 1
            }
            if (o2.toString() == filename) {
                return@Comparator -1
            }
            return@Comparator comparator.compare(o1, o2)
        }
    }


    private fun zFirstComparator(): Comparator<NodeDescriptor<*>?> {
        val comparator = super.createComparator();
        return Comparator { o1, o2 ->
            logger.warn("sorting comparator for $o1 and $o2")
            if (o1.toString() == "zfirst.txt") {
                return@Comparator -1
            }
            if (o2.toString() == "zfirst.txt") {
                return@Comparator 1
            }
            return@Comparator comparator.compare(o1, o2)
        }
    }

}
