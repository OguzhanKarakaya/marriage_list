package com.main.marriage_list.model.product

data class ProductDetailModel(
    val productId: String? = "",
    var productName: String? = "",
    val productImage: String? = "",
    val productPrice: Double? = 0.0,
    val productCount: Int? = 0,
    val isProductBought: Boolean? = false,
    val isProductWanted: Boolean? = false
)
