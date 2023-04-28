package com.main.marriage_list.ui.homepage

import com.main.marriage_list.model.product.ProductModel
import com.main.marriage_list.repository.homepage.HomePageRepository
import com.main.marriage_list.ui.base.BaseViewModel
import com.main.marriage_list.ui.base.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(repository: HomePageRepository) :
    BaseViewModel<HomePageEvents>() {


    var productClicked: (ProductModel) -> Unit = {
        event.value = Event(HomePageEvents.OpenProduct(it))
    }
}