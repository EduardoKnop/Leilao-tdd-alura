package com.example.leilao.model

import java.io.Serializable

class Leilao(
    val descricao: String,
) : Serializable {

    val id = 0L
    private val lances = mutableListOf<Lance>()

    var maiorLance: Double? = null
        get() = if (lances.isEmpty()) null else getLances().first().valor
        private set
    var menorLance: Double? = null
        get() = if (lances.isEmpty()) null else getLances().last().valor
        private set

    fun newLance(lance: Lance) {
        if (lances.isEmpty()) {
            lances.add(lance)

            return
        }

        if (lance.valor <= maiorLance!!)
            throw CheaperLanceException("Lance's Valor Cheaper than MaiorLance")

        if (lance.usuario == lances.last().usuario)
            throw SameUserException("Lance's User was the Same that the Previously One")

        if (userHasMoreThanFiveLances(lance))
            throw MoreFiveLancesException("User already did 5 Lances in this Leilao")

        lances.add(lance)
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

    fun getLances(): List<Lance> {

        return lances.sorted().reversed()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Leilao

        if (descricao != other.descricao) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = descricao.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }

    inner class CheaperLanceException(message: String) : RuntimeException(message)

    inner class SameUserException(message: String) : RuntimeException(message)

    inner class MoreFiveLancesException(message: String) : RuntimeException(message)

}