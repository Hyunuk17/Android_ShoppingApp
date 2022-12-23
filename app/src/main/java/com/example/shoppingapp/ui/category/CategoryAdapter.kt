package com.example.shoppingapp.ui.category

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.databinding.CategoryCardBinding

class ItemViewHolder(val binding:CategoryCardBinding) : RecyclerView.ViewHolder(binding.root)

class CategoryAdapter(val context:Context, val List:MutableList<Item>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(CategoryCardBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ItemViewHolder).binding
        binding.categoryItemTitle.text = List!![position].title
        binding.categoryItemPrice.text = List!![position].price.toString()
        binding.categoryItemImg.setImageResource(List!![position].img[0])


        // 나중에 Item 개별 화면으로 Intent 시키기
        binding.categoryLayout.setOnClickListener {
//            Toast.makeText(context, binding.categoryItemTitle.text, Toast.LENGTH_LONG).show()
            val intent = Intent(context, ItemActivity::class.java)
            intent.putExtra("Item", List[position])
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return List!!.size
    }




}