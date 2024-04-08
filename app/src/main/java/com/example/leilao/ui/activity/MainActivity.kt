package com.example.leilao.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leilao.databinding.ActivityMainBinding
import com.example.leilao.model.Lance
import com.example.leilao.model.Leilao
import com.example.leilao.model.Usuario
import com.example.leilao.ui.recyclerview.LeilaoAdapter

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
        console.newLance(Lance(Usuario("José"), 55.0))
        console.newLance(Lance(Usuario("Felipe"), 56.0))
        console.newLance(Lance(Usuario("Eduardo"), 220.50))
        console.newLance(Lance(Usuario("José"), 250.35))

        val carro = Leilao("Carro")
        carro.newLance(Lance(Usuario("Felipe"), 10000.0))

        list.add(console)
        list.add(carro)

        return list
    }

}