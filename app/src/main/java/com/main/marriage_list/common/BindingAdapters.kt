package com.main.marriage_list.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.main.marriage_list.model.product.ProductDetailModel
import com.main.marriage_list.model.product.ProductModel
import com.main.marriage_list.ui.homepage.ProductMainAdapter
import com.main.marriage_list.ui.product.ProductDetailAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("app:loadImageUrl")
    fun loadImageFromUrl(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            Glide.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("app:setProductList", "app:onProductClick")
    fun setProductList(
        recyclerView: RecyclerView,
        productList: ArrayList<ProductModel>?,
        onProductClicked: (ProductModel) -> Unit
    ) {
        productList?.let {
            val adapter = ProductMainAdapter(it)
            adapter.setOnProductDetailClickListener(onProductClicked)
            recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 2)
            recyclerView.adapter = adapter
        }
    }

    @JvmStatic
    @BindingAdapter("app:setProductDetailList", "app:onProductDetailClick")
    fun setProductDetailList(
        recyclerView: RecyclerView,
        productDetailList: ArrayList<ProductDetailModel>?,
        onProductDetailClicked: (ProductDetailModel) -> Unit
    ) {
        productDetailList?.let {
            val adapter = ProductDetailAdapter(it)
            adapter.onProductDetailClickListener(onProductDetailClicked)
            recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL))
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = adapter
        }
    }
}