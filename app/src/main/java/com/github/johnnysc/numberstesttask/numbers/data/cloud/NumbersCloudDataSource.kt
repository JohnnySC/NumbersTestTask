package com.github.johnnysc.numberstesttask.numbers.data.cloud

import com.github.johnnysc.numberstesttask.numbers.data.NumberData
import com.github.johnnysc.numberstesttask.numbers.data.cache.FetchNumber

/**
 * @author Asatryan on 19.09.2022
 */
interface NumbersCloudDataSource : FetchNumber {

    suspend fun randomNumber(): NumberData

    class Base(
        private val service: NumbersService,
        private val randomApiHeader: RandomApiHeader
    ) : NumbersCloudDataSource {

        override suspend fun randomNumber(): NumberData {
            val response = service.random()
            val body = response.body() ?: throw IllegalStateException("service unavailable")
            val headers = response.headers()
            randomApiHeader.findInHeaders(headers)?.let { (_, value) ->
                return NumberData(value, body)
            }
            throw IllegalStateException("service unavailable")
        }

        override suspend fun number(number: String): NumberData {
            val fact = service.fact(number)
            return NumberData(number, fact)
        }
    }
}