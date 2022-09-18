package com.github.johnnysc.numberstesttask.numbers.domain

/**
 * @author Asatryan on 18.09.2022
 */
interface NumbersInteractor {

    suspend fun init(): NumbersResult

    suspend fun factAboutNumber(number: String): NumbersResult

    suspend fun factAboutRandomNumber(): NumbersResult
}