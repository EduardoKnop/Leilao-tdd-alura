package com.example.leilao.ui

import com.example.leilao.api.retrofit.client.LeilaoWebClient
import com.example.leilao.api.retrofit.client.ResponseListener
import com.example.leilao.model.Leilao
import com.example.leilao.ui.recyclerview.LeilaoAdapter

class LeilaoUpdate {

    fun updateLeilaoList(adapter: LeilaoAdapter,
                         client: LeilaoWebClient,
                         listener: StatusListener
    ) {
        client.getAll(object : ResponseListener<List<Leilao>> {
            override fun success(response: List<Leilao>?) {
                adapter.updateDataSet(response!!)
            }

            override fun error(message: String) {
                listener.onError(message)
            }
        })
    }

    interface StatusListener {

        fun onError(message: String)

    }
}