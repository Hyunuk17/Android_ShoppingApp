package com.example.shoppingapp.ui.login

import android.database.sqlite.SQLiteDatabase
import com.example.shoppingapp.DBHelper

class UserList {
    companion object {
        var userList:MutableList<User> = mutableListOf(User("Manager", "Manager", "Manager", "Manager"))



        // UserList DB화 고민
        // DB
//        var dbHelper = DBHelper(this, "mydb.db", null, 1)
//        var database: SQLiteDatabase = dbHelper.writableDatabase

//        Main

    }

}