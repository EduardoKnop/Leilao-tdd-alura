package com.example.leilao.model

import java.io.Serializable

class Usuario (
    val id: Long,
    val nome: String
) : Serializable {

    constructor(nome: String) : this(0L, nome)

    override fun toString(): String {
        return "$id - $nome"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        if (id != other.id) return false
        if (nome != other.nome) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + nome.hashCode()
        return result
    }

}