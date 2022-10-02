package com.github.johnnysc.numberstesttask.numbers.data.cloud

import okhttp3.Headers.Companion.toHeaders
import retrofit2.Response

interface RandomApiHeader {

    fun findInHeaders(headers: okhttp3.Headers): Pair<String, String>?

    interface MockResponse {
        fun makeResponse(body: String, headerValue: String): Response<String>
    }

    interface Combo : RandomApiHeader, MockResponse

    abstract class Abstract(private val header: String) : Combo {

        override fun findInHeaders(headers: okhttp3.Headers): Pair<String, String>? =
            headers.find { (key, _) -> key == header }

        override fun makeResponse(body: String, headerValue: String): Response<String> =
            Response.success(body, mapOf(header to headerValue).toHeaders())
    }

    class Base : Abstract("X-Numbers-API-Number")
    class Mock(value: String = "X-Numbers-API-Number-Mocked") : Abstract(value)
}