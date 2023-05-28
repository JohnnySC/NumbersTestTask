package com.github.johnnysc.numberstesttask.numbers.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.johnnysc.numberstesttask.main.presentation.BaseViewModel
import com.github.johnnysc.numberstesttask.main.presentation.Init
import com.github.johnnysc.numberstesttask.main.presentation.NavigationCommunication
import com.github.johnnysc.numberstesttask.main.presentation.NavigationStrategy
import com.github.johnnysc.numberstesttask.main.presentation.RunAsync
import com.github.johnnysc.numberstesttask.main.presentation.Screen
import com.github.johnnysc.numberstesttask.main.presentation.UiFeature
import com.github.johnnysc.numberstesttask.numbers.domain.NumberDetailsUseCase

/**
 * @author Asatryan on 18.09.2022
 */
interface NumbersViewModel : Init, FetchNumbers, ObserveNumbers, ClearError, ShowDetails {

    class Base(
        runAsync: RunAsync,
        private val initial: UiFeature,
        private val numberFact: NumbersFactFeature,
        private val randomNumberFact: UiFeature,
        private val showDetails: ShowDetails,
        private val communications: NumbersCommunications
    ) : BaseViewModel(runAsync), NumbersViewModel {

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) =
            communications.observeProgress(owner, observer)

        override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) =
            communications.observeState(owner, observer)

        override fun observeList(owner: LifecycleOwner, observer: Observer<List<NumberUi>>) =
            communications.observeList(owner, observer)

        override fun init(isFirstRun: Boolean) {
            if (isFirstRun) initial.handle(this)
        }

        override fun fetchRandomNumberFact() {
            randomNumberFact.handle(this)
        }

        override fun fetchNumberFact(number: String) {
            numberFact.number(number).handle(this)
        }

        override fun clearError() = communications.showState(UiState.ClearError())

        override fun showDetails(item: NumberUi) = showDetails.showDetails(item)
    }
}

interface ShowDetails {

    fun showDetails(item: NumberUi)

    class Base(
        private val useCase: NumberDetailsUseCase,
        private val navigationCommunication: NavigationCommunication.Mutate,
        private val detailsMapper: NumberUi.Mapper<String>,
    ) : ShowDetails {

        override fun showDetails(item: NumberUi) {
            useCase.saveDetails(item.map(detailsMapper))
            navigationCommunication.map(NavigationStrategy.Add(Screen.Details))
        }
    }
}

interface FetchNumbers {

    fun fetchRandomNumberFact()

    fun fetchNumberFact(number: String)
}

interface ClearError {
    fun clearError()
}