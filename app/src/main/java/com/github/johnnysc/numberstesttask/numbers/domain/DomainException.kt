package com.github.johnnysc.numberstesttask.numbers.domain

/**
 * @author Asatryan on 19.09.2022
 */
abstract class DomainException : IllegalStateException()

class NoInternetConnectionException : DomainException()

class ServiceUnavailableException : DomainException()