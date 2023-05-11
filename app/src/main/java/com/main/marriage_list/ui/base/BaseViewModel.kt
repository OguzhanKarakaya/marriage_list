package com.main.marriage_list.ui.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.main.marriage_list.common.ErrorType
import com.main.marriage_list.common.FullLoadingDialogFragment
import com.main.marriage_list.helper.component.ResultBottomSheetFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseViewModel<S : BaseEvents> : ViewModel() {

    protected val disposables = CompositeDisposable()
    internal var errorType = MutableLiveData<ErrorType>()
    protected fun mainThread(): Scheduler = AndroidSchedulers.mainThread()

    private val _event = MutableLiveData<Event<S>>()
    val event: MutableLiveData<Event<S>>
        get() = _event

    fun showProgressDialog(fragment: Fragment, tag: String) {
        FullLoadingDialogFragment().apply {
            isCancelable = false
        }.show(fragment.childFragmentManager, tag)
    }

    fun dismissProgressDialog(fragment: Fragment, tag: String) {
        (fragment.childFragmentManager.findFragmentByTag(tag) as? FullLoadingDialogFragment)?.dismiss()
    }

    fun setResultSheet(
        fragment: Fragment,
        isSuccess: Boolean,
        title: String,
        description: String,
        buttonText: String,
        buttonClickListener: ResultBottomSheetFragment.ResultBottomSheetClickListener?
    ) {
        val bottomSheetFragment = ResultBottomSheetFragment.newInstance(
            isSuccess = isSuccess,
            title = title,
            description = description,
            buttonText = buttonText,
            buttonClickListener = buttonClickListener
        )
        bottomSheetFragment.show(fragment.requireActivity().supportFragmentManager, "")
    }
}