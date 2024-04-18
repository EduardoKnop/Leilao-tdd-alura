package com.example.leilao.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.leilao.database.dao.UserDAO
import com.example.leilao.model.Usuario
import com.example.leilao.ui.recyclerview.UserAdapter

class UserUpdate(private val dao: UserDAO,
                 private val adapter: UserAdapter,
                 private val recyclerView: RecyclerView
) {

    fun update(user: Usuario) {
        adapter.add(dao.insert(user))
        recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
    }
}