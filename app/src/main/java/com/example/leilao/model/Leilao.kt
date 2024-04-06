package com.example.leilao.model

import java.io.Serializable

class Leilao (
    val descricao: String,
) : Serializable {

    private lateinit var lances: List<Lance>

}