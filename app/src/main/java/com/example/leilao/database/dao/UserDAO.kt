package com.example.leilao.database.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.leilao.database.DatabaseHelper
import com.example.leilao.database.contract.UserContract
import com.example.leilao.model.Usuario

class UserDAO(context: Context): DatabaseHelper(
    context
) {

    fun insert(user: Usuario): Usuario {
        val db = writableDatabase
        val values = getValues(user)
        val id = db.insert(UserContract.TABLE_USERS, null, values)

        return Usuario(id, user.nome)
    }

    fun getAll(): List<Usuario> {
        val users = ArrayList<Usuario>()
        val db = writableDatabase
        val cursor = db.rawQuery("SELECT * FROM " + UserContract.TABLE_USERS, null)

        while (cursor.moveToNext()) {
            val user = getAndSetUser(cursor)
            users.add(user)
        }
        return users
    }

    private fun getValues(user: Usuario): ContentValues {
        val values = ContentValues()
        values.put(UserContract.COLUMN_NAME, user.nome)

        return values
    }


    private fun getAndSetUser(cursor: Cursor): Usuario {
        val id = cursor.getLong(cursor.getColumnIndexOrThrow(UserContract.COLUMN_ID))
        val name = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.COLUMN_NAME))

        return Usuario(id, name)
    }
}