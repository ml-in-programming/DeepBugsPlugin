package org.jetbrains.research.groups.ml_methods.deepbugs.python.inspections

import org.jetbrains.research.groups.ml_methods.deepbugs.python.models_manager.ModelsManager
import org.jetbrains.research.groups.ml_methods.deepbugs.python.settings.PyDeepBugsInspectionConfig
import org.jetbrains.research.groups.ml_methods.deepbugs.python.utils.DeepBugsPythonBundle

class PyDeepBugsBinOperandInspection : PyDeepBugsBinExprInspection() {
    override val keyMessage: String = "binary.operand.inspection.warning"

    override fun getModel() = ModelsManager.binOperandModel
    override fun getThreshold(): Float = PyDeepBugsInspectionConfig.getInstance().curBinOperandThreshold

    override fun getDisplayName() = DeepBugsPythonBundle.message("binary.operand.inspection.display")
    override fun getShortName(): String = DeepBugsPythonBundle.message("bin.operand.inspection.short.name")
}