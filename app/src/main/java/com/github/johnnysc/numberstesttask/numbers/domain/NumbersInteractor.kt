package com.github.johnnysc.numberstesttask.numbers.domain

import com.github.johnnysc.numberstesttask.details.data.NumberFactDetails

/**
 * @author Asatryan on 18.09.2022
 */
interface NumbersInteractor :
    NumbersInitialUseCase,
    NumbersFactUseCase,
    RandomNumbersFactUseCase,
    NumberDetailsUseCase {

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

interface NumbersInitialUseCase {
    suspend fun init(): NumbersResult
}

interface NumbersFactUseCase {
    suspend fun factAboutNumber(number: String): NumbersResult
}

interface RandomNumbersFactUseCase {
    suspend fun factAboutRandomNumber(): NumbersResult
}

interface NumberDetailsUseCase {
    fun saveDetails(details: String)
}