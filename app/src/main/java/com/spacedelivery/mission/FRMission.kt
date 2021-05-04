package com.spacedelivery.mission

import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.spacedelivery.base.App
import com.spacedelivery.base.App.Companion.db
import com.spacedelivery.R
import com.spacedelivery.adapter.PlanetRecyclerViewAdapter
import com.spacedelivery.base.BaseFragment
import com.spacedelivery.database.PlanetDB
import com.spacedelivery.database.SpaceName
import com.spacedelivery.utils.LinearLayoutManagerWrapper
import kotlinx.android.synthetic.main.fr_mission.*
import kotlinx.coroutines.*


/**
 * Created by Deniz KATIPOGLU on 03,May,2021
 */

class FRMission : BaseFragment<FRMissionViewModel>() {

    var mLayoutManager: LinearLayoutManagerWrapper? = null
    var adapter: PlanetRecyclerViewAdapter? = null
    var planetModelList: ArrayList<PlanetDB>? = null
    var spaceInfo: SpaceName? = null
    var viewModelJob = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var countDown: CountDownTimer? = null
    var damagePoint = 0

    companion object {
        fun newInstance(): FRMission {
            return FRMission()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fr_mission
    }

    override fun setListeners() {
        mLayoutManager =
            LinearLayoutManagerWrapper(activity!!, LinearLayoutManager.HORIZONTAL, false)

        rvPlanet.layoutManager = mLayoutManager

        setSpaceInfo()
        var viewModelJob = Job()
        val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

        uiScope.launch {
            withContext(Dispatchers.IO) {
                planetModelList = arrayListOf()
                planetModelList!!.addAll(db!!.TodoDao().getAllPlanet())
            }

            withContext(Dispatchers.Main) {
                setPlanetAdapter(planetModelList)

            }

        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (adapter != null) {
                    adapter!!.filter(p0.toString())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }

    override fun setReceivers() {

    }


    private fun setSpaceInfo() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                spaceInfo = db!!.TodoDao().getAll()[0]
                withContext(Dispatchers.Main) {
                    spaceName.text = spaceInfo!!.spaceName
                    spaceDamage.text = spaceInfo!!.damage.toString()
                    tvUGS.text = "UGS : " + String.format("%.2f", spaceInfo!!.capacity)
                    tvEUS.text = "EUS : " + String.format("%.2f", spaceInfo!!.pace)
                    tvDS.text = "DS : " + String.format("%.2f", spaceInfo!!.durability)
                }
            }
            withContext(Dispatchers.Main) {
                if (spaceInfo!!.damage >=10) {
                    countDown = object : CountDownTimer(spaceInfo!!.durability.toLong(), 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            spaceTime.text = "time :" + (millisUntilFinished / 1000)
                        }

                        override fun onFinish() {
                            spaceInfo!!.damage -= 10
                            spaceDamage.text = spaceInfo!!.damage.toString()
                            GlobalScope.launch {
                                db!!.TodoDao().updateTodo(spaceInfo!!)
                            }

                            if (spaceInfo!!.damage >= 10) {
                                start()
                            } else {
                                rvPlanet.smoothScrollToPosition(0)
                            }

                        }
                    }.start()
                }else{
                    spaceTime.text = "time :0"
                }
            }
        }
    }

    private fun setPlanetAdapter(planetList: List<PlanetDB>?) {
        adapter = object : PlanetRecyclerViewAdapter(activity!!, planetList) {
            override fun onClickTravel(planetDB: PlanetDB) {
                if (spaceInfo!!.damage>= 10){
                    uiScope.launch {
                        withContext(Dispatchers.IO) {
                            if (spaceInfo!!.capacity - planetDB.need >= 0) {
                                spaceInfo!!.capacity -= planetDB.need
                                planetDB.need = 0
                                withContext(Dispatchers.Main) {
                                    tvUGS.text =
                                        "UGS : " + String.format("%.2f", spaceInfo!!.capacity)
                                }
                            } else {
                                planetDB.need -= spaceInfo!!.capacity.toInt()
                                withContext(Dispatchers.Main) {
                                    rvPlanet.smoothScrollToPosition(0)

                                    for (item in planetList!!) {
                                        item.eus = App.getInstance()!!.distanceTwoPlanet(
                                            planetList[planetList.size - 1].coordinateX,
                                            item.coordinateX,
                                            planetList[planetList.size - 1].coordinateY,
                                            item.coordinateY
                                        )
                                    }
                                    tvTravelPlanet.text = planetList[planetList.size - 1].name
                                    Toast.makeText(
                                        context,
                                        "Yeterli kapasite yok",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                                return@withContext

                            }
                            if (spaceInfo!!.pace - planetDB.eus >= 0) {
                                spaceInfo!!.pace -= planetDB.eus.toFloat()
                                planetDB.eus = 0.0
                                withContext(Dispatchers.Main) {
                                    tvEUS.text = "EUS : " + String.format("%.2f", spaceInfo!!.pace)
//                                spaceTime.text =  String.format("%.2f", spaceInfo!!.pace)+ " s"
                                }
                            } else {
                                planetDB.eus -= spaceInfo!!.pace
                                withContext(Dispatchers.Main) {
                                    rvPlanet.smoothScrollToPosition(0)
                                    for (item in planetList!!) {
                                        item.eus = App.getInstance()!!.distanceTwoPlanet(
                                            planetList[planetList.size - 1].coordinateX,
                                            item.coordinateX,
                                            planetList[planetList.size - 1].coordinateY,
                                            item.coordinateY
                                        )
                                    }
                                    tvTravelPlanet.text = planetList[planetList.size - 1].name
                                    Toast.makeText(context, "Yeterli eus yok", Toast.LENGTH_SHORT)
                                        .show()
                                }

                                return@withContext

                            }

                            for (item in planetList!!) {
                                item.eus = App.getInstance()!!.distanceTwoPlanet(
                                    planetDB.coordinateX,
                                    item.coordinateX,
                                    planetDB.coordinateY,
                                    item.coordinateY
                                )
                            }

                            withContext(Dispatchers.Main) {
                                tvTravelPlanet.text = planetDB.name
                                notifyDataSetChanged()
                            }

                        }

                    }
                }else{
                    Toast.makeText(context,"Hasar puanınınız yeterli değil!!",Toast.LENGTH_SHORT).show()
                }

            }

            override fun onClickFavorite(planetModel: PlanetDB, favoriteText: String) {
                GlobalScope.launch {
                    planetModel.favorite = favoriteText
                    db!!.TodoDao().updateTodoPlanet(planetModel)
                }
            }

        }
        rvPlanet.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        countDown!!.cancel()

    }
}