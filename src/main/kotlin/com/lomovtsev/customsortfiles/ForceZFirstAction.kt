package com.lomovtsev.customsortfiles

import com.intellij.ide.projectView.ProjectView
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project

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
