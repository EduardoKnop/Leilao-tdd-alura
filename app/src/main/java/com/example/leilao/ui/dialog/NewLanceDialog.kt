package com.example.leilao.ui.dialog

import android.content.Context
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.example.leilao.database.dao.UserDAO
import com.example.leilao.databinding.FormLanceBinding
import com.example.leilao.model.Lance
import com.example.leilao.model.Usuario

private const val TITLE = "Novo lance"
private const val POSITIVE_BUTTON = "Propor"
private const val NEGATIVE_BUTTON = "Cancelar"

class NewLanceDialog(
    private val context: Context,
    private val binding: FormLanceBinding,
    private val userDAO: UserDAO,
    private val listener: LanceCreatedListener
) : AlertDialog.Builder(context) {

    override fun create(): AlertDialog {
        super.create()

        val adapter = ArrayAdapter(context,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            userDAO.getAll()
        )
        binding.formLanceUsuario.adapter = adapter

        val dialog = AlertDialog.Builder(context)
            .setTitle(TITLE)
            .setView(binding.root)
            .setPositiveButton(POSITIVE_BUTTON) { _, _ ->
                val value = binding.formLanceValor.editText!!.text.toString()
                val user = (binding.formLanceUsuario.selectedItem) as Usuario

                try {
                    listener.lanceCreated(Lance(user, value.toDouble()))
                } catch (e: NumberFormatException) {
                    WarningDialog(context).createInvalidValue()
                }
            }
        dialog.setNegativeButton(NEGATIVE_BUTTON, null)

        return dialog.show()
    }

    interface LanceCreatedListener {

        fun lanceCreated(lance: Lance)
    }
}