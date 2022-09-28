package com.github.johnnysc.numberstesttask.numbers.data.cache

import com.github.johnnysc.numberstesttask.numbers.data.NumberData

/**
 * @author Asatryan on 28.09.2022
 */
class NumberDataToCache : NumberData.Mapper<NumberCache> {
    override fun map(id: String, fact: String) = NumberCache(id, fact, System.currentTimeMillis())
}