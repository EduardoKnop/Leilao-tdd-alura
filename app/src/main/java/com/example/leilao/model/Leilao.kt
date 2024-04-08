package com.example.leilao.model

import java.io.Serializable

class Leilao (
    val descricao: String,
) : Serializable {

    private val lances = mutableListOf<Lance>()
    var maiorLance: Double? = null
        get() = if (lances.isEmpty()) null else lances.last().valor
        private set
    var menorLance: Double? = null
        get() = if (lances.isEmpty()) null else lances.first().valor
        private set

    fun newLance(lance: Lance): Boolean {
        if (lances.isEmpty()) {
            lances.add(lance)

            return true
        }

        if (lance.valor <= maiorLance!!) throw RuntimeException("Lance's Valor Cheaper than MaiorLance")
        if (lance.usuario == lances.last().usuario) throw RuntimeException("Lance's User was the Same that the Previously One")
        if (userHasMoreThanFiveLances(lance)) throw RuntimeException("User already did 5 Lances in this Leilao")

        lances.add(lance)

        return true
    }

    private fun userHasMoreThanFiveLances(lance: Lance): Boolean {
        var count = 0

        lances.forEach { l ->
            if (l.usuario == lance.usuario) {
                count++

                if (count >= 5) return true
            }
        }

        return false
    }

    fun getLances() : List<Lance> {

        return lances.reversed()
    }

}