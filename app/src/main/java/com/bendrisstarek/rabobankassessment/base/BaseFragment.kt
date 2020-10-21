package com.bendrisstarek.rabobankassessment.base;

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment

/**
 * this class represents a custom fragment
 */
abstract class BaseFragment : Fragment() {
    protected var mContext: Context? = null
    protected var mActivity: BaseActivity? = null
    private var mResources: Resources? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as BaseActivity?
        mContext = mActivity
        mResources = resources
        retrieveExtras(if (arguments != null) arguments else Bundle())
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
    }

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    @CallSuper
    protected fun retrieveExtras(bundle: Bundle?) {
    }

    @CallSuper
    protected fun removeExtras(bundle: Bundle?) {
        arguments = null
    }
}