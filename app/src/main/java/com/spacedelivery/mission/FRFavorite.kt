package com.spacedelivery.mission

import androidx.recyclerview.widget.LinearLayoutManager
import com.spacedelivery.base.App
import com.spacedelivery.R
import com.spacedelivery.adapter.FavoriteRecyclerViewAdapter
import com.spacedelivery.base.BaseFragment
import com.spacedelivery.database.PlanetDB
import com.spacedelivery.utils.LinearLayoutManagerWrapper
import kotlinx.android.synthetic.main.fr_favorite.*
import kotlinx.coroutines.*

/**
 * Created by Deniz KATIPOGLU on 03,May,2021
 */

class FRFavorite : BaseFragment<FRFavoriteViewModel>() {

    var mLayoutManager: LinearLayoutManagerWrapper? = null
    var adapter: FavoriteRecyclerViewAdapter? = null
    var planetFavoriteList: ArrayList<PlanetDB>? = null

    var viewModelJob = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    companion object {
        fun newInstance(): FRFavorite {
            return FRFavorite()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fr_favorite
    }

    override fun setListeners() {

        mLayoutManager =
            LinearLayoutManagerWrapper(activity!!, LinearLayoutManager.VERTICAL, false)
        rvFavorite.layoutManager = mLayoutManager

        setFavoriteAdapter()

    }

    override fun setReceivers() {

    }

    private fun setFavoriteAdapter() {
        var viewModelJob = Job()
        val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
        uiScope.launch {
            withContext(Dispatchers.IO) {
                planetFavoriteList = arrayListOf()
                App.db!!.TodoDao().getAllPlanet()
                for (item in App.db!!.TodoDao().getAllPlanet()) {
                    if (item.favorite == "true") {
                        planetFavoriteList!!.add(item)
                    }
                }
            }
            withContext(Dispatchers.Main) {
                if (planetFavoriteList != null && planetFavoriteList!!.size > 0) {
                    adapter =
                        object : FavoriteRecyclerViewAdapter(activity!!, planetFavoriteList!!) {
                            override fun onClickFavorite(
                                planetModel: PlanetDB,
                                favoriteText: String
                            ) {
                                uiScope.launch {
                                    withContext(Dispatchers.IO){
                                        planetModel.favorite = favoriteText
                                        App.db!!.TodoDao().updateTodoPlanet(planetModel)
                                    }
                                    withContext(Dispatchers.Main){
                                        planetFavoriteList!!.remove(planetModel)
                                        notifyDataSetChanged()
                                    }
                                }
                            }

                        }
                    rvFavorite.adapter = adapter
                }
            }
        }

    }

}