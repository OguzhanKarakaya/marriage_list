package com.main.marriage_list.model

data class FurnitureModel(
    var furnitureTitle: String? = "",
    var furnitureDetailList: ArrayList<FurnitureDetailModel>? = arrayListOf()
)

data class FurnitureDetailModel(
    var furnitureName: String? = "",
    var furniturePrice: Double? = 0.0,
    var furnitureCount: Int? = 0
)