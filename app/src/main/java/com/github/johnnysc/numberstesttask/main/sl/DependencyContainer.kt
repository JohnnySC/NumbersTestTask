package com.github.johnnysc.numberstesttask.main.sl

import androidx.lifecycle.ViewModel
import com.github.johnnysc.numberstesttask.numbers.presentation.NumbersViewModel
import com.github.johnnysc.numberstesttask.numbers.sl.NumbersModule

/**
 * @author Asatryan on 30.09.2022
 */
interface DependencyContainer {

    fun <T : ViewModel> module(clasz: Class<T>): Module<*>

    class Error : DependencyContainer {
        override fun <T : ViewModel> module(clasz: Class<T>): Module<*> =
            throw IllegalStateException("no module found for $clasz")
    }

    class Base(
        private val core: Core,
        private val dependencyContainer: DependencyContainer = Error()
    ) : DependencyContainer {

        override fun <T : ViewModel> module(clasz: Class<T>): Module<*> =
            if (clasz == NumbersViewModel::class.java)
                NumbersModule(core)
            else
                dependencyContainer.module(clasz)
    }
}