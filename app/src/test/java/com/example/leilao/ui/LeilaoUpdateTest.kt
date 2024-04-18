package com.example.leilao.ui

import android.annotation.SuppressLint
import com.example.leilao.api.retrofit.client.LeilaoWebClient
import com.example.leilao.api.retrofit.client.ResponseListener
import com.example.leilao.model.Leilao
import com.example.leilao.ui.recyclerview.LeilaoAdapter

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class LeilaoUpdateTest {

    @Mock
    lateinit var adapter: LeilaoAdapter
    @Mock
    lateinit var client: LeilaoWebClient
    @Mock
    lateinit var listener: LeilaoUpdate.StatusListener

    @Test
    fun updateLeilaoList_ReceivesLeiloes_UpdatesList() {
        val leilaoUpdate = LeilaoUpdate()
        val leiloes = arrayListOf(Leilao("Carro"), Leilao("Moto"))

        Mockito.doAnswer {
            (it.arguments[0] as ResponseListener<List<Leilao>>).success(leiloes)

            null
        }.`when`(client).getAll(anyObject())

        leilaoUpdate.updateLeilaoList(adapter, client, listener)

        Mockito.verify(client).getAll(anyObject())
        Mockito.verify(adapter).updateDataSet(leiloes)
    }

    @Test
    fun updateLeilaoList_ReceivesLeiloes_FailsUpdateList() {
        val leilaoUpdate = LeilaoUpdate()

        Mockito.doAnswer {
            (it.arguments[0] as ResponseListener<List<Leilao>>).error(Mockito.anyString())

            null
        }.`when`(client).getAll(anyObject())

        leilaoUpdate.updateLeilaoList(adapter, client, listener)

        Mockito.verify(client).getAll(anyObject())
    }

    @SuppressLint("CheckResult")
    private fun <T> anyObject(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

}