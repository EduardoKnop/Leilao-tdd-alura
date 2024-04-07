package com.example.leilao.ui.activity

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.leilao.R
import com.example.leilao.databinding.ActivityLancesLeilaoBinding
import com.example.leilao.model.Lance
import com.example.leilao.model.Leilao
import java.io.Serializable
import java.util.Arrays

class LancesLeilaoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLancesLeilaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("leilao")) {
            val leilao = getSerializable(this, "leilao", Leilao::class.java)

            val listLances = if (leilao.lances.size > 3) {
                leilao.getLancesSortedByValor().subList(0, 3)
            } else {
                leilao.getLancesSortedByValor()
            }

            var maioresLances = ""
            listLances.forEach {
                maioresLances += String.format("R$ %.2f\n", it.valor)
            }

            binding.lancesLeilaoDescricao.text = leilao.descricao
            binding.lancesLeilaoMaiorLance.text = String.format("R$ %.2f", leilao.maiorLance ?: 0.0)
            binding.lancesLeilaoMenorLance.text = String.format("R$ %.2f", leilao.menorLance ?: 0.0)
            binding.lancesLeilaoMaioresLances.text = maioresLances
        }
    }

    fun <T : Serializable?> getSerializable(activity: Activity, name: String, clazz: Class<T>): T
    {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            activity.intent.getSerializableExtra(name, clazz)!!
        else
            activity.intent.getSerializableExtra(name) as T
    }

}