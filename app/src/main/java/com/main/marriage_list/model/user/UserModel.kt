package com.main.marriage_list.model.user

import com.main.marriage_list.model.product.ProductModel

data class UserModel(
    val userBaseModel: UserBaseModel = UserBaseModel(),
    var wantsToBuyList: ArrayList<ProductModel>? = arrayListOf(),
    var boughtList: ArrayList<ProductModel>? = arrayListOf()
)
