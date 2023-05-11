package com.main.marriage_list.model.product

import com.google.gson.annotations.SerializedName

data class ProductDetailModel(
    @SerializedName("productId") var productId: String? = "",
    @SerializedName("productName") var productName: String? = "",
    @SerializedName("productImage") var productImage: String? = "",
    @SerializedName("productPrice") var productPrice: Double? = 0.0,
    @SerializedName("productLink") var productLink: String? = "",
    @SerializedName("productCount") var productCount: Int? = 0,
    @SerializedName("boughtProductCount") var boughtProductCount: Int? = 0,
    @SerializedName("wantedProductCount") var wantedProductCount: Int? = 0,
    @SerializedName("productMainType") var productMainType: String? = ""
)

data class ProductMainTest(
    var test: ProductChildTest? = null
)

data class ProductChildTest(
    var detailList: ArrayList<ProductDetailModel>? = arrayListOf()
)