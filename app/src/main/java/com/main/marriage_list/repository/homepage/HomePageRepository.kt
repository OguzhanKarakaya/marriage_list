package com.main.marriage_list.repository.homepage

import com.main.marriage_list.model.product.ProductModel
import io.reactivex.rxjava3.core.Single

interface HomePageRepository {
    fun getList(type: String): Single<ProductModel>
}