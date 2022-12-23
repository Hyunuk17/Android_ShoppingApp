package com.example.shoppingapp.ui.category

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.DBHelper
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ItemDetailBinding
import com.example.shoppingapp.ui.cart.CartList
import com.example.shoppingapp.ui.history.HistoryList
import com.example.shoppingapp.ui.login.Login
import com.example.shoppingapp.ui.login.LoginActivity
import com.example.shoppingapp.ui.login.User
import com.example.shoppingapp.ui.login.UserList
import java.time.LocalDate

class ItemActivity: AppCompatActivity() {
    lateinit var binding:ItemDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar:ActionBar? = supportActionBar
        actionBar!!.hide()

        binding =ItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemDetails = intent.getSerializableExtra("Item") as Item
        binding.detailName.text = itemDetails.title
        binding.brandName.text = itemDetails.brand
        binding.detailCode.text = itemDetails.codeNum
        binding.detailPrice.text = itemDetails.price.toString()

        val size = arrayListOf<String>("XXL", "XL", "L", "M", "S")
        binding.detailSize.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, size)
        var selectedSize = ""
        binding.detailSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedSize = ""; // cart에 저장할 때 사용
                when(p2) {
                    0 -> selectedSize = size[0]
                    1 -> selectedSize = size[1]
                    2 -> selectedSize = size[2]
                    3 -> selectedSize = size[3]
                    4 -> selectedSize = size[4]
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                selectedSize = ""
            }
        }

        val qty = arrayListOf<Int>(1,2,3,4,5)
        binding.detailQty.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, qty)

        binding.detailTotal.text = 0.toString()

        var selectedQty = 1;
        var totalPrice = 0;
        binding.detailQty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedQty = p2+1 // 선택된 수량 저장용
                totalPrice = itemDetails.price * selectedQty
                binding.detailTotal.text = totalPrice.toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                selectedQty = 1
            }
        }

        // 아이템 사진 recyclerView
        val detailImg = arrayListOf<Int>()
        detailImg.addAll(itemDetails.img)

        val itemAdapter = ItemAdapter(this, detailImg)
        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerView2)

        binding.recyclerView2.apply {
            this.layoutManager = layoutManager
            this.adapter = itemAdapter
        }

        // Button 장바구니 / 결제
        binding.buttonCart.setOnClickListener {
            if(Login.login == null) { // 로그인 안되어 있으면
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

                Toast.makeText(this, "Login is required", Toast.LENGTH_LONG).show()
            }
            else { // 로그인 되어있다면
                // itemDetails 객체 Order 클래스 형태로 변환 시켜서 CartList 배열에 넣기
                val cart = Order(itemDetails.title, itemDetails.codeNum, selectedSize, selectedQty, totalPrice, itemDetails.img[0], null, Login.login!!.id)
                CartList.cartList!!.add(cart)

                Toast.makeText(this, "Added to cart", Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonBuy.setOnClickListener {
            // 로그인 확인
            if(Login.login == null) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

                Toast.makeText(this, "Login is required", Toast.LENGTH_LONG).show()
            }
            else {
                // itemDetails 객체 Order 클래스 형태로 변환 시켜서 DB에 저장
                val order = Order(itemDetails.title, itemDetails.codeNum, selectedSize, selectedQty, totalPrice, itemDetails.img[0], LocalDate.now().toString(),
                    Login.login!!.id)

                // History DB에 등록
                var dbHelper = DBHelper(this, "mydb.db", null, 1)
                var database: SQLiteDatabase = dbHelper.writableDatabase
                var query = ("INSERT INTO history(name, code, size, qty, price, img, date, userId) VALUES ('${order.name}', '${order.code}', '${order.size}', '${order.qty}', '${order.price}', '${order.img}', '${order.date}', '${order.userId}');")
                database.execSQL(query)

                Toast.makeText(this, "Complete payment", Toast.LENGTH_LONG).show()
            }
        }


        // 로그인 위젯
        binding.login2.setOnClickListener {
            if(Login.login == null) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            else {
                Login.login = null
                Toast.makeText(this@ItemActivity, "Complete logout", Toast.LENGTH_LONG).show()
            }

        }
        binding.close.setOnClickListener {
            finish()
        }

    }


}