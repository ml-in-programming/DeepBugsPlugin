package org.jetbrains.research.groups.ml_methods.deepbugs.python.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.research.groups.ml_methods.deepbugs.services.settings.DeepBugsInspectionConfig

@State(name = "deepbugs_python_config", storages = [Storage("deepbugs_python_config.xml")])
class PyDeepBugsInspectionConfig : PersistentStateComponent<PyDeepBugsInspectionConfig>, DeepBugsInspectionConfig {
    override var curBinOperatorThreshold = PyDeepBugsInspectionConfigurable.PY_DEFAULT_BIN_OPERATOR_CONFIG
    override var curBinOperandThreshold = PyDeepBugsInspectionConfigurable.PY_DEFAULT_BIN_OPERAND_CONFIG
    override var curSwappedArgsThreshold = PyDeepBugsInspectionConfigurable.PY_DEFAULT_SWAPPED_ARGS_CONFIG

    override fun getState(): PyDeepBugsInspectionConfig = this

    override fun loadState(state: PyDeepBugsInspectionConfig) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        fun getInstance(): PyDeepBugsInspectionConfig = ServiceManager.getService(PyDeepBugsInspectionConfig::class.java)
    }
}