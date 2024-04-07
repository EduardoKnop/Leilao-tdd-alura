package com.example.leilao.model

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class LeilaoTest {

    private val DESCRIPTION = "description-1"
    private val USER_1 = "user-1"
    private val VALUE = 50.0

    private val bid1 = Leilao(DESCRIPTION)
    private val user1 = Usuario(USER_1)

    @Test
    fun getLances() {
    }

    @Test
    fun getMaiorLance_NoParameters_ReturnsNull() {

        assertNull(bid1.maiorLance)
    }

    @Test
    fun getMaiorLance_UniqueLance_ReturnsValue() {
        bid1.newLance(Lance(user1, VALUE))

        assertEquals(VALUE, bid1.maiorLance!!, 0.0001)
    }

    @Test
    fun getMaiorLance_VariousLancesAscendingOrder_ReturnsMaiorLance() {
        bid1.newLance(Lance(user1, 10.1))
        bid1.newLance(Lance(user1, 20.0))
        bid1.newLance(Lance(user1, 50.6))
        bid1.newLance(Lance(user1, 390.3))
        bid1.newLance(Lance(user1, 5600.56))
        bid1.newLance(Lance(user1, 12_896.8))

        assertEquals(12_896.8, bid1.maiorLance!!, 0.0001)
    }

    @Test
    fun getMaiorLance_VariousLancesDescendingOrder_ReturnsMaiorLance() {
        bid1.newLance(Lance(user1, 456.98))
        bid1.newLance(Lance(user1, 431.4))
        bid1.newLance(Lance(user1, 399.99))
        bid1.newLance(Lance(user1, 250.7))
        bid1.newLance(Lance(user1, 121.8))
        bid1.newLance(Lance(user1, 34.8))

        assertEquals(456.98, bid1.maiorLance!!, 0.0001)
    }

    @Test
    fun getMenorLance_NoParameters_ReturnsNull() {

        assertNull(bid1.menorLance)
    }

    @Test
    fun getMenorLance_UniqueLance_ReturnsValue() {
        bid1.newLance(Lance(user1, VALUE))

        assertEquals(VALUE, bid1.menorLance!!, 0.0001)
    }

    @Test
    fun getMenorLance_VariousLancesAscendingOrder_ReturnsMenorLance() {
        bid1.newLance(Lance(user1, 10.1))
        bid1.newLance(Lance(user1, 20.0))
        bid1.newLance(Lance(user1, 50.6))
        bid1.newLance(Lance(user1, 390.3))
        bid1.newLance(Lance(user1, 5600.56))
        bid1.newLance(Lance(user1, 12_896.8))

        assertEquals(10.1, bid1.menorLance!!, 0.0001)
    }

    @Test
    fun getMenorLance_VariousLancesDescendingOrder_ReturnsMenorLance() {
        bid1.newLance(Lance(user1, 456.98))
        bid1.newLance(Lance(user1, 431.4))
        bid1.newLance(Lance(user1, 399.99))
        bid1.newLance(Lance(user1, 250.7))
        bid1.newLance(Lance(user1, 121.8))
        bid1.newLance(Lance(user1, 34.8))

        assertEquals(34.8, bid1.menorLance!!, 0.0001)
    }

    @Test
    fun newLance() {
    }

    @Test
    fun verifyUpdateMaiorLance() {

    }

    @Test
    fun verifyUpdateMenorLance() {

    }

    @Test
    fun getLancesSortedByValor() {
    }

    @Test
    fun getDescricao_ReceivesDescription_ReturnsDescription() {

        assertEquals(DESCRIPTION, bid1.descricao)
    }
}