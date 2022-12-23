package com.example.shoppingapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var homeList:MutableList<Event>? = null
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // recyclerView
        val recyclerView:RecyclerView = binding.homeRecyclerView
        homeList = setHomeList()
        val homeAdapter = context?.let { HomeAdapter(it, homeList) }

        // layout ViewPager처럼 동작
        val layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false) // 수평정렬
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        recyclerView.apply {
            this.layoutManager = layoutManager
            this.adapter = homeAdapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setHomeList() : MutableList<Event>? {

        val homeEvent = arrayListOf(
            R.drawable.home_event5_lg,
            R.drawable.home_event2_lg,
            R.drawable.home_event3_lg,
            R.drawable.home_event4_lg,
            R.drawable.home_event1_lg

        )

        homeList = mutableListOf(
            Event("2022 F/W Season Event", "22.12.05 ~ 12.18", homeEvent[0], "BALLUTE"),
            Event("2022 F/W Season New Release", "2022.11.10 ~ 12.31", homeEvent[1], "UGG"),
            Event("Winter Holiday Collection", "2022.12.05 ~ 12.26", homeEvent[2], "CONVERSE"),
            Event("2022 F/W Backpack Collection", "2022.10.25 ~ 12.31", homeEvent[3], "COLEMAN"),
            Event("2022 Holiday Present Promotion", "2022.12.05 ~ 12.19", homeEvent[4], "DR.G")
        )

        return homeList
    }
}