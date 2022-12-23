package com.example.shoppingapp.ui.category

import com.example.shoppingapp.R
import com.example.shoppingapp.ui.category.ItemList.Companion.itemList


// ItemList를 여러 프래그먼트에서 접근하므로 전역으로 선언
public class ItemList {
    companion object {
        var itemList:MutableList<Item>? = setItemList()

    }

}
    fun setItemList() : MutableList<Item>? {
        val itemImage = arrayListOf(
            arrayListOf(R.drawable.lafudgestore_outer_1),
            arrayListOf(R.drawable.yale_outer_1),
            arrayListOf(R.drawable.brownbreath_outer_1),
            arrayListOf(R.drawable.espionage_outer_1),
            arrayListOf(R.drawable.takeasy_outer_1),
            arrayListOf(R.drawable.yale_top_1, R.drawable.yale_top_2),
            arrayListOf(R.drawable.thisitneverthat_top_1),
            arrayListOf(R.drawable.jeep_top_1),
            arrayListOf(R.drawable.vivastudio_top_1),
            arrayListOf(R.drawable.physicaleducationdepartment_top_1),
            arrayListOf(R.drawable.xero_pants_1),
            arrayListOf(R.drawable.wooalong_pants_1),
            arrayListOf(R.drawable.nomanual_pants_1),
            arrayListOf(R.drawable.beslow_pants_1),
            arrayListOf(R.drawable.fatalism_pants_1),
            arrayListOf(R.drawable.nike_shoes_1),
            arrayListOf(R.drawable.adidas_shoes_1),
            arrayListOf(R.drawable.converse_shoes_1),
            arrayListOf(R.drawable.drmartens_shoes_1),
            arrayListOf(R.drawable.vans_shoes_1)
        )

        itemList = mutableListOf(
            Item("Original M-1965 Fishtail Parka", "FU3D3GE12", "Lafudgestore", "Outer", 138000, 29354, itemImage[0]),
            Item("Warm Up Quilting Jacket Ivory", "YC6JK0001Iv", "Yale", "Outer", 99000, 9672, itemImage[1]),
            Item("Tag Fleece Jacket - Black", "BHFMHZB01BK", "Brownbreath", "Outer", 69000, 1384, itemImage[2]),
            Item("Miler Heavyweight Cardigan Burnt Orange", "20220103-12", "Espionage", "Outer", 132000,3353, itemImage[3]),
            Item("Raccu Baseball Overjacket", "T21JP004", "Takesey", "Outer", 119000, 43293, itemImage[4]),
            Item("2 Tone Arch Hoodie Gray", "YA8HD1071GR", "Yale", "Top", 79000, 53634, itemImage[5]),
            Item("T-Logo Hoodie Heather Grey", "TN220TSWHO01HR", "Thisisneverthat", "Top", 95000, 12839, itemImage[6]),
            Item("Half Zip-Up M-Logo Sweat", "JM5TSU838MW", "Jeep", "Top", 69000, 14000, itemImage[7]),
            Item("Retriever Crewneck", "KAVT202", "Vivastudio", "Top", 69000, 65943, itemImage[8]),
            Item("Phyps Strawberry Hoodie Oatmeal", "PC7HD0002OT", "Physical Education Department","Top", 82000, 49872, itemImage[9]),
            Item("Classic String Sweat Pant", "2020SS03", "XERO", "Pants", 39000, 39352, itemImage[10]),
            Item("Signature Relax Wide Pant - Grey", "SE3DWD535GY", "WOOALONG", "Pants", 79000, 34632, itemImage[11]),
            Item("T.W Denim Pants - Washed Black", "NM23DP01M1BK", "Nomanual", "Pants", 119000, 30295, itemImage[12]),
            Item("Comfy One Tuck Selvage Denim Pants_Indigo", "BSL21SCOONTUSEDEPAIN", "Beslow", "Pants", 99000, 46749, itemImage[13]),
            Item("#0199 Sensitive - B Standard Fit", "F0199", "Fatalism", "Pants", 89000, 6806, itemImage[14]),
            Item("Daybreak Sneakers CK2351-101", "CK2351-101", "Nike", "Shoes", 199000, 54882, itemImage[15]),
            Item("BW Army FWWHT", "HP5515", "Adidas", "Shoes", 139000, 72019, itemImage[16]),
            Item("Chuck 70 Classic Parchment", "162053C", "Converse", "Shoes", 99000, 7446, itemImage[17]),
            Item("1461 Vintage Black Smooth", "11838002", "Dr.martens", "Shoes", 190000, 3422, itemImage[18]),
            Item("Old Skool Platform - Black", "VN0A3BUY281", "Vans", "Shoes", 85000, 305024, itemImage[19])


        )

        // 판매량 순 정렬
        itemList!!.sortWith(compareBy<Item> {it.sales})

        return itemList
    }
