package com.lomovtsev.customsortfiles

import com.intellij.ide.projectView.impl.ProjectViewPane
import com.intellij.ide.util.treeView.NodeDescriptor
import com.intellij.openapi.project.Project

class CustomSortProjectViewPane(project: Project) : ProjectViewPane(project) {
    companion object {
        const val ID = "ZFirstProjectView"
    }

    override fun getTitle(): String = "ZFirst View"

    override fun getId(): String = ID
    override fun getWeight(): Int = 50

    /**
     * break the ordering after StructureProvider modifications 
     */
    override fun createComparator(): Comparator<NodeDescriptor<*>> {
        return Comparator { _, _ -> 0 }
    }


}
