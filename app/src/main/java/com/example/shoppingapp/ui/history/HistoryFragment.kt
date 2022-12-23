package com.example.shoppingapp.ui.history

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.DBHelper
import com.example.shoppingapp.databinding.FragmentHistoryBinding
import com.example.shoppingapp.ui.category.Order
import com.example.shoppingapp.ui.login.Login
import java.time.LocalDate

class HistoryFragment : Fragment() {
    private var _binding:FragmentHistoryBinding? = null

    private val binding get() = _binding!! // 바인딩 변수 재 선언? // get()은 무슨 의미

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)// xml inflate
        val root:View = binding.root

        if (Login.login == null) {
            HistoryList.historyList = mutableListOf()
        }else {
            HistoryList.historyList = mutableListOf()

            var dbHelper = DBHelper(context, "mydb.db", null, 1)
            var database: SQLiteDatabase = dbHelper.writableDatabase
            var query = "SELECT * FROM history WHERE userId = '${Login.login!!.id}'"
                var cursor = database.rawQuery(query, null)
                while(cursor.moveToNext()) {
                    var _name = cursor.getString(cursor.getColumnIndex("name"))
                    var _code = cursor.getString(cursor.getColumnIndex("code"))
                    var _size = cursor.getString(cursor.getColumnIndex("size"))
                    var _qty = cursor.getInt(cursor.getColumnIndex("qty"))
                    var _price = cursor.getInt(cursor.getColumnIndex("price"))
                    var _img = cursor.getInt(cursor.getColumnIndex("img"))
                    var _date = cursor.getString(cursor.getColumnIndex("date"))
                    var _userId = cursor.getString(cursor.getColumnIndex("userId"))
                    var order = Order(_name, _code, _size, _qty, _price, _img, _date, _userId)

//             DB에서 꺼내서 historyList에 넣기
                    HistoryList.historyList!!.add(order)
                }
        }


        binding.hisRecyclerView.adapter = HistoryAdapter(context, HistoryList.historyList)
        binding.hisRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        return root
    }

    override fun onDestroy() {
        super.onDestroyView()
        _binding = null
    }
}