package com.example.leilao.ui.recyclerview

import com.example.leilao.model.Leilao
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class LeilaoAdapterTest {

    @Spy
    private val adapter = LeilaoAdapter()

    @Test
    fun updateDataSet_SendList_UpdatesAdapter() {
        Mockito.doNothing().`when`(adapter).notifyDataSetChanged()

        adapter.updateDataSet(listOf(
            Leilao("PS4"),
            Leilao("Carro")
        ))

        Mockito.verify(adapter).notifyDataSetChanged()
        assertEquals(2, adapter.itemCount)
    }

}