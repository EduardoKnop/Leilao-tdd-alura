package com.example.leilao.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

private const val DELTA = 0.0001
private const val DESCRIPTION = "description-1"
private const val USER_1 = "user-1"
private const val USER_2 = "user-2"
private const val VALUE = 50.0

class LeilaoTest {

    private val bid1 = Leilao(DESCRIPTION)
    private val user1 = Usuario(USER_1)
    private val user2 = Usuario(USER_2)

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

        assertEquals(VALUE, bid1.maiorLance!!, DELTA)
    }

    @Test
    fun getMaiorLance_VariousLancesAscendingOrder_ReturnsMaiorLance() {
        bid1.newLance(Lance(user1, 10.1))
        bid1.newLance(Lance(user2, 20.0))
        bid1.newLance(Lance(user1, 50.6))
        bid1.newLance(Lance(user2, 390.3))
        bid1.newLance(Lance(user1, 5600.56))
        bid1.newLance(Lance(user2, 12_896.8))

        assertEquals(12_896.8, bid1.maiorLance!!, DELTA)
    }

    @Test
    fun getMenorLance_NoParameters_ReturnsNull() {

        assertNull(bid1.menorLance)
    }

    @Test
    fun getMenorLance_UniqueLance_ReturnsValue() {
        bid1.newLance(Lance(user1, VALUE))

        assertEquals(VALUE, bid1.menorLance!!, DELTA)
    }

    @Test
    fun getMenorLance_VariousLancesAscendingOrder_ReturnsMenorLance() {
        bid1.newLance(Lance(user1, 10.1))
        bid1.newLance(Lance(user2, 20.0))
        bid1.newLance(Lance(user1, 50.6))
        bid1.newLance(Lance(user2, 390.3))
        bid1.newLance(Lance(user1, 5600.56))
        bid1.newLance(Lance(user2, 12_896.8))

        assertEquals(10.1, bid1.menorLance!!, DELTA)
    }

    @Test
    fun newLance_LancesCheaperOrEqualsThanMaiorLance_ShouldNotBeAccepted() {
        bid1.newLance(Lance(user1, 500.0))

        var e: Exception
        e = assertThrows(Leilao.CheaperLanceException::class.java) { bid1.newLance(Lance(user1, 400.0)) }
        assertEquals("Lance's Valor Cheaper than MaiorLance", e.message)
        e = assertThrows(Leilao.CheaperLanceException::class.java) { bid1.newLance(Lance(user2, 450.0)) }
        assertEquals("Lance's Valor Cheaper than MaiorLance", e.message)
        e = assertThrows(Leilao.CheaperLanceException::class.java) { bid1.newLance(Lance(user1, 100.0)) }
        assertEquals("Lance's Valor Cheaper than MaiorLance", e.message)
        e = assertThrows(Leilao.CheaperLanceException::class.java) { bid1.newLance(Lance(user2, 500.0)) }
        assertEquals("Lance's Valor Cheaper than MaiorLance", e.message)
        assertEquals(1, bid1.getLances().size)
    }

    @Test
    fun newLance_SameUserCreateTwoOrMoreConsecutiveLances_ShouldNotBeAccepted() {
        bid1.newLance(Lance(user1, 500.0))

        var e: Exception
        e = assertThrows(Leilao.SameUserException::class.java) {
            bid1.newLance(
                Lance(
                    Usuario("user-1"),
                    600.0
                )
            )
        }
        assertEquals("Lance's User was the Same that the Previously One", e.message)
        e = assertThrows(Leilao.SameUserException::class.java) {
            bid1.newLance(
                Lance(
                    Usuario("user-1"),
                    700.0
                )
            )
        }
        assertEquals("Lance's User was the Same that the Previously One", e.message)
        assertEquals(1, bid1.getLances().size)
    }

    @Test
    @DisplayName("If a User create more than Five Lances, Throws a RuntimeException")
    fun newLance_SameUserCreateMoreThanFiveLances_ShouldNotBeAccepted() {
        bid1.newLance(Lance(user1, 100.0))
        bid1.newLance(Lance(user2, 150.0))
        bid1.newLance(Lance(user1, 200.0))
        bid1.newLance(Lance(user2, 250.0))
        bid1.newLance(Lance(user1, 300.0))
        bid1.newLance(Lance(user2, 350.0))
        bid1.newLance(Lance(user1, 400.0))
        bid1.newLance(Lance(user2, 450.0))
        bid1.newLance(Lance(user1, 500.0))
        bid1.newLance(Lance(user2, 550.0))

        val e = assertThrows(Leilao.MoreFiveLancesException::class.java) { bid1.newLance(Lance(user1, 600.0)) }
        assertEquals("User already did 5 Lances in this Leilao", e.message)
        assertEquals(10, bid1.getLances().size)
        assertEquals(550.0, bid1.maiorLance!!, DELTA)
    }

    @Test
    fun verifyUpdateMaiorLance() {

    }

    @Test
    fun verifyUpdateMenorLance() {

    }

    @Test
    fun getLances_ReceivesNoLances_ReturnsEmptyList() {

        assertEquals(0, bid1.getLances().size)
    }

    @Test
    fun getLances_ReceivesVariousLances_ReturnsOrderedList() {
        val lance1 = Lance(user1, 5600.56)
        val lance2 = Lance(user2, 456.98)
        val lance3 = Lance(user1, 431.4)
        val lance4 = Lance(user2, 390.3)
        val lance5 = Lance(user1, 250.7)
        val lance6 = Lance(user2, 50.6)

        val listOrdered = mutableListOf(
            lance1, lance2, lance3, lance4, lance5, lance6
        )

        bid1.newLance(lance6)
        bid1.newLance(lance5)
        bid1.newLance(lance4)
        bid1.newLance(lance3)
        bid1.newLance(lance2)
        bid1.newLance(lance1)

        assertEquals(listOrdered, bid1.getLances())
        assertEquals(bid1.maiorLance!!, bid1.getLances().first().valor, DELTA)
        assertEquals(bid1.menorLance!!, bid1.getLances().last().valor, DELTA)
    }

    @Test
    fun getDescricao_ReceivesDescription_ReturnsDescription() {

        assertEquals(DESCRIPTION, bid1.descricao)
    }
}