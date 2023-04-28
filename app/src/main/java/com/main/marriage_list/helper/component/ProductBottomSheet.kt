package com.main.marriage_list.helper.component

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.main.marriage_list.common.setSafeOnClickListener
import com.main.marriage_list.databinding.ProductBottomSheetBinding
import com.main.marriage_list.model.product.ProductDetailModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: ProductBottomSheetBinding
    private var onSaveButtonClickListener: ProductSaveClickListener? = null
    private var productType: String? = ""
    private var productMainType: String? = ""
    private var productDetailModel = ProductDetailModel()

    companion object {
        fun newInstance(
            productMainType: String? = null,
            productType: String? = null,
            buttonClickListener: ProductSaveClickListener? = null
        ): ProductBottomSheet {
            val fragment = ProductBottomSheet()
            fragment.onSaveButtonClickListener = buttonClickListener
            val bundle = Bundle()
            bundle.putString("productMainType", productMainType)
            bundle.putString("productType", productType)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProductBottomSheetBinding.inflate(inflater, container, false)

        arguments?.let {
            productMainType = it.getString("productMainType")
            productType = it.getString("productType")
        }

        binding.title = productType

        binding.btnSaveProduct.setSafeOnClickListener {
            with(productDetailModel) {
                productPrice = binding.etPrice.text.toString().toDouble()
                productLink = binding.etLink.text.toString()
                val totalCount =
                    binding.etPlanned.text.toString().toInt() + binding.etPurchased.text.toString()
                        .toInt()
                productCount = totalCount
            }
            dismiss()
            onSaveButtonClickListener?.onProductSaveClicked(productDetailModel)
        }

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener {
            val bottomSheet =
                (requireDialog().findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)) as FrameLayout
            val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet)
            behavior.apply {
                state = BottomSheetBehavior.STATE_EXPANDED
                peekHeight = RelativeLayout.LayoutParams.WRAP_CONTENT
                addBottomSheetCallback(object :
                    BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                            //resultBottomSheetClickListener?.onContinueButtonClick(isSuccess)
                        }
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) = Unit
                })
            }
        }
        return bottomSheetDialog
    }

    interface ProductSaveClickListener {
        fun onProductSaveClicked(productDetailModel: ProductDetailModel)
    }
}