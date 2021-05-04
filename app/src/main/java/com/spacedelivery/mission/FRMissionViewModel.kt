package com.spacedelivery.mission


import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.spacedelivery.base.BaseViewModel
import com.spacedelivery.models.PlanetModel
import com.spacedelivery.network.ApiClient
import com.spacedelivery.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class FRMissionViewModel @Inject constructor() : BaseViewModel() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


}