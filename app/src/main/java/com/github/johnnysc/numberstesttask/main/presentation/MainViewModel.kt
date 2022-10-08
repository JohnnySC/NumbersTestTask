package com.github.johnnysc.numberstesttask.main.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.github.johnnysc.numberstesttask.numbers.presentation.Communication
import com.github.johnnysc.numberstesttask.random.WorkManagerWrapper

/**
 * @author Asatryan on 01.10.2022
 */
class MainViewModel(
    private val workManagerWrapper: WorkManagerWrapper,
    private val navigationCommunication: NavigationCommunication.Mutable
) : ViewModel(), Init, Communication.Observe<NavigationStrategy> {

    override fun init(isFirstRun: Boolean) {
        if (isFirstRun) {
            navigationCommunication.map(NavigationStrategy.Replace(Screen.Numbers))
            workManagerWrapper.start()
        }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<NavigationStrategy>) =
        navigationCommunication.observe(owner, observer)
}