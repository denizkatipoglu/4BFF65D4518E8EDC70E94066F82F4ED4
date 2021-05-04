package com.spacedelivery.main


import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.spacedelivery.base.BaseViewModel
import com.spacedelivery.mission.FRMission
import com.spacedelivery.models.PlanetModel
import com.spacedelivery.network.ApiClient
import com.spacedelivery.network.ApiService
import com.spacedelivery.widget.Menu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ACMainViewModel @Inject constructor() : BaseViewModel() {

    var apiInterface: ApiService? = null
    lateinit var onPlanetServiceListener: MutableLiveData<ArrayList<PlanetModel>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onPlanetServiceListener = MutableLiveData()
    }

    fun getPlanetService() {
        apiInterface = ApiClient.getClient()!!.create(ApiService::class.java)

        val call: Call<ArrayList<PlanetModel>> = apiInterface!!.doGetListResources()!!
        call.enqueue(object : Callback<ArrayList<PlanetModel>> {
            override fun onFailure(call: Call<ArrayList<PlanetModel>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<PlanetModel>>,
                response: Response<ArrayList<PlanetModel>>
            ) {


                onPlanetServiceListener.postValue(response.body())
            }

        })

    }




}