package com.lomovtsev.customsortfiles

import com.intellij.ide.SelectInTarget
import com.intellij.ide.impl.ProjectViewSelectInPaneTarget
import com.intellij.ide.projectView.impl.ProjectAbstractTreeStructureBase
import com.intellij.ide.projectView.impl.ProjectViewPane
import com.intellij.ide.util.treeView.NodeDescriptor
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project

class ZFirstProjectViewPane(project: Project): ProjectViewPane(project) {
    companion object {
        const val ID = "ZFirstProjectView"
    }

    private val logger = thisLogger()
    private val filename = "README.md"

    override fun getTitle(): String = "ZFirst View"

    override fun getId(): String = ID
    override fun getWeight(): Int = 50

    override fun createStructure(): ProjectAbstractTreeStructureBase {
        return ZFirstTreeStructure(this, myProject)
    }

    override fun installComparator() {
        installComparator(createComparator())
    }
    

    override fun createComparator(): Comparator<NodeDescriptor<*>?> {
        return readmeLastComparator()
    }
    

    override fun installComparator(comparator: java.util.Comparator<in NodeDescriptor<*>>) {
        
        super.installComparator(comparator)
    }

    override fun createSelectInTarget(): SelectInTarget {
        return ProjectViewSelectInPaneTarget(myProject, this, false)
    }
    
    private fun readmeLastComparator(): Comparator<NodeDescriptor<*>?> {
        val comparator = super.createComparator();
        return Comparator { o1, o2 ->
            logger.warn("sorting comparator for $o1 and $o2")
            if (o1.toString() == filename) { return@Comparator 1 }
            if (o2.toString() == filename) { return@Comparator -1 }
            return@Comparator comparator.compare(o1, o2)
        }
    }
    
     
    private fun zFirstComparator(): Comparator<NodeDescriptor<*>?> {
        val comparator = super.createComparator();
        return Comparator { o1, o2 ->
            logger.warn("sorting comparator for $o1 and $o2")
            if (o1.toString() == "zfirst.txt") { return@Comparator -1 }
            if (o2.toString() == "zfirst.txt") { return@Comparator 1 }
            return@Comparator comparator.compare(o1, o2)
        }
    }

}
