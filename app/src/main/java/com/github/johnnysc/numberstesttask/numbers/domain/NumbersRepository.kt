package com.github.johnnysc.numberstesttask.numbers.domain

/**
 * @author Asatryan on 19.09.2022
 */
interface NumbersRepository : RandomNumberRepository {

    suspend fun allNumbers(): List<NumberFact>

    suspend fun numberFact(number: String): NumberFact
}

interface RandomNumberRepository {
    suspend fun randomNumberFact(): NumberFact
}