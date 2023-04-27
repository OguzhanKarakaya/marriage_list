package com.main.marriage_list.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.main.marriage_list.common.ErrorType
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
}