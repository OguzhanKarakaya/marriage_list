package com.main.marriage_list.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.main.marriage_list.common.setSafeOnClickListener
import com.main.marriage_list.databinding.ItemProductDetailBinding
import com.main.marriage_list.model.product.ProductDetailModel

class ProductDetailAdapter(private val productList: ArrayList<ProductDetailModel>) :
    RecyclerView.Adapter<ProductDetailAdapter.ViewHolder>() {

    private var onProductDetailClicked: ((ProductDetailModel) -> Unit)? = null

    fun onProductDetailClickListener(listener: ((ProductDetailModel) -> Unit)?) {
        onProductDetailClicked = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductDetailAdapter.ViewHolder {
        val binding =
            ItemProductDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductDetailAdapter.ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    inner class ViewHolder(val binding: ItemProductDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ProductDetailModel) {
            binding.model = model
            binding.parentLayout.setSafeOnClickListener {
                onProductDetailClicked?.invoke(model)
            }
        }
    }
}