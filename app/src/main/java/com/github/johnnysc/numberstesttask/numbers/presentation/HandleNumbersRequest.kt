package com.github.johnnysc.numberstesttask.numbers.presentation

import android.view.View
import com.github.johnnysc.numberstesttask.numbers.domain.NumbersResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface HandleNumbersRequest {

    fun handle(
        coroutineScope: CoroutineScope,
        block: suspend () -> NumbersResult
    )

    class Base(
        private val dispatchers: DispatchersList,
        private val communications: NumbersCommunications,
        private val numbersResultMapper: NumbersResult.Mapper<Unit>,
    ) : HandleNumbersRequest {

        override fun handle(
            coroutineScope: CoroutineScope,
            block: suspend () -> NumbersResult
        ) {
            communications.showProgress(View.VISIBLE)
            coroutineScope.launch(dispatchers.io()) {
                val result = block.invoke()
                withContext(dispatchers.ui()) {
                    communications.showProgress(View.GONE)
                    result.map(numbersResultMapper)
                }
            }
        }
    }
}