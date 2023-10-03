package com.example.idedevelopment

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class TWFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val content =
            ContentFactory.SERVICE.getInstance().createContent(TWPanel(project), "", false)
        toolWindow.contentManager.addContent(content)
    }
}
