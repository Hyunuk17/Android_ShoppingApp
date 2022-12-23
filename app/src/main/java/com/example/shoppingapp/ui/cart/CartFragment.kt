package com.example.shoppingapp.ui.cart

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.*
import com.example.shoppingapp.DBHelper
import com.example.shoppingapp.ui.history.HistoryList
import com.example.shoppingapp.ui.category.Order
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentCartBinding
import com.example.shoppingapp.ui.login.Login
import java.time.LocalDate

class CartFragment : Fragment() {
    private var _binding : FragmentCartBinding? = null
//    private var cartList:MutableList<Order>? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val root:View = binding.root

        if(Login.login == null) {
            CartList.cartList = mutableListOf()
        }

        val adapter = context?.let { CartAdapter(it, CartList.cartList) }
        binding.recyclerView3.adapter = adapter
        binding.recyclerView3.layoutManager = LinearLayoutManager(context, VERTICAL, false )

        binding.cartTotalQty.text = CartList.cartList!!.size.toString()// Order의 Price는 qty*item_Price 해서 넣기

        var totalPrice = 0;
        for(i in 0 until CartList.cartList!!.size) {
            totalPrice += CartList.cartList!![i].price
        }
        binding.cartTotalPrice.text = totalPrice.toString()

        binding.button.setOnClickListener { // DB에 넣기
            Toast.makeText(context, "Complete payment", Toast.LENGTH_LONG).show()

            var dbHelper = DBHelper(context, "mydb.db", null, 1)
            var database: SQLiteDatabase = dbHelper.writableDatabase

            for (i in 0 until CartList.cartList!!.size) {
                CartList.cartList!![i].date = LocalDate.now().toString() // 구매 시간 추가
                HistoryList.historyList!!.add(CartList.cartList!![i]) // 결제 내역으로 옮김

                // DB에 저장
                var order = CartList.cartList!![i] // Order에 시간 들어간 형태 -> History값
                var query = ("INSERT INTO history(name, code, size, qty, price, img, date, userId) VALUES ('${order.name}', '${order.code}', '${order.size}', '${order.qty}', '${order.price}', '${order.img}', '${order.date}', '${order.userId}');")
                database.execSQL(query)

            }

            // DB에 넣었으면 카트 내역 삭제하고 RecyclerView에 통지
            CartList.cartList!!.clear()
            CartList.cartList= mutableListOf()
            binding.recyclerView3.adapter!!.notifyDataSetChanged()
            binding.cartTotalQty.text = CartList.cartList!!.size.toString()
            totalPrice = 0
            binding.cartTotalPrice.text = totalPrice.toString()
        }


        return root
    }



    override fun onDestroy() {
        super.onDestroyView()
        _binding = null
    }
}