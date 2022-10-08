package com.github.johnnysc.numberstesttask.random

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.github.johnnysc.numberstesttask.numbers.domain.RandomNumberRepository

/**
 * @author Asatryan on 08.10.2022
 */
class PeriodicRandomWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result = try {
        val repository = (applicationContext as ProvidePeriodicRepository)
            .providePeriodicRepository()
        repository.randomNumberFact()
        Result.success()
    } catch (e: Exception) {
        Result.retry()
    }
}

interface ProvidePeriodicRepository {
    fun providePeriodicRepository(): RandomNumberRepository
}