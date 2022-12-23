package com.example.shoppingapp.ui.category

import java.io.Serializable

data class Item(val title:String, val codeNum:String, val brand:String, val category: String, val price:Int, val sales:Int, val img: ArrayList<Int>) :
    Serializable