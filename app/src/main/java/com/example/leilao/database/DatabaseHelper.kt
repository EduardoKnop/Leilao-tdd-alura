package com.example.leilao.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.leilao.database.contract.UserContract

private const val DB_LEILAO = "leilao-db"
private const val VERSION = 1

private const val CREATE_USER_TABLE =
    "CREATE TABLE " + UserContract.TABLE_USERS + " (" +
            UserContract.COLUMN_ID + " INTEGER PRIMARY KEY, " +
            UserContract.COLUMN_NAME + " TEXT)"

open class DatabaseHelper constructor(context: Context): SQLiteOpenHelper(
    context, DB_LEILAO, null, VERSION
) {

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}