package com.github.johnnysc.numberstesttask.main.sl

import android.content.Context
import com.github.johnnysc.numberstesttask.numbers.data.cache.CacheModule
import com.github.johnnysc.numberstesttask.numbers.data.cloud.CloudModule
import com.github.johnnysc.numberstesttask.numbers.data.cloud.RandomApiHeader

/**
 * @author Asatryan on 01.10.2022
 */
interface ProvideInstances : ProvideRandomApiHeader {

    fun provideCloudModule(): CloudModule
    fun provideCacheModule(): CacheModule

    class Release(private val context: Context) : ProvideInstances {
        override fun provideCloudModule() = CloudModule.Base()
        override fun provideCacheModule() = CacheModule.Base(context)
        override fun provideRandomApiHeader() = RandomApiHeader.Base()
    }

    class Mock(private val context: Context) : ProvideInstances {
        override fun provideCloudModule() = CloudModule.Mock(provideRandomApiHeader())
        override fun provideCacheModule() = CacheModule.Mock(context)
        override fun provideRandomApiHeader() = RandomApiHeader.Mock()
    }
}

interface ProvideRandomApiHeader {
    fun provideRandomApiHeader(): RandomApiHeader.Combo
}