package com.example.retrofitforecaster

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object HttpLoggingInterceptorClient {
    val client: OkHttpClient
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            interceptor.redactHeader("Authorization")
            interceptor.redactHeader("Cookie")
            return OkHttpClient.Builder().addInterceptor(interceptor).build()

        }
}
