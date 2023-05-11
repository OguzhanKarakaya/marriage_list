package com.main.marriage_list.ui.homepage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.main.marriage_list.model.product.*
import com.main.marriage_list.repository.homepage.HomePageRepository
import com.main.marriage_list.ui.base.BaseViewModel
import com.main.marriage_list.ui.base.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomePageViewModel @Inject constructor(repository: HomePageRepository) :
    BaseViewModel<HomePageEvents>() {

    private val _productLiveData = MutableLiveData(ArrayList<ProductDetailModel>())
    val productLiveData: LiveData<ArrayList<ProductDetailModel>>
        get() = _productLiveData


    fun getProducts(){
        val productDetailList: MutableList<ProductDetailModel> = mutableListOf()
        val reference: DatabaseReference = Firebase.database.reference
        reference.child("Çeyiz").get().addOnCompleteListener {
            if (it.isSuccessful) {
                it.result?.children?.mapNotNull { doc ->
                    (doc.value as ArrayList<*>).forEach { value ->
                        val detailModel = ProductDetailModel()
                        detailModel.productId = (value as HashMap<*, *>)["productId"].toString()
                        detailModel.productName = value["productName"].toString()
                        detailModel.productImage = value["productImage"].toString()
                        detailModel.productPrice = value["productPrice"].toString().toDouble()
                        detailModel.productLink = value["productLink"].toString()
                        detailModel.boughtProductCount = value["boughtProductCount"].toString().toInt()
                        detailModel.wantedProductCount = value["wantedProductCount"].toString().toInt()
                        detailModel.productMainType = value["productMainType"].toString()
                        productDetailList.add(detailModel)
                    }
                }
                _productLiveData.postValue(productDetailList as ArrayList<ProductDetailModel>?)
            } else {
                Log.i("TAG", "getProducts: ")
            }
        }
    }

    var productClicked: (ProductModel) -> Unit = {
        event.value = Event(HomePageEvents.OpenProduct(it))
    }
}