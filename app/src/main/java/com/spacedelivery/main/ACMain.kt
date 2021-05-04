package com.spacedelivery.main

import android.content.Intent
import android.text.TextUtils
import android.widget.SeekBar
import android.widget.Toast
import androidx.lifecycle.Observer
import com.spacedelivery.base.App
import com.spacedelivery.base.App.Companion.db
import com.spacedelivery.R
import com.spacedelivery.base.BaseActivity
import com.spacedelivery.database.PlanetDB
import com.spacedelivery.database.SpaceName
import com.spacedelivery.mission.ACMissionPage
import com.spacedelivery.utils.Preferences
import kotlinx.android.synthetic.main.ac_main.*
import kotlinx.coroutines.*


/**
 * Created by Deniz KATIPOGLU on 03,MAY,2021
 */
class ACMain : BaseActivity<ACMainViewModel>(), SeekBar.OnSeekBarChangeListener {

    private val TOTAL_AMOUNT = 15
    private var powerPoint = 12
    private var spaceInfo: SpaceName? = null

    private val mAllProgress = intArrayOf(1, 1, 1)

    override fun getLayoutId(): Int {
        return R.layout.ac_main
    }

    override fun setListeners() {

        seekBar1.setOnSeekBarChangeListener(this)
        seekBar2.setOnSeekBarChangeListener(this)
        seekBar3.setOnSeekBarChangeListener(this)

        if (!Preferences.getBoolean(Preferences.Keys.SERVICE_ACTIVATED, false)) {
            viewModel.getPlanetService()
        }
        cvCont.setOnClickListener {

            if (TextUtils.isEmpty(etSpaceName.text)) {
                Toast.makeText(this, "Lütfen İsim Girin...", Toast.LENGTH_SHORT).show()
            } else if (seekBar1.progress + seekBar2.progress + seekBar3.progress < 15) {
                Toast.makeText(
                    this,
                    "Lütfen verilen puanların hepsini dağıtın.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                spaceInfo = SpaceName(
                    1, etSpaceName.text.toString(), seekBar3.progress.toFloat() * 10000,
                    seekBar1.progress.toFloat() * 10000, seekBar2.progress.toFloat() * 20,100
                )
                GlobalScope.launch {
                    if (db!!.TodoDao().getAll().isNotEmpty()) {
                        db!!.TodoDao().delete(spaceInfo!!)
                    }
                    db!!.TodoDao().insertAll(spaceInfo!!)
                }
                startActivity(Intent(this, ACMissionPage::class.java))
            }
        }
    }

    override fun setReceivers() {
        viewModel.onPlanetServiceListener.observe(this, Observer {
            GlobalScope.launch {
                Preferences.setBoolean(Preferences.Keys.SERVICE_ACTIVATED, true)
                for (i in 0 until it!!.size) {
                    db!!.TodoDao().insertAllPlanet(
                        PlanetDB(
                            i,
                            it[i].name,
                            it[i].coordinateX,
                            it[i].coordinateY,
                            it[i].capacity,
                            it[i].stock,
                            it[i].need,
                            App.getInstance()!!.distanceTwoPlanet(
                                it[0].coordinateX,
                                it[i].coordinateX,
                                it[0].coordinateY,
                                it[i].coordinateY
                            ),
                            "false"
                        )
                    )


                }
            }

        })
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
        val which = whichIsIt(seekBar!!.getId())

        val storedProgress = mAllProgress[which]

        if (seekBar.progress == 0) {
            seekBar.progress = 1
            return
        }
        if (seekBar.progress > storedProgress) {
            val remaining = remaining()
            if (remaining == 0) {
                seekBar.progress = storedProgress
                return
            } else {
                if (storedProgress + remaining >= seekBar.progress) {
                    mAllProgress[which] = seekBar.progress
                } else {
                    mAllProgress[which] = storedProgress + remaining
                }
            }
        } else if (seekBar.progress <= storedProgress) {
            mAllProgress[which] = seekBar.progress
        }

        var increaseValue = 0
        if (seekBar.progress < storedProgress) {
            increaseValue = storedProgress - seekBar.progress
            powerPoint += increaseValue
        } else {
            increaseValue = seekBar.progress - storedProgress
            powerPoint -= increaseValue
        }
        spacePoint.text = powerPoint.toString()

    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

    private fun remaining(): Int {
        var remaining = TOTAL_AMOUNT
        for (i in 0..2) {
            remaining -= mAllProgress[i]
        }
        if (remaining >= 15) {
            remaining = 15
        } else if (remaining <= 0) {
            remaining = 0
        }
        return remaining
    }

    private fun whichIsIt(id: Int): Int {
        return when (id) {
            R.id.seekBar1 -> 0
            R.id.seekBar2 -> 1
            R.id.seekBar3 -> 2
            else -> throw IllegalStateException(
                "There should be a Seekbar with this id($id)!"
            )
        }
    }

}