package com.github.johnnysc.numberstesttask.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.johnnysc.numberstesttask.numbers.presentation.DispatchersList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Asatryan on 08.04.2023
 */
abstract class BaseViewModel(
    private val runAsync: RunAsync
) : ViewModel(), Handle {

    override fun <T : Any> handle(block: suspend () -> T, ui: (T) -> Unit) =
        runAsync.runAsync(viewModelScope, block, ui)
}

interface Handle {
    fun <T : Any> handle(
        block: suspend () -> T, ui: (T) -> Unit
    )
}

interface RunAsync {

    fun <T : Any> runAsync(
        scope: CoroutineScope, block: suspend () -> T, ui: (T) -> Unit
    )

    class Base(
        private val dispatchersList: DispatchersList
    ) : RunAsync {

        override fun <T : Any> runAsync(
            scope: CoroutineScope, block: suspend () -> T, ui: (T) -> Unit
        ) {
            scope.launch(dispatchersList.io()) {
                val result = block.invoke()
                withContext(dispatchersList.ui()) {
                    ui.invoke(result)
                }
            }
        }
    }
}