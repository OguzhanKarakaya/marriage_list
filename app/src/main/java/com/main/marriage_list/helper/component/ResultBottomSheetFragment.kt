package com.main.marriage_list.helper.component

import android.app.Dialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.main.marriage_list.R
import com.main.marriage_list.common.setSafeOnClickListener
import com.main.marriage_list.databinding.ResultBottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ResultBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: ResultBottomSheetBinding
    private var buttonClickListener: ResultBottomSheetClickListener? = null
    private var isSuccess = false

    companion object {
        fun newInstance(
            isSuccess: Boolean = false,
            title: String? = null,
            description: String? = null,
            buttonText: String? = null,
            buttonClickListener: ResultBottomSheetClickListener? = null
        ): ResultBottomSheetFragment {
            val fragment = ResultBottomSheetFragment()
            fragment.buttonClickListener = buttonClickListener
            val bundle = Bundle()
            bundle.putBoolean("isSuccess", isSuccess)
            bundle.putString("title", title)
            bundle.putString("description", description)
            bundle.putString("buttonText", buttonText)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ResultBottomSheetBinding.inflate(inflater, container, false)

        arguments?.let {
            isSuccess = it.getBoolean("isSuccess")
            with(binding) {
                txtResult.text = it.getString("title")
                txtDescription.text = it.getString("description")
                imgResult.setBackgroundDrawable(setResultIcon(isSuccess))
                btnResultSheet.text = it.getString("buttonText")
                imgClose.setOnClickListener { dismiss() }
                btnResultSheet.setSafeOnClickListener {
                    dismiss()
                    buttonClickListener?.onContinueButtonClick(isSuccess)
                }
            }
        }

        return binding.root
    }

    private fun setResultIcon(isSuccess: Boolean): Drawable? {
        return if (isSuccess)
            context?.let { ContextCompat.getDrawable(it, R.drawable.ic_baseline_check_circle_outline_72) }
        else context?.let { ContextCompat.getDrawable(it, R.drawable.ic_baseline_error_outline_72) }
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

    interface ResultBottomSheetClickListener {
        fun onContinueButtonClick(isSuccess: Boolean)
    }
}