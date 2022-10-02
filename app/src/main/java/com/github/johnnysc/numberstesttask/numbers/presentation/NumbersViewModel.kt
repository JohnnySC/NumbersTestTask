package com.github.johnnysc.numberstesttask.numbers.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.johnnysc.numberstesttask.R
import com.github.johnnysc.numberstesttask.main.presentation.Init
import com.github.johnnysc.numberstesttask.main.presentation.NavigationCommunication
import com.github.johnnysc.numberstesttask.main.presentation.NavigationStrategy
import com.github.johnnysc.numberstesttask.main.presentation.Screen
import com.github.johnnysc.numberstesttask.numbers.domain.NumbersInteractor

/**
 * @author Asatryan on 18.09.2022
 */
interface NumbersViewModel : Init, FetchNumbers, ObserveNumbers, ClearError {

    fun showDetails(item: NumberUi)

    class Base(
        private val handleResult: HandleNumbersRequest,
        private val manageResources: ManageResources,
        private val communications: NumbersCommunications,
        private val interactor: NumbersInteractor,
        private val navigationCommunication: NavigationCommunication.Mutate,
        private val detailsMapper: NumberUi.Mapper<String>,
    ) : ViewModel(), NumbersViewModel {

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) =
            communications.observeProgress(owner, observer)

        override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) =
            communications.observeState(owner, observer)

        override fun observeList(owner: LifecycleOwner, observer: Observer<List<NumberUi>>) =
            communications.observeList(owner, observer)

        override fun init(isFirstRun: Boolean) {
            if (isFirstRun)
                handleResult.handle(viewModelScope) {
                    interactor.init()
                }
        }

        override fun fetchRandomNumberFact() = handleResult.handle(viewModelScope) {
            interactor.factAboutRandomNumber()
        }

        override fun fetchNumberFact(number: String) {
            if (number.isEmpty())
                communications.showState(UiState.ShowError(manageResources.string(R.string.empty_number_error_message)))
            else
                handleResult.handle(viewModelScope) {
                    interactor.factAboutNumber(number)
                }
        }

        override fun clearError() = communications.showState(UiState.ClearError())

        override fun showDetails(item: NumberUi) {
            interactor.saveDetails(item.map(detailsMapper))
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