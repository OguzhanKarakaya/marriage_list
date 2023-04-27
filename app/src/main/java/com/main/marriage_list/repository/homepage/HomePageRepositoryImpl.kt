package com.main.marriage_list.repository.homepage

import android.util.Log
import com.main.marriage_list.model.product.ProductDetailModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class HomePageRepositoryImpl @Inject constructor() : HomePageRepository {

    private val TAG = "TAG_BASE_REPOSITORY"

    override fun saveProduct(productDetailModel: ProductDetailModel): Single<Boolean> {
        Log.i(TAG, "product saved successfully")
        return Single.just(true)
    }
}