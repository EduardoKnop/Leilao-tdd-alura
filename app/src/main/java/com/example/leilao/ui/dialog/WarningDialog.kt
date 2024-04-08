package com.example.leilao.ui.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog

private const val POSITIVE_BUTTON = "Ok"
private const val MESSAGE_MORE_FIVE_LANCES = "Usuário não pode dar mais de cinco lances no mesmo leilão"
private const val MESSAGE_TWO_CONSECUTIVE_LANCES = "O mesmo usuário do último lance não pode propror novos lances"
private const val MESSAGE_CHEAPER_LANCE = "Lance precisa ser maior que o último lance"
private const val MESSAGE_FAILED_SEND_LANCE = "Não foi possível enviar o Lance"
private const val MESSAGE_INVALID_VALUE = "Valor Inválido"

class WarningDialog(context: Context) : AlertDialog.Builder(context) {

    fun createFiveLances(): AlertDialog {
        return AlertDialog.Builder(context)
            .setMessage(MESSAGE_MORE_FIVE_LANCES)
            .setPositiveButton(POSITIVE_BUTTON, null)
            .show()
    }

    fun createConsecutiveLances(): AlertDialog {
        return AlertDialog.Builder(context)
            .setMessage(MESSAGE_TWO_CONSECUTIVE_LANCES)
            .setPositiveButton(POSITIVE_BUTTON, null)
            .show()
    }

    fun createCheaperLance(): AlertDialog {
        return AlertDialog.Builder(context)
            .setMessage(MESSAGE_CHEAPER_LANCE)
            .setPositiveButton(POSITIVE_BUTTON, null)
            .show()
    }

    fun createFailedSendLance(): AlertDialog {
        return AlertDialog.Builder(context)
            .setMessage(MESSAGE_FAILED_SEND_LANCE)
            .setPositiveButton(POSITIVE_BUTTON, null)
            .show()
    }

    fun createInvalidValue(): AlertDialog {
        return AlertDialog.Builder(context)
            .setMessage(MESSAGE_INVALID_VALUE)
            .setPositiveButton(POSITIVE_BUTTON, null)
            .show()
    }

}