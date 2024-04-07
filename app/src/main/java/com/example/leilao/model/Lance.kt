package com.example.leilao.model

import java.io.Serializable

class Lance (
    val usuario: Usuario,
    val valor: Double
) : Serializable, Comparable<Lance> {

    override fun compareTo(other: Lance): Int {

        return this.valor.compareTo(other.valor)
    }
}