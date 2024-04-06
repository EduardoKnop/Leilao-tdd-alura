package com.example.leilao.ui.activity

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.leilao.R
import com.example.leilao.databinding.ActivityLancesLeilaoBinding
import com.example.leilao.model.Leilao
import java.io.Serializable

class LancesLeilaoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLancesLeilaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("leilao")) {
            val leilao = getSerializable(this, "leilao", Leilao::class.java)
            binding.lancesLeilaoDescricao.text = leilao.descricao
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