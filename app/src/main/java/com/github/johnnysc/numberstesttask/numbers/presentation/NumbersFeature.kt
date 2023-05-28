package com.github.johnnysc.numberstesttask.numbers.presentation

import android.view.View
import com.github.johnnysc.numberstesttask.main.presentation.Handle
import com.github.johnnysc.numberstesttask.main.presentation.UiFeature
import com.github.johnnysc.numberstesttask.numbers.domain.NumbersResult

/**
 * @author Asatryan on 08.04.2023
 */
abstract class NumbersFeature(
    private val communications: NumbersCommunications,
    private val numbersResultMapper: NumbersResult.Mapper<Unit>
) : UiFeature, suspend () -> NumbersResult {

    override fun handle(handle: Handle) {
        communications.showProgress(View.VISIBLE)
        handle.handle(this) { result ->
            communications.showProgress(View.GONE)
            showUi(result)
        }
    }

    protected fun showUi(result: NumbersResult) = result.map(numbersResultMapper)
}