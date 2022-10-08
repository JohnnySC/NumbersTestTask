package com.github.johnnysc.numberstesttask.main.sl

import androidx.lifecycle.ViewModel
import com.github.johnnysc.numberstesttask.details.presentation.NumberDetailsViewModel
import com.github.johnnysc.numberstesttask.details.sl.NumberDetailsModule
import com.github.johnnysc.numberstesttask.main.presentation.MainViewModel
import com.github.johnnysc.numberstesttask.numbers.domain.NumbersRepository
import com.github.johnnysc.numberstesttask.numbers.presentation.NumbersViewModel
import com.github.johnnysc.numberstesttask.numbers.sl.NumbersModule
import com.github.johnnysc.numberstesttask.numbers.sl.ProvideNumbersRepository
import com.github.johnnysc.numberstesttask.random.WorkManagerWrapper

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
    ) : DependencyContainer, ProvideNumbersRepository {

        private val repository: NumbersRepository by lazy {
            ProvideNumbersRepository.Base(core).provideNumbersRepository()
        }

        override fun <T : ViewModel> module(clasz: Class<T>): Module<*> = when (clasz) {
            MainViewModel::class.java -> MainModule(core)
            NumbersViewModel.Base::class.java -> NumbersModule(core, this)
            NumberDetailsViewModel::class.java -> NumberDetailsModule(core)
            else -> dependencyContainer.module(clasz)
        }

        override fun provideNumbersRepository(): NumbersRepository = repository
    }
}