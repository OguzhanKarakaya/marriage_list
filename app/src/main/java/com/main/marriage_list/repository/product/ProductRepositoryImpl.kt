package com.main.marriage_list.repository.product

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.main.marriage_list.helper.Constants
import com.main.marriage_list.model.product.ProductDetailModel
import com.main.marriage_list.model.product.ProductModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor() : ProductRepository {

    private val TAG = "TAG_PRODUCT_REPOSITORY"

    override fun getProducts(productModel: ProductModel): Single<ProductDetailModel> {
        Log.i(TAG, "Product getting successfully")
        val productDetailModel = ProductDetailModel()
        return Single.just(productDetailModel)
    }

    override fun saveProducts(productDetailModel: ProductDetailModel, uId: String): Single<Boolean> {
        var isSavedSuccess = false
        val fireStore = Firebase.firestore
        val product = hashMapOf(
            "productId" to productDetailModel.productId,
            "isBought" to "X",
            "isWanted" to ""
        )
        fireStore.collection(Constants.USERS_DATABASE).document(uId).collection("test").document("1").set(product)
            .addOnSuccessListener {
                isSavedSuccess = true
            }
            .addOnFailureListener {
                isSavedSuccess = false
            }
        return Single.just(isSavedSuccess)
    }
}