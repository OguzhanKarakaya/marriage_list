package com.main.marriage_list.model.product

data class ProductDetailModel(
    val productId: String? = "",
    var productName: String? = "",
    var productImage: String? = "",
    var productPrice: Double? = 0.0,
    var productLink: String? = "",
    var productCount: Int? = 0,
    var isProductBought: Boolean? = false,
    var isProductWanted: Boolean? = false
)
