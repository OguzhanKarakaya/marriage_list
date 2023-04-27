package com.main.marriage_list.repository.homepage

import com.main.marriage_list.model.product.ProductDetailModel
import io.reactivex.rxjava3.core.Single

interface HomePageRepository {
    fun saveProduct(productDetailModel: ProductDetailModel): Single<Boolean>
}