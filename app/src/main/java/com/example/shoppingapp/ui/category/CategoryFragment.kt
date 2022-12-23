package com.example.shoppingapp.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentCategoryBinding
import com.example.shoppingapp.ui.category.ItemList.Companion.itemList

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private var filteredItemList:MutableList<Item>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // RecyclerView
        filteredItemList = mutableListOf()
        filteredItemList!!.addAll(itemList!!)
        binding.recyclerView.adapter = context?.let { CategoryAdapter(it, filteredItemList) }
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)


        // 아이콘 클릭 시 필터링
        binding.categoryAll.setOnClickListener {
            binding.categoryAll.setTextColor(ContextCompat.getColor(context!!, R.color.black))
            binding.categoryOuter.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.categoryTop.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.categoryPants.setTextColor(ContextCompat.getColor(context!!,androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.categoryShoes.setTextColor(ContextCompat.getColor(context!!,androidx.appcompat.R.color.abc_secondary_text_material_light))
            filtering("All")
        }
        binding.categoryOuter.setOnClickListener {
            binding.categoryAll.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.categoryOuter.setTextColor(ContextCompat.getColor(context!!, R.color.black))
            binding.categoryTop.setTextColor(ContextCompat.getColor(context!!,  androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.categoryPants.setTextColor(ContextCompat.getColor(context!!,  androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.categoryShoes.setTextColor(ContextCompat.getColor(context!!,  androidx.appcompat.R.color.abc_secondary_text_material_light))
            filtering("Outer")
        }
        binding.categoryTop.setOnClickListener {
            binding.categoryAll.setTextColor(ContextCompat.getColor(context!!,  androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.categoryOuter.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.categoryTop.setTextColor(ContextCompat.getColor(context!!,R.color.black))
            binding.categoryPants.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.categoryShoes.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            filtering("Top")
        }
        binding.categoryPants.setOnClickListener {
            binding.categoryAll.setTextColor(ContextCompat.getColor(context!!,  androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.categoryOuter.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.categoryTop.setTextColor(ContextCompat.getColor(context!!,  androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.categoryPants.setTextColor(ContextCompat.getColor(context!!,R.color.black))
            binding.categoryShoes.setTextColor(ContextCompat.getColor(context!!,  androidx.appcompat.R.color.abc_secondary_text_material_light))
            filtering("Pants")
        }
        binding.categoryShoes.setOnClickListener {
            binding.categoryAll.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.categoryOuter.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.categoryTop.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.categoryPants.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.categoryShoes.setTextColor(ContextCompat.getColor(context!!,R.color.black))
            filtering("Shoes")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun filtering(category:String): MutableList<Item>? {
        filteredItemList!!.clear()

        if(category == "All") {
            filteredItemList!!.addAll(itemList!!)
        }
        else {
            for (i in 0 until itemList!!.size) {
                if (itemList!![i].category == category) {
                    filteredItemList!!.add(itemList!![i])

                }
            }
       }

        binding.recyclerView.adapter!!.notifyDataSetChanged()
        return filteredItemList
    }
}