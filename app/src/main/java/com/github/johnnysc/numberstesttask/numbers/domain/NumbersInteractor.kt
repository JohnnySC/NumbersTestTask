package com.github.johnnysc.numberstesttask.numbers.domain

import com.github.johnnysc.numberstesttask.details.data.NumberFactDetails

/**
 * @author Asatryan on 18.09.2022
 */
interface NumbersInteractor {

    fun saveDetails(details: String)

    suspend fun init(): NumbersResult

    suspend fun factAboutNumber(number: String): NumbersResult

    suspend fun factAboutRandomNumber(): NumbersResult

    class Base(
        private val repository: NumbersRepository,
        private val handleRequest: HandleRequest,
        private val numberFactDetails: NumberFactDetails.Save
    ) : NumbersInteractor {

        override fun saveDetails(details: String) = numberFactDetails.save(details)

        override suspend fun init() = NumbersResult.Success(repository.allNumbers())

        override suspend fun factAboutNumber(number: String) = handleRequest.handle {
            repository.numberFact(number)
        }

        override suspend fun factAboutRandomNumber() = handleRequest.handle {
            repository.randomNumberFact()
        }
    }
}