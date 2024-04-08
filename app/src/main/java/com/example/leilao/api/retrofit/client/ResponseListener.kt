package com.example.leilao.api.retrofit.client

interface ResponseListener<T> {

    fun success(response: T?)

    fun error(message: String)
}