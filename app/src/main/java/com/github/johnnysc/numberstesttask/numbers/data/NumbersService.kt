package com.github.johnnysc.numberstesttask.numbers.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Asatryan on 26.09.2022
 */
interface NumbersService {

    @GET("{id}")
    suspend fun fact(@Path("id") id: String): String

    @GET("random/math")
    suspend fun random(): Response<String>
}