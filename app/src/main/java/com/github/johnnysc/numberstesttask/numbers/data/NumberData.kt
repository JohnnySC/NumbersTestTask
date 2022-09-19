package com.github.johnnysc.numberstesttask.numbers.data

/**
 * @author Asatryan on 19.09.2022
 */
data class NumberData(
    private val id: String,
    private val fact: String
) {

    interface Mapper<T> {
        fun map(id: String, fact: String): T

        class Matches(private val id: String) : Mapper<Boolean> {
            override fun map(id: String, fact: String) = this.id == id
        }
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(id, fact)
}