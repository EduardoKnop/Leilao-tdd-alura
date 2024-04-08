package com.example.leilao.ui.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.leilao.databinding.FormUserBinding
import com.example.leilao.model.Usuario


private const val TITLE = "Novo Usuário"
private const val POSITIVE_BUTTON = "Adicionar Usuário"

class NewUserDialog(
    private val context: Context,
    private val binding: FormUserBinding,
    private val listener: UserCreatedListener
) : AlertDialog.Builder(context) {

    override fun create(): AlertDialog {
        super.create()

        val dialog = AlertDialog.Builder(context)
            .setTitle(TITLE)
            .setView(binding.root)
            .setPositiveButton(POSITIVE_BUTTON) { _, _ ->
                val name = binding.formUsuarioNome.editText!!.text.toString()
                val user = Usuario(name)
                listener.userCreated(user)
            }

        return dialog.show()
    }

    interface UserCreatedListener {

        fun userCreated(user: Usuario)
    }

}