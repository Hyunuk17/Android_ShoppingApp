package com.example.shoppingapp.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.ui.category.Order
import com.example.shoppingapp.databinding.CartItemBinding
import com.example.shoppingapp.ui.login.Login

class CartViewHolder(val binding:CartItemBinding) : RecyclerView.ViewHolder(binding.root)

class CartAdapter(val context: Context, private val cartList: MutableList<Order>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CartViewHolder(CartItemBinding.inflate(LayoutInflater.from(context),parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val binding = (holder as CartViewHolder).binding
            binding.cartImg.setImageResource(cartList!![position].img)
            binding.cartInfo.text = cartList[position].name
            binding.cartSize.text = cartList[position].size
            binding.cartQty.text = cartList[position].qty.toString()
            binding.cartPrice.text = cartList[position].price.toString()

    }

    override fun getItemCount(): Int {
        return cartList!!.size
    }
}