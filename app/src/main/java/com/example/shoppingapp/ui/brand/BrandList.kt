package com.example.shoppingapp.ui.brand

import com.example.shoppingapp.R
import com.example.shoppingapp.ui.brand.BrandList.Companion.brandList

public class BrandList {
    companion object {
        var brandList:MutableList<Brand>? = setBrandList()
    }
}

fun setBrandList():MutableList<Brand>? {
    val brandImage = arrayListOf(
        R.drawable.nike,
        R.drawable.adidas,
        R.drawable.converse,
        R.drawable.mlb,
        R.drawable.thenorthface,
        R.drawable.thisisneverthat,
        R.drawable.asics,
        R.drawable.newbalance,
        R.drawable.coor,
        R.drawable.puma,
        R.drawable.covernat,
        R.drawable.drmartens,
        R.drawable.lmood,
        R.drawable.maisonmined,
        R.drawable.yale,
        R.drawable.whatitisnt,
        R.drawable.jeep,
        R.drawable.k2,
        R.drawable.poloralphlauren,
        R.drawable.vans
    )

    brandList = mutableListOf(
        Brand("Nike", "Sports", brandImage[0]),
        Brand("Adidas", "Sports", brandImage[1]),
        Brand("Converse", "Casual", brandImage[2]),
        Brand("Mlb", "Sports", brandImage[3]),
        Brand("Thenorthface", "Casual", brandImage[4]),
        Brand("Thisisneverthat", "Casual", brandImage[5]),
        Brand("Asics", "Sports", brandImage[6]),
        Brand("Newbalance", "Casual", brandImage[7]),
        Brand("Coor", "Formal", brandImage[8]),
        Brand("Puma", "Casual", brandImage[9]),
        Brand("Covernat", "Casual", brandImage[10]),
        Brand("Dr.marthens", "Formal", brandImage[11]),
        Brand("Lmood", "Formal", brandImage[12]),
        Brand("Maisonmined", "Formal", brandImage[13]),
        Brand("Yale", "Casual", brandImage[14]),
        Brand("Whatitisnt", "Casual", brandImage[15]),
        Brand("Jeep", "Casual", brandImage[16]),
        Brand("K2", "Sports", brandImage[17]),
        Brand("Poloralphlauren", "Formal", brandImage[18]),
        Brand("Vans", "Casual", brandImage[19])


    )

    return brandList
}