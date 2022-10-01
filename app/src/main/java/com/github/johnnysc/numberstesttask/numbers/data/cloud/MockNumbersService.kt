package com.github.johnnysc.numberstesttask.numbers.data.cloud

import retrofit2.Response

/**
 * @author Asatryan on 01.10.2022
 */
class MockNumbersService : NumbersService {

    override suspend fun fact(id: String) = "fact about $id"

    override suspend fun random(): Response<String> {
        TODO()
    }
}