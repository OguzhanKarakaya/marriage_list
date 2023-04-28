package com.main.marriage_list.repository.product

import android.util.Log
import com.main.marriage_list.model.product.ProductDetailModel
import com.main.marriage_list.model.product.ProductModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(): ProductRepository{

    private val TAG = "TAG_PRODUCT_REPOSITORY"

    override fun getProducts(productModel: ProductModel): Single<ProductDetailModel> {
        Log.i(TAG, "Product getting successfully")
        val productDetailModel = ProductDetailModel()
        return Single.just(productDetailModel)
    }

    override fun saveProducts(productDetailModel: ProductDetailModel): Single<Boolean> {
        Log.i(TAG, "Products saved successfully")
        return Single.just(true)
    }
}