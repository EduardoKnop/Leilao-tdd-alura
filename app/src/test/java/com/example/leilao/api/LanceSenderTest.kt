package com.example.leilao.api

import android.annotation.SuppressLint
import com.example.leilao.api.retrofit.client.LeilaoWebClient
import com.example.leilao.model.Lance
import com.example.leilao.model.Leilao
import com.example.leilao.model.Usuario
import com.example.leilao.ui.dialog.WarningDialog
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class LanceSenderTest {

    @Mock
    lateinit var dialog: WarningDialog
    @Mock
    lateinit var client: LeilaoWebClient
    @Mock
    lateinit var listener: LanceSender.LanceListener
    @Mock
    lateinit var leilao: Leilao

    @Test
    fun sendLance_LancesCheaperOrEqualsThanMaiorLance_ReturnsErrorMessage() {
        val lanceSender = LanceSender(dialog, client, listener)

        Mockito.doThrow(Leilao.CheaperLanceException::class.java)
            .`when`(leilao).newLance(anyObject())
        Mockito.doReturn(Mockito.any(WarningDialog::class.java)).`when`(dialog).createCheaperLance()

        lanceSender.sendLance(leilao, Lance(Usuario("Pedro"), 1.5))

        Mockito.verify(dialog).createCheaperLance()
        Mockito.verify(client, Mockito.never())
            .putLance(anyObject(), Mockito.anyLong(), anyObject())
    }

    @SuppressLint("CheckResult")
    private fun <T> anyObject(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T
}