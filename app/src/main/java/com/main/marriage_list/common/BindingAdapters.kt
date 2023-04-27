package com.main.marriage_list.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.main.marriage_list.model.product.ProductModel
import com.main.marriage_list.ui.homepage.ProductMainAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("app:setProductList", "app:onProductDetailClick")
    fun setProductList(
        recyclerView: RecyclerView,
        productList: ArrayList<ProductModel>?,
        onProductDetailClicked: (ProductModel) -> Unit
    ) {
        productList?.let {
            val adapter = ProductMainAdapter(productList)
            adapter.setOnProductDetailClickListener(onProductDetailClicked)
            recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 2)
            recyclerView.adapter = adapter
        }
    }

    @JvmStatic
    @BindingAdapter("app:loadImageUrl")
    fun loadImageFromUrl(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            Glide.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
        }
    }
}