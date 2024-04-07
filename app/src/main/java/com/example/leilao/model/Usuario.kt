package com.example.leilao.model

import java.io.Serializable

class Usuario (
    val nome: String
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        return nome == other.nome
    }

    override fun hashCode(): Int {
        return nome.hashCode()
    }
}