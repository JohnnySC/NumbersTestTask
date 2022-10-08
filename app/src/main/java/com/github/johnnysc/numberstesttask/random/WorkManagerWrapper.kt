package com.github.johnnysc.numberstesttask.random

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

/**
 * @author Asatryan on 08.10.2022
 */
interface WorkManagerWrapper {

    fun start()

    class Base(context: Context) : WorkManagerWrapper {

        private val workManager = WorkManager.getInstance(context)

        override fun start() {
            val request = PeriodicWorkRequestBuilder<PeriodicRandomWorker>(
                15,
                TimeUnit.MINUTES
            ).setConstraints(
                Constraints.Builder()
                    .setRequiresBatteryNotLow(true)
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            ).build()
            workManager.enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
        }

        companion object {
            private const val WORK_NAME = "random number work"
        }
    }
}