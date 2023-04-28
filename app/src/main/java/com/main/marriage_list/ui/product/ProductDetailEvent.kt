package com.main.marriage_list.ui.product

import com.main.marriage_list.model.product.ProductDetailModel
import com.main.marriage_list.ui.base.BaseEvents

sealed class ProductDetailEvent : BaseEvents {
    data class OpenProductDetail(
        var data: ProductDetailModel
    ) : ProductDetailEvent()
}