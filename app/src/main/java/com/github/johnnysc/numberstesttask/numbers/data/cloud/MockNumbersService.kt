package com.github.johnnysc.numberstesttask.numbers.data.cloud

/**
 * @author Asatryan on 01.10.2022
 */
class MockNumbersService(
    private val randomApiHeader: RandomApiHeader.MockResponse
) : NumbersService {

    private var count = 0

    override suspend fun random() = (++count).toString().let {
        randomApiHeader.makeResponse(fact(it), it)
    }

    override suspend fun fact(id: String) = "fact about $id"
}