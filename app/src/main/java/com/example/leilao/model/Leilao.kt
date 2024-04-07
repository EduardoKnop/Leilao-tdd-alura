package com.example.leilao.model

import java.io.Serializable

class Leilao (
    val descricao: String,
) : Serializable {

    val lances = mutableListOf<Lance>()
    var maiorLance: Double? = null
        private set
    var menorLance: Double? = null
        private set

    fun newLance(lance: Lance) {
        lances.add(lance)

        verifyUpdateMaiorLance(lance)
        verifyUpdateMenorLance(lance)
    }

    private fun verifyUpdateMaiorLance(lance: Lance) {
        if (maiorLance == null || lance.valor >= maiorLance!!) {
            maiorLance = lance.valor
        }
    }

    private fun verifyUpdateMenorLance(lance: Lance) {
        if (menorLance == null || lance.valor <= menorLance!!) {
            menorLance = lance.valor
        }
    }

    fun getLancesSortedByValor() : List<Lance> {

        return lances.sortedDescending()
    }

}