package com.spacedelivery.mission


import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.spacedelivery.base.BaseViewModel
import com.spacedelivery.widget.Menu
import javax.inject.Inject

class ACMissionViewModel @Inject constructor() : BaseViewModel() {

    lateinit var onTabSelectionListener: MutableLiveData<Menu>

    private var selectedTab = Menu.HOME
    private var homeFragment = FRMission.newInstance()
    private var favoriteFragment = FRFavorite.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onTabSelectionListener = MutableLiveData()
    }


    fun onTabItemSelected(menu: Menu, id: Int) {
        selectedTab = menu

        when (menu) {
            Menu.HOME -> {
                showFragment(homeFragment, id)
            }
            Menu.FAVORITE -> {
                showFragment(favoriteFragment, id)
            }

        }

        onTabSelectionListener.postValue(selectedTab)
    }

}