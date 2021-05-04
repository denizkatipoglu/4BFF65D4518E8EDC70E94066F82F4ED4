package com.spacedelivery.base

import androidx.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.spacedelivery.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import java.lang.reflect.ParameterizedType
import java.util.logging.Logger
import javax.inject.Inject

/**
 * Created by Deniz Katipoğlu on 29.07.2018.
 */
abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: VM


    abstract fun getLayoutId(): Int

    abstract fun setListeners()

    abstract fun setReceivers()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)

        if (!::viewModel.isInitialized) {
            viewModel = ViewModelProvider(this, viewModelFactory).get((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>)
        }

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(getLayoutId(), container, false)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onViewCreated(savedInstanceState, arguments)

        viewModel.onShowFragmentListener.observe(this, Observer { factory -> (activity as BaseActivity<*>).showFragment(factory!!) })
        viewModel.onShowLockScreenListener.observe(this, Observer { (activity as BaseActivity<*>).showLockScreen() })
        viewModel.onHideLockScreenListener.observe(this, Observer { (activity as BaseActivity<*>).hideLockScreen() })


        setListeners()
        setReceivers()
    }

    override fun onResume() {
        super.onResume()


        viewModel.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)


        viewModel.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {

        super.onDestroyView()
    }

    fun showFragment(fragment: BaseFragment<*>, id: Int) {
        var factory = FragmentFactory.Builder(fragment)
                .setViewId(id)
                .build()

        (activity as BaseActivity<*>).showFragment(factory)
    }


    override fun onPause() {

        viewModel.onPause()

        super.onPause()
    }

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (::viewModel.isInitialized) {
            viewModel.onActivityResult(requestCode, resultCode, data)
        }
    }

    @CallSuper
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (::viewModel.isInitialized) {
            viewModel.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}