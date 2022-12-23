package com.example.shoppingapp.ui.brand

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.databinding.BrandCardBinding

class BrandViewHolder(val binding: BrandCardBinding) : RecyclerView.ViewHolder(binding.root)

class BrandAdapter(val context: Context, val brandList:MutableList<Brand>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BrandViewHolder(BrandCardBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as BrandViewHolder).binding
        binding.brandImg.setImageResource(brandList!![position].img)
        binding.brandTitle.text = brandList!![position].title
        binding.brandRank.text = "${position+1}"
    }

    override fun getItemCount(): Int {
        return brandList!!.size
    }


}