package com.spacedelivery.network

import com.spacedelivery.models.PlanetModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Deniz KATIPOGLU on 03,May,2021
 */
interface ApiService {


    @GET("e7211664-cbb6-4357-9c9d-f12bf8bab2e2")
    fun doGetListResources(): Call<ArrayList<PlanetModel>>?
}
