package com.github.johnnysc.numberstesttask.numbers.domain

/**
 * @author Asatryan on 18.09.2022
 */
sealed class NumbersResult {

    interface Mapper<T> {
        fun map(list: List<NumberFact>): T
        fun map(errorMessage: String): T
    }

    abstract fun <T> map(mapper: Mapper<T>): T

    data class Success(private val list: List<NumberFact> = emptyList()) : NumbersResult() {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(list)
    }

    data class Failure(private val message: String) : NumbersResult() {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(message)
    }
}