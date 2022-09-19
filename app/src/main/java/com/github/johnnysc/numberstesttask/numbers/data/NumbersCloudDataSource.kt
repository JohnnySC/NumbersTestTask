package com.github.johnnysc.numberstesttask.numbers.data

/**
 * @author Asatryan on 19.09.2022
 */
interface NumbersCloudDataSource : FetchNumber {

    suspend fun randomNumber(): NumberData
}