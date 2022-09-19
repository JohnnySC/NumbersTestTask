package com.github.johnnysc.numberstesttask.numbers.data

import com.github.johnnysc.numberstesttask.numbers.domain.HandleError
import com.github.johnnysc.numberstesttask.numbers.domain.NoInternetConnectionException
import com.github.johnnysc.numberstesttask.numbers.domain.ServiceUnavailableException
import java.net.UnknownHostException

/**
 * @author Asatryan on 19.09.2022
 */
class HandleDomainError : HandleError<Exception> {

    override fun handle(e: Exception) = when (e) {
        is UnknownHostException -> NoInternetConnectionException()
        else -> ServiceUnavailableException()
    }
}