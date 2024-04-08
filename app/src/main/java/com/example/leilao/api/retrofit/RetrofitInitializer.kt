package com.example.leilao.api.retrofit

import com.example.leilao.api.retrofit.service.LeilaoService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL_BASE = "http://192.168.193.79:8080/"
class RetrofitInitializer {

    private var retrofit: Retrofit

    init {
        val client = getOkHttp()
        retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getOkHttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    fun getLeilaoService(): LeilaoService {
        return retrofit.create(LeilaoService::class.java)
    }
}