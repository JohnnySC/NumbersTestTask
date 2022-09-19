package com.github.johnnysc.numberstesttask.numbers.domain

import com.github.johnnysc.numberstesttask.R
import com.github.johnnysc.numberstesttask.numbers.presentation.ManageResources

/**
 * @author Asatryan on 19.09.2022
 */
interface HandleError<T> {

    fun handle(e: Exception): T

    class Base(private val manageResources: ManageResources) : HandleError<String> {

        override fun handle(e: Exception) = manageResources.string(
            when (e) {
                is NoInternetConnectionException -> R.string.no_connection_message
                else -> R.string.service_is_unavailable
            }
        )
    }
}