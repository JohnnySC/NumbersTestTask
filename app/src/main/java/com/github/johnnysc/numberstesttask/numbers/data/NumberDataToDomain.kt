package com.github.johnnysc.numberstesttask.numbers.data

import com.github.johnnysc.numberstesttask.numbers.domain.NumberFact

/**
 * @author Asatryan on 19.09.2022
 */
class NumberDataToDomain : NumberData.Mapper<NumberFact> {
    override fun map(id: String, fact: String) = NumberFact(id, fact)
}