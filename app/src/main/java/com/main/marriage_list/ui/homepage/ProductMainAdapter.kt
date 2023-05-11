package com.main.marriage_list.ui.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.main.marriage_list.common.setSafeOnClickListener
import com.main.marriage_list.databinding.ItemProductMainBinding
import com.main.marriage_list.model.product.ProductModel

class ProductMainAdapter(private val productList: ArrayList<ProductModel>) :
    RecyclerView.Adapter<ProductMainAdapter.ViewHolder>() {

    private var productDetailClicked: ((ProductModel) -> Unit)? = null

    fun setOnProductDetailClickListener(listener: ((ProductModel) -> Unit)?) {
        this.productDetailClicked = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductMainAdapter.ViewHolder {
        val binding =
            ItemProductMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductMainAdapter.ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    inner class ViewHolder(val binding: ItemProductMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ProductModel) {
            binding.productModel = model
            binding.parentLayout.setSafeOnClickListener {
                productDetailClicked?.invoke(model)
            }
        }
    }
}