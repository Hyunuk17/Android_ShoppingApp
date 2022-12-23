package com.example.shoppingapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.databinding.HomeCardBinding

// ViewHolder 클래스 선언
class HomeViewHolder(val binding:HomeCardBinding) :RecyclerView.ViewHolder(binding.root)

class HomeAdapter(val context: Context, val homeList:MutableList<Event>?) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeViewHolder(HomeCardBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as HomeViewHolder).binding
        binding.eventTitle.text = homeList!![position].title
        binding.eventDue.text = homeList!![position].due
        binding.imageView.setImageResource(homeList!![position].img)
        binding.eventContent.text = homeList!![position].brand
        binding.eventCount.text = "${position+1} / ${homeList.size}"
    }

    override fun getItemCount(): Int {
        return homeList!!.size
    }

}