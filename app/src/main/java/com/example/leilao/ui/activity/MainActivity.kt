package com.example.leilao.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leilao.R
import com.example.leilao.api.retrofit.client.LeilaoWebClient
import com.example.leilao.api.retrofit.client.ResponseListener
import com.example.leilao.databinding.ActivityMainBinding
import com.example.leilao.model.Leilao
import com.example.leilao.ui.LeilaoUpdate
import com.example.leilao.ui.recyclerview.LeilaoAdapter

private const val MESSAGE_FAILED_LOAD_LEILOES = "Não foi possível carregar os leilões"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var leilaoAdapter: LeilaoAdapter
    private val client = LeilaoWebClient()
    private val leilaoUpdate = LeilaoUpdate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        leilaoAdapter = LeilaoAdapter()

        binding.listaLeilaoRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.listaLeilaoRecyclerview.adapter = leilaoAdapter

        leilaoAdapter.setOnClickListener(object :
            LeilaoAdapter.OnClickListener {
            override fun onClick(position: Int, model: Leilao) {
                val intent = Intent(this@MainActivity, LancesLeilaoActivity::class.java)
                intent.putExtra(LeilaoConstants.LEILAO_KEY, model)
                startActivity(intent)
            }
        })
    }

    override fun onResume() {
        super.onResume()

        leilaoUpdate.updateLeilaoList(leilaoAdapter, client, object : LeilaoUpdate.StatusListener {
            override fun onError(message: String) {
                Toast.makeText(
                    this@MainActivity, MESSAGE_FAILED_LOAD_LEILOES, Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId

        if (itemId == R.id.lista_leilao_menu_usuarios) {
            val intent = Intent(this@MainActivity, UserListActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}