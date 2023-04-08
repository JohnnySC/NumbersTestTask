package com.github.johnnysc.numberstesttask.numbers.presentation

import com.github.johnnysc.numberstesttask.numbers.domain.NumbersResult
import com.github.johnnysc.numberstesttask.numbers.domain.RandomNumbersFactUseCase

/**
 * @author Asatryan on 08.04.2023
 */
class RandomNumberFactFeature(
    private val useCase: RandomNumbersFactUseCase,
    communications: NumbersCommunications,
    numbersResultMapper: NumbersResult.Mapper<Unit>,
) : NumbersFeature(communications, numbersResultMapper) {

    override suspend fun invoke() = useCase.factAboutRandomNumber()
}