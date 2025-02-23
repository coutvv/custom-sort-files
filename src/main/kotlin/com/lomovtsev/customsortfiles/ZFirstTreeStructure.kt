package com.lomovtsev.customsortfiles

import com.intellij.ide.projectView.ProjectView
import com.intellij.ide.projectView.ProjectViewSettings
import com.intellij.ide.projectView.TreeStructureProvider
import com.intellij.ide.projectView.ViewSettings
import com.intellij.ide.projectView.impl.ProjectTreeStructure
import com.intellij.ide.projectView.impl.nodes.ProjectViewProjectNode
import com.intellij.ide.util.treeView.AbstractTreeNode
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.registry.Registry

class ZFirstTreeStructure(
    private val pane: ZFirstProjectViewPane,
    project: Project
) : ProjectTreeStructure(project, "zFirstTreeStruture"), ProjectViewSettings {

    override fun createRoot(project: Project, settings: ViewSettings): AbstractTreeNode<*> {
        val result = ProjectViewProjectNode(project, settings)
//        sortTreeStructure(result.children)
        return result;
    }
    

    override fun getChildElements(element: Any): Array<out Any?> {
        return super.getChildElements(element)
    }

    override fun getProviders(): List<TreeStructureProvider> {
        return listOf(
            ZFirstTreeStructureProvider()
        )
    }

    override fun setProviders(vararg treeStructureProviders: TreeStructureProvider?) {
        // nothing to do
    }

    override fun isShowExcludedFiles(): Boolean {
        return ProjectView.getInstance(myProject).isShowExcludedFiles(pane.id)
    }

    override fun isShowLibraryContents(): Boolean {
        return true
    }

    override fun isUseFileNestingRules(): Boolean {
        return ProjectView.getInstance(myProject).isUseFileNestingRules(pane.id)
    }

    override fun isToBuildChildrenInBackground(element: Any): Boolean {
        return Registry.`is`("ide.projectView.ProjectViewPaneTreeStructure.BuildChildrenInBackground")
    }

    private fun sortTreeStructure(children: Collection<AbstractTreeNode<*>>?) {

        if (children == null || children.isEmpty()) {
            return
        }
        val s =  children.joinToString { it.name ?: it.value.toString() }
        println(s)
        (children as MutableList).sortWith { o1, o2 ->
            if (o1.value == "zfirst.txt") {
                return@sortWith -1
            }
            if (o2.value == "zfirst.txt") {
                return@sortWith 1
            }
            return@sortWith o1.name?.compareTo(o2?.name ?: "") ?: 0
        }
        children.forEach {
            sortTreeStructure(it.children)
        }

    }
}
