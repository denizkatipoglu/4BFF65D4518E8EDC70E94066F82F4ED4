package com.spacedelivery.base

import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.igasystem.iga.util.fragment.AnimationType
import com.igasystem.iga.util.fragment.TransitionType

import com.spacedelivery.di.ViewModelFactory
import com.spacedelivery.dialog.DGProgress
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import java.lang.reflect.ParameterizedType
import javax.inject.Inject


/**
 * Created by Deniz KATIPOGLU on 17,December,2020
 */
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: VM

    private var dgLockScreen: DGProgress? = null

    abstract fun getLayoutId(): Int

    abstract fun setListeners()

    abstract fun setReceivers()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)


        setContentView(getLayoutId())

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>)

        viewModel.onViewCreated(savedInstanceState, intent.extras)

        viewModel.onShowFragmentListener.observe(this, Observer { factory -> showFragment(factory!!) })


        setListeners()
        setReceivers()
    }

    override fun onResume() {
        super.onResume()

        viewModel.onResume()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)


        viewModel.onSaveInstanceState(outState)
    }

    open fun startActivity(activity: BaseActivity<*>) {
        startActivity(Intent(this, activity.javaClass))
    }

    open fun startActivity(activity: BaseActivity<*>, bundle: Bundle) {
        startActivity(Intent(this, activity.javaClass).putExtras(bundle))
    }

    override fun onBackPressed() {

        if (viewModel.isBackEnable()) {
            viewModel.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun onPause() {

        viewModel.onPause()

        super.onPause()
    }

    override fun onDestroy() {

        super.onDestroy()
    }

    @CallSuper
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        viewModel.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        viewModel.onActivityResult(requestCode, resultCode, data)
    }

    open fun showFragment(builder: FragmentFactory?) {
        if (builder == null) {
            throw NullPointerException("Builder can't be null")
        }

        var fm: FragmentManager? = supportFragmentManager

        if (builder.getManager() != null) {
            fm = builder.getManager()
        }

        val ft = fm!!.beginTransaction()

        if (builder.isClearBackStack()) {
            fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        if (builder.isDialog()) {
            builder.getDialogFragment()!!.show(fm, null)
        } else {
            val fragment = builder.getFragment()
            val tag = builder.getTag()

            val containerId = builder.getViewId()

            var view = builder.getView()

            if (view == null) {
                view = findViewById(containerId)

                if (view == null) {
                    throw NullPointerException("Please provide a valid view code")
                }
            }

            if (builder.getAnimationType() !== AnimationType.NO_ANIM) {
                val anim = AnimationType.getAnimation(builder.getAnimationType())

                ft.setCustomAnimations(anim[0], anim[1], anim[2], anim[3])
            }

            if (builder.addToBackStack()) {
                ft.addToBackStack(tag)
            }

            when (builder.getTransitionType()) {
                TransitionType.ADD -> ft.add(view.id, fragment!!, tag)
                TransitionType.SHOW -> ft.show(fragment!!)
                TransitionType.HIDE -> ft.hide(fragment!!)
                else -> ft.replace(view.id, fragment!!, tag)
            }

            /**
             * commit() changed to commitAllowingStateLoss() for "Can not perform this action after onSaveInstanceState"
             */
            ft.commitAllowingStateLoss()
        }
    }

    fun showLockScreen() {
        if (dgLockScreen == null) {
            dgLockScreen = DGProgress(this)
        }

        if (!dgLockScreen!!.isShowing) {
            dgLockScreen!!.show()
        }
    }

    fun hideLockScreen() {
        if (dgLockScreen != null && dgLockScreen!!.isShowing) {
            dgLockScreen!!.dismiss()
        }
    }
}