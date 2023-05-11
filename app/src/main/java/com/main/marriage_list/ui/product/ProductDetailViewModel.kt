package com.main.marriage_list.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.main.marriage_list.helper.Constants
import com.main.marriage_list.model.product.ProductDetailModel
import com.main.marriage_list.ui.base.BaseViewModel
import com.main.marriage_list.ui.base.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor() :
    BaseViewModel<ProductDetailEvent>() {

    private val _saveProductDetailLiveData = MutableLiveData<Boolean>()
    val saveProductDetailLiveData: LiveData<Boolean>
        get() = _saveProductDetailLiveData

    fun saveToDb(productDetailModel: ProductDetailModel) {
        val fireStore = Firebase.firestore
        val product = hashMapOf(
            "productId" to productDetailModel.productId,
            "boughtCount" to productDetailModel.boughtProductCount?.toString(),
            "wantedCount" to productDetailModel.wantedProductCount?.toString(),
            "productName" to productDetailModel.productName,
            "productPrice" to productDetailModel.productPrice.toString(),
            "productLink" to productDetailModel.productLink,
            "productCount" to productDetailModel.productCount.toString()
        )
        fireStore.collection(Constants.USERS_DATABASE)
            .document(Constants.currentUser?.userBaseModel?.userId.toString())
            .collection(Constants.USER_PRODUCT_DATABASE).add(product)
            .addOnSuccessListener {
                _saveProductDetailLiveData.postValue(true)
            }
            .addOnFailureListener {
                _saveProductDetailLiveData.postValue(false)
            }
    }

    var productDetailClicked: (ProductDetailModel) -> Unit = {
        event.value = Event(ProductDetailEvent.OpenProductDetail(it))
    }
}