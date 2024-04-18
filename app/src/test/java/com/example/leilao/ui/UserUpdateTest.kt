package com.example.leilao.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.leilao.database.dao.UserDAO
import com.example.leilao.model.Usuario
import com.example.leilao.ui.recyclerview.UserAdapter
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class UserUpdateTest {

    @Mock
    lateinit var context: Context
    @Mock
    lateinit var dao: UserDAO
    @Mock
    lateinit var adapter: UserAdapter
    @Mock
    lateinit var recyclerView: RecyclerView

    @Test
    fun update_ReceivesNewUser_UpdatesAdapter() {
        val userUpdate = UserUpdate(dao, adapter, recyclerView)
        val user = Usuario("User")

        Mockito.`when`(dao.insert(user))
            .thenReturn(Usuario(1, "User"))
        Mockito.`when`(adapter.itemCount).thenReturn(1)

        userUpdate.update(user)

        Mockito.verify(dao).insert(Usuario("User"))
        Mockito.verify(adapter).add(Usuario(1, "User"))
        Mockito.verify(recyclerView).smoothScrollToPosition(0)
    }
}