package com.main.marriage_list.model.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ProductModel(
    var productDetailList: @RawValue ArrayList<ProductDetailModel>? = arrayListOf()
) : Parcelable

@Parcelize
data class ProductMainModel(
    var mutfak: List<ProductModel>? = null
) : Parcelable


data class Product(
    val productName: String? = null,
    val productImage: String? = null
)

data class Category(
    val products: ArrayList<Product>? = null
)

data class Furniture(
    val categories: List<Category>? = null
)

data class ProductList(val productList: ArrayList<Product>) : ArrayList<Product>()