package com.github.johnnysc.numberstesttask.numbers.presentation

import com.github.johnnysc.numberstesttask.numbers.domain.NumberFact
import com.github.johnnysc.numberstesttask.numbers.domain.NumbersResult

/**
 * @author Asatryan on 18.09.2022
 */
class NumbersResultMapper(
    private val communications: NumbersCommunications,
    private val mapper: NumberFact.Mapper<NumberUi>
) : NumbersResult.Mapper<Unit> {

    override fun map(list: List<NumberFact>) {
        if (list.isNotEmpty())
            communications.showList(list.map { it.map(mapper) })
        communications.showState(UiState.Success())
    }

    override fun map(errorMessage: String) =
        communications.showState(UiState.ShowError(errorMessage))
}