package org.jetbrains.research.deepbugs.services.logger

import com.intellij.internal.statistic.eventLog.*

object DeepBugsEventLogger {
    private val loggerProvider: StatisticsEventLoggerProvider = getEventLogProvider("DBP")

    fun log(group: EventLogGroup, action: String) {
        return loggerProvider.logger.log(group, action, false)
    }

    fun log(group: EventLogGroup, action: String, data: Map<String, Any>) {
        return loggerProvider.logger.log(group, action, data, false)
    }

    fun logState(group: EventLogGroup, action: String) {
        return loggerProvider.logger.log(group, action, true)
    }

    fun logState(group: EventLogGroup, action: String, data: Map<String, Any>) {
        return loggerProvider.logger.log(group, action, data, true)
    }

    fun getConfig(): StatisticsEventLoggerProvider {
        return loggerProvider
    }

    fun isEnabled(): Boolean {
        return loggerProvider.logger !is EmptyStatisticsEventLogger
    }
}