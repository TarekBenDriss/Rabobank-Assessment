package com.bendrisstarek.rabobankassessment.base;

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * this class represents a custom view model
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private val mDisposable: CompositeDisposable?
    override fun onCleared() {
        super.onCleared()
        if (mDisposable != null && !mDisposable.isDisposed) {
            mDisposable.clear()
        }
    }

    init {
        mDisposable = CompositeDisposable()
    }
}