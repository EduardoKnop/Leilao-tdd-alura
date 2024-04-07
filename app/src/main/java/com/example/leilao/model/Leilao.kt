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

    fun newLance(lance: Lance): Boolean {
        if (lances.isEmpty()) {
            lances.add(lance)
            maiorLance = lance.valor
            menorLance = lance.valor

            return true
        }

        if (lance.valor <= maiorLance!! || lance.usuario == lances.last().usuario) return false

        var countUserLances = 0
        lances.forEach { l ->
            if (l.usuario == lance.usuario) {
                countUserLances++

                if (countUserLances >= 5) return false
            }
        }

        lances.add(lance)
        maiorLance = lance.valor

        return true
    }

    fun getLancesSortedByValor() : List<Lance> {

        return lances.reversed()
    }

}