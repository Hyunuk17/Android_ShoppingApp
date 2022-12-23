package com.example.shoppingapp.ui.category

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.databinding.DetailCardBinding
import com.example.shoppingapp.databinding.ItemDetailBinding

class DetailViewHolder(val binding: DetailCardBinding) : RecyclerView.ViewHolder(binding.root)

class ItemAdapter(val context: Context, val item: ArrayList<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DetailViewHolder(DetailCardBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as DetailViewHolder).binding
        binding.imageView2.setImageResource(item[position])
    }

    override fun getItemCount(): Int {
       return item.size
    }

}