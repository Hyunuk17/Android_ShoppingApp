package com.example.shoppingapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.time.LocalDate


class DBHelper(context: Context?, name:String, factory:SQLiteDatabase.CursorFactory?, version:Int) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql:String = "CREATE TABLE if not exists user(id varchar(20) PRIMARY KEY, pw, phone, email);"
        val sql2:String = "CREATE TABLE if not exists history(id integer PRIMARY KEY autoincrement, name, code, size, qty integer, price integer, img integer, date LocalDate, userId);"

        db!!.execSQL(sql)
        db!!.execSQL(sql2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val sql:String = "DROP TABLE if exists user"
        db!!.execSQL(sql) // 삭제하고
        onCreate(db) // 다시 생성

        val sql2:String = "DROP TABLE if exists history"
        db!!.execSQL(sql2) // 삭제하고
        onCreate(db) // 다시 생성
    }
}