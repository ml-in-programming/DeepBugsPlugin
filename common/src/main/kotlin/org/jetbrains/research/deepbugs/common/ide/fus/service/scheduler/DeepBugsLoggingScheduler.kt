package org.jetbrains.research.deepbugs.common.ide.fus.service.scheduler

import com.intellij.concurrency.JobScheduler
import com.intellij.ide.ApplicationInitializedListener
import com.intellij.internal.statistic.utils.StatisticsUploadAssistant
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.*
import org.jetbrains.research.deepbugs.common.ide.fus.service.log.DeepBugsStateLogger
import java.util.*
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

class DeepBugsLoggingScheduler : ApplicationInitializedListener {
    private val persistStatisticsSessionsMap = Collections.synchronizedMap(HashMap<Project, Future<*>>())

    override fun componentsInitialized() {
        scheduleStatesLogging()
    }

    private fun scheduleStatesLogging() {
        if (!StatisticsUploadAssistant.isSendAllowed()) return

        val connection = ApplicationManager.getApplication().messageBus.connect()
        connection.subscribe(ProjectManager.TOPIC, object : ProjectManagerListener {
            override fun projectOpened(project: Project) {
                val future = JobScheduler.getScheduler().scheduleWithFixedDelay(
                    { DeepBugsStateLogger.getInstance().logProjectStates(project) },
                    LOG_PROJECTS_STATES_INITIAL_DELAY_MIN.toLong(),
                    LOG_PROJECTS_STATES_DELAY_MIN.toLong(), TimeUnit.MINUTES
                )
                persistStatisticsSessionsMap[project] = future
            }

            override fun projectClosed(project: Project) {
                persistStatisticsSessionsMap.remove(project)?.cancel(true)
            }
        })
    }

    companion object {
        private const val LOG_PROJECTS_STATES_INITIAL_DELAY_MIN = 12
        private const val LOG_PROJECTS_STATES_DELAY_MIN = 12 * 60
    }
}