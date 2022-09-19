package com.github.johnnysc.numberstesttask.numbers.domain

/**
 * @author Asatryan on 19.09.2022
 */
interface NumbersRepository {

    suspend fun allNumbers(): List<NumberFact>

    suspend fun numberFact(number: String): NumberFact

    suspend fun randomNumberFact(): NumberFact
}