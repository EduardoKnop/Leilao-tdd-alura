package com.example.leilao.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leilao.databinding.ItemUserBinding
import com.example.leilao.model.Usuario

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val users = arrayListOf<Usuario>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater
            .from(parent.context), parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]

        holder.tvName.text = String.format("%2d - %s", user.id, user.nome)
    }

    fun add(users: List<Usuario>) {
        users.forEach {
            add(it)
        }
    }

    fun add(user: Usuario) {
        users.add(user)
        notifyItemInserted(users.indexOf(user))
    }

    class ViewHolder(view: ItemUserBinding) : RecyclerView.ViewHolder(view.root) {
        val tvName = view.tvName
    }
}