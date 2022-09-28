package com.github.johnnysc.numberstesttask.numbers.data

import com.github.johnnysc.numberstesttask.numbers.data.cache.NumbersCacheDataSource
import com.github.johnnysc.numberstesttask.numbers.data.cloud.NumbersCloudDataSource
import com.github.johnnysc.numberstesttask.numbers.domain.NumberFact
import com.github.johnnysc.numberstesttask.numbers.domain.NumbersRepository

/**
 * @author Asatryan on 19.09.2022
 */
class BaseNumbersRepository(
    private val cloudDataSource: NumbersCloudDataSource,
    private val cacheDataSource: NumbersCacheDataSource,
    private val handleDataRequest: HandleDataRequest,
    private val mapperToDomain: NumberData.Mapper<NumberFact>,
) : NumbersRepository {

    override suspend fun allNumbers(): List<NumberFact> {
        val data = cacheDataSource.allNumbers()
        return data.map { it.map(mapperToDomain) }
    }

    override suspend fun numberFact(number: String) = handleDataRequest.handle {
        val dataSource = if (cacheDataSource.contains(number))
            cacheDataSource
        else
            cloudDataSource
        dataSource.number(number)
    }

    override suspend fun randomNumberFact() = handleDataRequest.handle {
        cloudDataSource.randomNumber()
    }
}