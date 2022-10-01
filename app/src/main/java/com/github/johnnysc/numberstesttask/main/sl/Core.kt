package com.github.johnnysc.numberstesttask.main.sl

import android.content.Context
import com.github.johnnysc.numberstesttask.numbers.data.cache.CacheModule
import com.github.johnnysc.numberstesttask.numbers.data.cloud.CloudModule
import com.github.johnnysc.numberstesttask.numbers.presentation.DispatchersList
import com.github.johnnysc.numberstesttask.numbers.presentation.ManageResources

/**
 * @author Asatryan on 30.09.2022
 */
interface Core : CloudModule, CacheModule, ManageResources {

    fun provideDispatchers(): DispatchersList

    class Base(
        context: Context,
        private val provideInstances: ProvideInstances
    ) : Core {

        private val manageResources: ManageResources = ManageResources.Base(context)

        private val dispatchersList by lazy {
            DispatchersList.Base()
        }
        private val cloudModule by lazy {
            provideInstances.provideCloudModule()
        }

        private val cacheModule by lazy {
            provideInstances.provideCacheModule()
        }

        override fun <T> service(clasz: Class<T>): T = cloudModule.service(clasz)

        override fun provideDataBase() = cacheModule.provideDataBase()

        override fun string(id: Int) = manageResources.string(id)

        override fun provideDispatchers() = dispatchersList

    }
}