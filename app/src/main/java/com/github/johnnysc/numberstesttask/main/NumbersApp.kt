package com.github.johnnysc.numberstesttask.main

import android.app.Application
import com.github.johnnysc.numberstesttask.BuildConfig
import com.github.johnnysc.numberstesttask.numbers.data.CloudModule

/**
 * @author Asatryan on 26.09.2022
 */
class NumbersApp : Application() {

    override fun onCreate() {
        super.onCreate()

        //todo move out of here
        val cloudModule = if (BuildConfig.DEBUG)
            CloudModule.Debug()
        else
            CloudModule.Release()
    }
}