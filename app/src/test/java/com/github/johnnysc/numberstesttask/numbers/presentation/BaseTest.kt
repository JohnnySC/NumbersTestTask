package com.github.johnnysc.numberstesttask.numbers.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.johnnysc.numberstesttask.main.presentation.NavigationCommunication
import com.github.johnnysc.numberstesttask.main.presentation.NavigationStrategy
import com.github.johnnysc.numberstesttask.main.presentation.RunAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

/**
 * @author Asatryan on 18.09.2022
 */
abstract class BaseTest {

    protected class FakeRunAsync : RunAsync {
        override fun <T : Any> runAsync(
            scope: CoroutineScope,
            block: suspend () -> T,
            ui: (T) -> Unit
        ) = runBlocking { block.invoke().let { ui.invoke(it) } }
    }

    protected class TestNavigationCommunication : NavigationCommunication.Mutable {

        lateinit var strategy: NavigationStrategy
        var count = 0
        override fun observe(owner: LifecycleOwner, observer: Observer<NavigationStrategy>) = Unit

        override fun map(source: NavigationStrategy) {
            strategy = source
            count++
        }
    }

    protected class TestNumbersCommunications : NumbersCommunications {

        val progressCalledList = mutableListOf<Int>()
        val stateCalledList = mutableListOf<UiState>()
        var timesShowList = 0
        val numbersList = mutableListOf<NumberUi>()

        override fun showProgress(show: Int) {
            progressCalledList.add(show)
        }

        override fun showState(uiState: UiState) {
            stateCalledList.add(uiState)
        }

        override fun showList(list: List<NumberUi>) {
            timesShowList++
            numbersList.addAll(list)
        }

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) = Unit
        override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) = Unit
        override fun observeList(owner: LifecycleOwner, observer: Observer<List<NumberUi>>) = Unit
    }
}