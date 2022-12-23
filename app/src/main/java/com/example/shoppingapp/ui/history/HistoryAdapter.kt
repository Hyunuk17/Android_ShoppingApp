package com.example.shoppingapp.ui.history

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.ui.category.Order
import com.example.shoppingapp.databinding.HistoryCardBinding

class HistoryViewHolder(val binding:HistoryCardBinding) : RecyclerView.ViewHolder(binding.root)

class HistoryAdapter(val context: Context?, val historyList: MutableList<Order>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HistoryViewHolder(HistoryCardBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as HistoryViewHolder).binding
        binding.historyDate.text = historyList!![position].date.toString()
        binding.historyImg.setImageResource(historyList!![position].img)
        binding.historyInfo.text = historyList[position].name
        binding.historySize.text = historyList[position].size
        binding.historyQty.text = historyList[position].qty.toString()
        binding.historyPrice.text = historyList[position].price.toString()

    }

    override fun getItemCount(): Int {
        return historyList!!.size
    }

}