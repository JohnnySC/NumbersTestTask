package com.github.johnnysc.numberstesttask.numbers.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Asatryan on 26.09.2022
 */
interface CloudModule {

    fun <T> service(clasz: Class<T>): T

    abstract class Abstract : CloudModule {

        protected abstract val level: HttpLoggingInterceptor.Level
        protected open val baseUrl: String = "http://numbersapi.com/"

        override fun <T> service(clasz: Class<T>): T {
            //todo refactor when service locator
            val interceptor = HttpLoggingInterceptor().apply {
                setLevel(level)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build()
            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
            return retrofit.create(clasz)
        }
    }

    class Debug : Abstract() {
        override val level = HttpLoggingInterceptor.Level.BODY
    }

    class Release : Abstract() {
        override val level = HttpLoggingInterceptor.Level.NONE
    }
}