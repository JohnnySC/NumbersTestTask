package com.github.johnnysc.numberstesttask.numbers.presentation

import com.github.johnnysc.numberstesttask.numbers.domain.NumbersInitialUseCase
import com.github.johnnysc.numberstesttask.numbers.domain.NumbersResult

/**
 * @author Asatryan on 08.04.2023
 */
class NumbersInitialFeature(
    communications: NumbersCommunications,
    numbersResultMapper: NumbersResult.Mapper<Unit>,
    private val useCase: NumbersInitialUseCase
) : NumbersFeature(communications, numbersResultMapper) {

    override suspend fun invoke() = useCase.init()
}