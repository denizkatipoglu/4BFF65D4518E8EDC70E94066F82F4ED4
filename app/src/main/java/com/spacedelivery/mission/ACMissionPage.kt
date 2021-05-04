package com.spacedelivery.mission

import android.os.Bundle
import androidx.lifecycle.Observer

import com.spacedelivery.R
import com.spacedelivery.base.BaseActivity
import com.spacedelivery.widget.Menu
import kotlinx.android.synthetic.main.ac_mission_page.*



class ACMissionPage : BaseActivity<ACMissionViewModel>() {


    override fun getLayoutId(): Int {
        return R.layout.ac_mission_page
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onTabItemSelected(Menu.HOME, container.id)
    }

    override fun setListeners() {
        bottomNav.setOnItemSelectedListener { menu ->
            viewModel.onTabItemSelected(menu, container.id)
        }
    }

    override fun setReceivers() {

        viewModel.onTabSelectionListener.observe(this, Observer { menu -> bottomNav.setSelection(menu!!) })
    }


}