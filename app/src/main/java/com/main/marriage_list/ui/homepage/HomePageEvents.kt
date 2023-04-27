package com.main.marriage_list.ui.homepage

import com.main.marriage_list.model.product.ProductModel
import com.main.marriage_list.ui.base.BaseEvents

sealed class HomePageEvents: BaseEvents {
    data class OpenProductDetail(
        var data: ProductModel
    ): HomePageEvents()
}