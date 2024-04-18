package com.example.leilao.api

import android.content.Context
import com.example.leilao.api.retrofit.client.LeilaoWebClient
import com.example.leilao.api.retrofit.client.ResponseListener
import com.example.leilao.model.Lance
import com.example.leilao.model.Leilao
import com.example.leilao.ui.dialog.WarningDialog

class LanceSender(private val dialog: WarningDialog,
                  private val client: LeilaoWebClient,
                  private val listener: LanceListener
) {

    fun sendLance(leilao: Leilao, lance: Lance) {
        try {
            leilao.newLance(lance)
            client.putLance(lance, leilao.id, object : ResponseListener<Void> {
                override fun success(response: Void?) {
                    listener.lanceReceived(leilao)
                }

                override fun error(message: String) {
                    dialog.createFailedSendLance().show()
                }

            })
        } catch (e: Leilao.CheaperLanceException) {
            dialog.createCheaperLance().show()
        } catch (e: Leilao.SameUserException) {
            dialog.createConsecutiveLances().show()
        } catch (e: Leilao.MoreFiveLancesException) {
            dialog.createFiveLances().show()
        }
    }

    interface LanceListener {

        fun lanceReceived(leilao: Leilao)

    }
}