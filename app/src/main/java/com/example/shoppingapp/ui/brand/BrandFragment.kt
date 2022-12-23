package com.example.shoppingapp.ui.brand

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shoppingapp.ui.brand.BrandList.Companion.brandList
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentBrandBinding

class BrandFragment : Fragment() {

    private var _binding: FragmentBrandBinding? = null
    private var filteredBrandList:MutableList<Brand>? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBrandBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // 버튼 클릭시 작동하도록 만들기 : 버튼 색도 변경
        filteredBrandList = mutableListOf()
        filteredBrandList!!.addAll(brandList!!)
        binding.recyclerView.adapter = context?.let { BrandAdapter(it, filteredBrandList) }
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)

        // 아이콘 클릭 시 필터링
        binding.brandAll.setOnClickListener {
            binding.brandAll.setTextColor(ContextCompat.getColor(context!!, R.color.black))
            binding.brandCasual.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.brandSports.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.brandFormal.setTextColor(ContextCompat.getColor(context!!,androidx.appcompat.R.color.abc_secondary_text_material_light))
            filtering("All")
        }
        binding.brandCasual.setOnClickListener {
            binding.brandAll.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.brandCasual.setTextColor(ContextCompat.getColor(context!!, R.color.black))
            binding.brandSports.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.brandFormal.setTextColor(ContextCompat.getColor(context!!,androidx.appcompat.R.color.abc_secondary_text_material_light))
            filtering("Casual")
        }
        binding.brandSports.setOnClickListener {
            binding.brandAll.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.brandCasual.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.brandSports.setTextColor(ContextCompat.getColor(context!!, R.color.black))
            binding.brandFormal.setTextColor(ContextCompat.getColor(context!!,androidx.appcompat.R.color.abc_secondary_text_material_light))
            filtering("Sports")
        }
        binding.brandFormal.setOnClickListener {
            binding.brandAll.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.brandCasual.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.brandSports.setTextColor(ContextCompat.getColor(context!!, androidx.appcompat.R.color.abc_secondary_text_material_light))
            binding.brandFormal.setTextColor(ContextCompat.getColor(context!!,R.color.black))
            filtering("Formal")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun filtering(brandType:String): MutableList<Brand>? {
        filteredBrandList!!.clear()

        if(brandType == "All") {
            filteredBrandList!!.addAll(brandList!!)
        }
        else {
            for (i in 0..brandList!!.size - 1) {
                if (brandList!![i].brandType == brandType) {
                    filteredBrandList!!.add(brandList!![i])

                }
            }
        }

        binding.recyclerView.adapter!!.notifyDataSetChanged()
        return filteredBrandList
    }
}