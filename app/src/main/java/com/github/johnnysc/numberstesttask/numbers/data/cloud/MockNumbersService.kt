package com.github.johnnysc.numberstesttask.numbers.data.cloud

import okhttp3.Headers.Companion.toHeaders
import retrofit2.Response

/**
 * @author Asatryan on 01.10.2022
 */
class MockNumbersService : NumbersService {

    private var count = 0

    override suspend fun fact(id: String) = "fact about $id"

    override suspend fun random(): Response<String> {
        count++
        return Response.success(
            "fact about $count",
            mapOf("X-Numbers-API-Number" to count.toString()).toHeaders()
        )
    }
}