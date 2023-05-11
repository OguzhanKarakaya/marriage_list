package com.main.marriage_list.repository.product

import com.main.marriage_list.model.product.ProductDetailModel
import com.main.marriage_list.model.product.ProductModel
import io.reactivex.rxjava3.core.Single

interface ProductRepository {
    fun getProducts(productModel: ProductModel): Single<ProductDetailModel>
    fun saveProducts(productDetailModel: ProductDetailModel, uId: String): Single<Boolean>
}