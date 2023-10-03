package com.example.idedevelopment

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.table.JBTable
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction
import java.awt.BorderLayout
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JTable
import javax.swing.ScrollPaneConstants
import javax.swing.table.DefaultTableModel

class TWPanel(project: Project) : SimpleToolWindowPanel(true) {
    private val updateButton: JButton = JButton("Update Statistic")
    private val table: JTable = JBTable(DefaultTableModel())

    init {

        updatePanel(project)
        layout = BorderLayout()

        val buttonPanel = JPanel()
        buttonPanel.add(updateButton)

        val scrollPane = JBScrollPane(table)
        scrollPane.horizontalScrollBarPolicy = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
        scrollPane.verticalScrollBarPolicy = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS

        add(buttonPanel, BorderLayout.NORTH)
        add(scrollPane, BorderLayout.CENTER)


        updateButton.addActionListener {
            updatePanel(project)
        }
    }

    private fun updatePanel(project: Project) {
        val tableModel = DefaultTableModel(arrayOf("Relative Path", "# Classes", "# Functions"), 0)
        val scope = GlobalSearchScope.projectScope(project)
        val psiManager: PsiManager = PsiManager.getInstance(project)

        val virtualFiles = FilenameIndex.getAllFilesByExt(project, "kt", scope)
        for (v in virtualFiles) {
            val psiFile = psiManager.findFile(v)
            val relativePath = project.basePath?.let { v.path.substring(it.length + 1) }
            tableModel.addRow(arrayOf(relativePath, countClasses(psiFile), countFunctions(psiFile)))
        }
        table.model = tableModel
    }

    private fun countClasses(psiFile: PsiFile?): Int {
        val ktClasses = PsiTreeUtil.findChildrenOfType(psiFile, KtClass::class.java)
        return ktClasses.size
    }

    private fun countFunctions(psiFile: PsiFile?): Int {
        val ktFunctions = PsiTreeUtil.findChildrenOfType(psiFile, KtNamedFunction::class.java)
        return ktFunctions.size
    }
}
