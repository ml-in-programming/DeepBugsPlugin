package org.jetbrains.research.groups.ml_methods.deepbugs.python.settings

import com.intellij.openapi.options.Configurable
import org.jetbrains.research.groups.ml_methods.deepbugs.python.utils.DeepBugsPythonBundle
import javax.swing.JComponent

class PyDeepBugsInspectionConfigurable(private val settings: PyDeepBugsInspectionConfig) : Configurable {
    companion object {
        const val defaultBinOperatorConfig: Float = 0.94f
        const val defaultBinOperandConfig: Float = 0.95f
        const val defaultSwappedArgsConfig: Float = 0.96f
    }

    private var deepBugsUI: PyDeepBugsUI? = null

    override fun getHelpTopic(): String? = null

    override fun getDisplayName() = DeepBugsPythonBundle.message("plugin.name")

    override fun isModified() =
            (deepBugsUI!!.binOperatorThreshold != settings.curBinOperatorThreshold) ||
                    (deepBugsUI!!.binOperandThreshold != settings.curBinOperandThreshold) ||
                    (deepBugsUI!!.swappedArgsThreshold != settings.curSwappedArgsThreshold)


    override fun apply() {
        settings.curBinOperatorThreshold = deepBugsUI!!.binOperatorThreshold
        settings.curBinOperandThreshold = deepBugsUI!!.binOperandThreshold
        settings.curSwappedArgsThreshold = deepBugsUI!!.swappedArgsThreshold
    }

    override fun reset() {
        deepBugsUI!!.binOperatorThreshold = settings.curBinOperatorThreshold
        deepBugsUI!!.binOperandThreshold = settings.curBinOperandThreshold
        deepBugsUI!!.swappedArgsThreshold = settings.curSwappedArgsThreshold
    }

    override fun disposeUIResources() {
        deepBugsUI = null
    }

    override fun createComponent(): JComponent? {
        deepBugsUI = PyDeepBugsUI()
        deepBugsUI!!.binOperatorThreshold = settings.curBinOperatorThreshold
        deepBugsUI!!.binOperandThreshold = settings.curBinOperandThreshold
        deepBugsUI!!.swappedArgsThreshold = settings.curSwappedArgsThreshold
        return deepBugsUI!!.rootPanel
    }
}