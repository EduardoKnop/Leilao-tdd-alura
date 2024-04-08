package com.example.leilao.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leilao.database.dao.UserDAO
import com.example.leilao.databinding.ActivityUserListBinding
import com.example.leilao.databinding.FormUserBinding
import com.example.leilao.model.Usuario
import com.example.leilao.ui.UserUpdate
import com.example.leilao.ui.dialog.NewUserDialog
import com.example.leilao.ui.recyclerview.UserAdapter

class UserListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserListBinding
    private lateinit var dao: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dao = UserDAO(this)
        val adapter = UserAdapter()

        binding.rvUserList.adapter = adapter
        adapter.add(dao.getAll())

        binding.fabAddUser.setOnClickListener {
            val dialog = NewUserDialog(this,
                FormUserBinding.inflate(layoutInflater),
                object : NewUserDialog.UserCreatedListener {
                override fun userCreated(user: Usuario) {
                    UserUpdate(dao, adapter, binding.rvUserList).update(user)
                }
            })
            dialog.show()
        }
    }
}