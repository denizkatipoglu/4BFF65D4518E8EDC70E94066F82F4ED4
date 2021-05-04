package com.spacedelivery.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper

/**
 * Created by Deniz KATIPOGLU on 17,December,2020
 */
abstract class BaseViewModel : ViewModel() {

    lateinit var onShowToastListener: MutableLiveData<String>
    lateinit var onShowFragmentListener: MutableLiveData<FragmentFactory>
    lateinit var onShowLockScreenListener: MutableLiveData<Unit>
    lateinit var onHideLockScreenListener: MutableLiveData<Unit>


    @CallSuper
    open fun onCreate(savedInstanceState: Bundle?) {
    }

    @CallSuper
    open fun onCreate(savedInstanceState: Bundle?, arguments: Bundle?) {
    }

    open fun onViewCreated(savedInstanceState: Bundle?, arguments: Bundle?) {
        onShowToastListener = MutableLiveData()
        onShowLockScreenListener = MutableLiveData()
        onHideLockScreenListener = MutableLiveData()
        onShowFragmentListener = MutableLiveData()

        onCreate(savedInstanceState)
        onCreate(savedInstanceState, arguments)
    }


    fun showFragment(fragment: BaseFragment<*>, id: Int) {
        var factory = FragmentFactory.Builder(fragment)
            .setViewId(id)
            .build()

        showFragment(factory)
    }

    fun showFragment(factory: FragmentFactory) {
        onShowFragmentListener.postValue(factory)
    }

    @CallSuper
    open fun onResume() {
    }

    @CallSuper
    open fun onPause() {
    }

    @CallSuper
    open fun onSaveInstanceState(outState: Bundle?) {
    }

    open fun isBackEnable(): Boolean {
        return false
    }

    open fun onBackPressed() {

    }

    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }

    open fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
    }
}