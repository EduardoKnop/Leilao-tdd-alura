package com.example.leilao.model

import java.io.Serializable

class Leilao (
    val descricao: String,
) : Serializable {

    private var lances: List<Lance> = ArrayList()
    private var maiorLance: Double? = null

    fun newLance(lance: Lance) {
        lances += lance

        if (maiorLance == null || lance.valor >= maiorLance!!) {
            maiorLance = lance.valor
        }
    }

    fun getMaiorLance(): Double? {

        return maiorLance
    }

}