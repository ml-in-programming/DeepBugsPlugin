package org.jetbrains.research.groups.ml_methods.deepbugs.python.inspections

import org.jetbrains.research.groups.ml_methods.deepbugs.python.inspections.base.PyDeepBugsCallExprInspection
import org.jetbrains.research.groups.ml_methods.deepbugs.python.settings.PyDeepBugsInspectionConfig
import org.jetbrains.research.groups.ml_methods.deepbugs.python.utils.DeepBugsPythonBundle
import org.jetbrains.research.groups.ml_methods.deepbugs.python.utils.models

class PyDeepBugsSwappedArgsInspection : PyDeepBugsCallExprInspection() {
    override val keyMessage = "python.swapped.args.inspection.warning"

    override fun getModel() = models.swappedArgsModel
    override fun getThreshold() = PyDeepBugsInspectionConfig.getInstance().curSwappedArgsThreshold

    override fun getDisplayName() = DeepBugsPythonBundle.message("python.swapped.args.inspection.display")
    override fun getShortName(): String = DeepBugsPythonBundle.message("python.swapped.args.inspection.short.name")
}