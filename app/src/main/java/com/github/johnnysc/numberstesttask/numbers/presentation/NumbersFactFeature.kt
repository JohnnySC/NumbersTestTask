package com.github.johnnysc.numberstesttask.numbers.presentation

import com.github.johnnysc.numberstesttask.R
import com.github.johnnysc.numberstesttask.main.presentation.Handle
import com.github.johnnysc.numberstesttask.main.presentation.UiFeature
import com.github.johnnysc.numberstesttask.numbers.domain.NumbersFactUseCase
import com.github.johnnysc.numberstesttask.numbers.domain.NumbersResult

/**
 * @author Asatryan on 08.04.2023
 */
interface NumbersFactFeature : UiFeature, suspend () -> NumbersResult {

    fun number(number: String): UiFeature

    class Base(
        private val manageResources: ManageResources,
        communications: NumbersCommunications,
        numbersResultMapper: NumbersResult.Mapper<Unit>,
        private val useCase: NumbersFactUseCase
    ) : NumbersFeature(communications, numbersResultMapper), NumbersFactFeature {

        private var number: String = "0"

        override fun number(number: String): UiFeature {
            this.number = number
            return this
        }

        override fun handle(handle: Handle) {
            if (number.isEmpty())
                handle.handle({
                    NumbersResult.Failure(
                        manageResources.string(R.string.empty_number_error_message)
                    )
                }) { showUi(it) }
            else
                super.handle(handle)
        }

        override suspend fun invoke() = useCase.factAboutNumber(number)
    }
}