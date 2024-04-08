package com.example.leilao.api

import android.content.Context
import com.example.leilao.api.retrofit.client.LeilaoWebClient
import com.example.leilao.api.retrofit.client.ResponseListener
import com.example.leilao.model.Lance
import com.example.leilao.model.Leilao
import com.example.leilao.ui.dialog.WarningDialog

class LanceSender(private val context: Context,
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
                    WarningDialog(context).createFailedSendLance().show()
                }

            })
        } catch (e: Leilao.CheaperLanceException) {
            WarningDialog(context).createCheaperLance().show()
        } catch (e: Leilao.SameUserException) {
            WarningDialog(context).createConsecutiveLances().show()
        } catch (e: Leilao.MoreFiveLancesException) {
            WarningDialog(context).createFiveLances().show()
        }
    }

    interface LanceListener {

        fun lanceReceived(leilao: Leilao)

    }
}