package com.github.johnnysc.numberstesttask.numbers.presentation

/**
 * @author Asatryan on 18.09.2022
 */
interface Mapper<R, S> {

    fun map(source: S): R

    interface Unit<S> : Mapper<kotlin.Unit, S>
}