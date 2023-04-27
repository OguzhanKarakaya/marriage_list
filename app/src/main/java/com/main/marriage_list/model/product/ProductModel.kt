package com.main.marriage_list.model.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ProductModel(
    var productDetailList: @RawValue ArrayList<ProductDetailModel>? = arrayListOf(),
    val productImage: String? = "",
    var productMainName: String? = "",
    val productMainType: String? = ""
) : Parcelable
