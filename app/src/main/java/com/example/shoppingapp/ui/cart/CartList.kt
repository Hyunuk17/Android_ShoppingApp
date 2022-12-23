package com.example.shoppingapp.ui.cart

import com.example.shoppingapp.ui.category.Order

class CartList {
    companion object {
        var cartList:MutableList<Order>? = mutableListOf()
    }
}