package com.github.johnnysc.numberstesttask.numbers.data.cache

import com.github.johnnysc.numberstesttask.numbers.data.NumberData

/**
 * @author Asatryan on 19.09.2022
 */
interface NumbersCacheDataSource : FetchNumber {

    suspend fun allNumbers(): List<NumberData>

    suspend fun contains(number: String): Boolean

    suspend fun saveNumber(numberData: NumberData)
}

interface FetchNumber {
    suspend fun number(number: String): NumberData
}