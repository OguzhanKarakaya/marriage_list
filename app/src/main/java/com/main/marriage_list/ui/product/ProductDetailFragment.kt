package com.main.marriage_list.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.main.marriage_list.R
import com.main.marriage_list.databinding.FragmentProductDetailBinding
import com.main.marriage_list.helper.component.ProductBottomSheet
import com.main.marriage_list.model.product.ProductDetailModel
import com.main.marriage_list.model.product.ProductModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding
    private val args: ProductDetailFragmentArgs by navArgs()
    private val viewModel: ProductDetailViewModel by viewModels()

    private var productDetailModel = ProductModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        productDetailModel = args.productDetailModel

        with(binding) {
            viewModel = this@ProductDetailFragment.viewModel
            productModel = productDetailModel
        }

        viewModel.event.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { st ->
                listenEvents(st)
            }
        }

        return binding.root
    }

    private fun listenEvents(event: ProductDetailEvent) {
        when (event) {
            is ProductDetailEvent.OpenProductDetail -> setBottomSheet(event.data)
        }
    }

    private fun setBottomSheet(detailModel: ProductDetailModel) {
        val bottomSheetFragment = ProductBottomSheet.newInstance(
            productMainType = detailModel.productMainType,
            productType = detailModel.productName,
            image = detailModel.productImage,
            buttonClickListener = object : ProductBottomSheet.ProductSaveClickListener {
                override fun onProductSaveClicked(productDetailModel: ProductDetailModel) {
                    viewModel.showProgressDialog(this@ProductDetailFragment, "tag")
                    viewModel.saveToDb(productDetailModel)
                    viewModel.saveProductDetailLiveData.observe(viewLifecycleOwner) {
                        viewModel.dismissProgressDialog(this@ProductDetailFragment, "tag")
                        if (!it)
                            viewModel.setResultSheet(
                                fragment = this@ProductDetailFragment,
                                isSuccess = false,
                                title = getString(R.string.fail),
                                description = getString(R.string.general_fail),
                                buttonText = getString(R.string.ok),
                                buttonClickListener = null
                            )
                    }
                }

            }
        )
        bottomSheetFragment.show(requireActivity().supportFragmentManager, "")
    }
}