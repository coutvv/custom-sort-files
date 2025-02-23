package com.lomovtsev.customsortfiles

import com.intellij.ide.projectView.ProjectView
import com.intellij.ide.projectView.impl.ProjectViewPane
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindowManager

class ForceZFirstAction: AnAction() {
    private val logger = thisLogger()

    override fun actionPerformed(e: AnActionEvent) {
        logger.warn("try force zfirst...")
        val project: Project = e.project ?: return

        val projectView = ProjectView.getInstance(project)
        projectView.currentProjectViewPane.updateFromRoot(true)
        logger.warn("try force is done!")
    }
}
