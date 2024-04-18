package com.example.leilao.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.leilao.api.LanceSender
import com.example.leilao.api.retrofit.client.LeilaoWebClient
import com.example.leilao.database.dao.UserDAO
import com.example.leilao.databinding.ActivityLancesLeilaoBinding
import com.example.leilao.databinding.FormLanceBinding
import com.example.leilao.model.Lance
import com.example.leilao.model.Leilao
import com.example.leilao.ui.dialog.NewLanceDialog
import com.example.leilao.ui.dialog.WarningDialog
import java.io.Serializable

class LancesLeilaoActivity : AppCompatActivity() {

    lateinit var binding: ActivityLancesLeilaoBinding
    val client = LeilaoWebClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLancesLeilaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(LeilaoConstants.LEILAO_KEY)) {
            var leilaoReceived =
                getSerializable(this, LeilaoConstants.LEILAO_KEY, Leilao::class.java)

            showLeilaoData(leilaoReceived)

            val dao = UserDAO(this@LancesLeilaoActivity)
            binding.fabAddLance.setOnClickListener {
                if (dao.getAll().isEmpty()) {
                    val dialog = AlertDialog.Builder(this)
                        .setTitle("Usuários não encontrados")
                        .setMessage("Não existem usuários cadastrados! Cadastre um usuário para propor um lance.")
                        .setPositiveButton("OK") { _, _ ->
                            val intent = Intent(this, UserListActivity::class.java)
                            this.startActivity(intent)
                        }
                    dialog.show()
                } else {
                    NewLanceDialog(this,
                        FormLanceBinding.inflate(layoutInflater),
                        dao,
                        object : NewLanceDialog.LanceCreatedListener {
                            override fun lanceCreated(lance: Lance) {
                                val lanceSender = LanceSender(
                                    WarningDialog(this@LancesLeilaoActivity),
                                    client,
                                    object : LanceSender.LanceListener {
                                        override fun lanceReceived(leilao: Leilao) {
                                            leilaoReceived = leilao
                                            showLeilaoData(leilaoReceived)
                                        }
                                    })
                                lanceSender.sendLance(leilaoReceived, lance)
                            }
                        }).show()
                }
            }
        } else {
            finish()
        }
    }

    private fun showLeilaoData(
        leilao: Leilao
    ) {
        val listLances = if (leilao.getLances().size > 3) {
            leilao.getLances().subList(0, 3)
        } else {
            leilao.getLances()
        }

        var maioresLances = ""
        listLances.forEach {
            maioresLances += String.format("R$ %.2f\n", it.valor)
        }

        binding.lancesLeilaoDescricao.text = leilao.descricao
        binding.lancesLeilaoMaiorLance.text =
            String.format("R$ %.2f", leilao.maiorLance ?: 0.0)
        binding.lancesLeilaoMenorLance.text =
            String.format("R$ %.2f", leilao.menorLance ?: 0.0)
        binding.lancesLeilaoMaioresLances.text = maioresLances
    }

    fun <T : Serializable?> getSerializable(activity: Activity, name: String, clazz: Class<T>): T {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            activity.intent.getSerializableExtra(name, clazz)!!
        else
            activity.intent.getSerializableExtra(name) as T
    }

}