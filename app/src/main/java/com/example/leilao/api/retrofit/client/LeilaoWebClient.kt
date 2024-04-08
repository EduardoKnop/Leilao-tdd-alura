package com.example.leilao.api.retrofit.client

import com.example.leilao.api.retrofit.RetrofitInitializer
import com.example.leilao.model.Lance
import com.example.leilao.model.Leilao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeilaoWebClient {

    private var service = RetrofitInitializer().getLeilaoService()

    fun getAll(listener: ResponseListener<List<Leilao>>) {
        val call = service.getAll()
        call.enqueue(object : Callback<List<Leilao>> {
            override fun onResponse(call: Call<List<Leilao>>,
                                    response: Response<List<Leilao>>
            ) {
                if (response.isSuccessful && response.body() != null)
                    listener.success(response.body()!!)
            }

            override fun onFailure(call: Call<List<Leilao>>, t: Throwable) {
                listener.error(t.message ?: "")
            }

        })
    }

    fun putLance(lance: Lance, id: Long, listener: ResponseListener<Void>) {
        val call = service.putLance(id, lance)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful)
                    listener.success(null)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                error(t.message ?: "")
            }
        })
    }
}