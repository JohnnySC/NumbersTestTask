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

    override fun map(list: List<NumberFact>, errorMessage: String) = communications.showState(
        if (errorMessage.isEmpty()) {
            if (list.isNotEmpty())
                communications.showList(list.map { it.map(mapper) })
            UiState.Success()
        } else
            UiState.ShowError(errorMessage)
    )
}