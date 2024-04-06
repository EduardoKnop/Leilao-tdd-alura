package com.example.leilao.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leilao.R
import com.example.leilao.databinding.ActivityMainBinding
import com.example.leilao.model.Leilao
import com.example.leilao.ui.recyclerview.LeilaoAdapter
import java.util.Arrays

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listaLeilaoRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.listaLeilaoRecyclerview.setHasFixedSize(true)
        val leilaoAdapter = LeilaoAdapter(leiloesDeExemplo())

        binding.listaLeilaoRecyclerview.adapter = leilaoAdapter

        leilaoAdapter.setOnClickListener(object :
            LeilaoAdapter.OnClickListener {
            override fun onClick(position: Int, model: Leilao) {
                val intent = Intent(this@MainActivity, LancesLeilaoActivity::class.java)
                intent.putExtra("leilao", model)
                startActivity(intent)
            }
        })
    }

    private fun leiloesDeExemplo(): List<Leilao> {
        val list = ArrayList<Leilao>()
        val console = Leilao("Console")

        list.add(console)

        return list
    }

}