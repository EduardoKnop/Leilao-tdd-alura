package com.example.leilao.api.retrofit.service

import com.example.leilao.model.Lance
import com.example.leilao.model.Leilao
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface LeilaoService {

    @GET("leilao")
    fun getAll(): Call<List<Leilao>>

    @PUT("leilao/{id}/lance")
    fun putLance(@Path("id") id: Long, @Body lance: Lance): Call<Void>
}