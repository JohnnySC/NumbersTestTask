package com.github.johnnysc.numberstesttask.main.sl

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.github.johnnysc.numberstesttask.BuildConfig
import com.github.johnnysc.numberstesttask.random.ProvidePeriodicRepository

/**
 * @author Asatryan on 26.09.2022
 */
class NumbersApp : Application(), ProvideViewModel, ProvidePeriodicRepository {

    private lateinit var viewModelsFactory: ViewModelsFactory
    private lateinit var dependencyContainer: DependencyContainer.Base

    override fun onCreate() {
        super.onCreate()
        val provideInstances = if (BuildConfig.DEBUG)
            ProvideInstances.Mock(this)
        else
            ProvideInstances.Release(this)

        dependencyContainer = DependencyContainer.Base(Core.Base(this, provideInstances))
        viewModelsFactory = ViewModelsFactory(dependencyContainer)
    }

    override fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, viewModelsFactory)[clazz]

    override fun providePeriodicRepository() = dependencyContainer.provideNumbersRepository()
}