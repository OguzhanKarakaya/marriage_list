package com.main.marriage_list.ui.product

import com.main.marriage_list.model.product.ProductDetailModel
import com.main.marriage_list.model.product.ProductModel
import com.main.marriage_list.repository.product.ProductRepository
import com.main.marriage_list.ui.base.BaseViewModel
import com.main.marriage_list.ui.base.Event
import com.main.marriage_list.ui.homepage.HomePageEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(repository: ProductRepository) :
    BaseViewModel<ProductDetailEvent>() {

    var productDetailClicked: (ProductDetailModel) -> Unit = {
        event.value = Event(ProductDetailEvent.OpenProductDetail(it))
    }

}