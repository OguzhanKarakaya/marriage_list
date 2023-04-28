package com.main.marriage_list.repository.homepage

import android.util.Log
import com.main.marriage_list.model.product.ProductDetailModel
import com.main.marriage_list.model.product.ProductModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class HomePageRepositoryImpl @Inject constructor() : HomePageRepository {

    private val TAG = "TAG_BASE_REPOSITORY"

    override fun getList(type: String): Single<ProductModel> {
        Log.i(TAG, "product saved successfully")
        val productModel = ProductModel()
        return Single.just(productModel)
    }
}