package com.spacedelivery.utils

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.spacedelivery.base.App

class Preferences {

    object Keys {
        const val PLANET_LIST = "PLANET_LIST"
        const val SERVICE_ACTIVATED = "service_activated"
    }

    companion object {
        // --- Objects --- //
        private var pref: SharedPreferences? = null

        private fun getPreferences(): SharedPreferences {
            if (pref == null) {
                val context = App.getInstance()!!.applicationContext
                pref = PreferenceManager.getDefaultSharedPreferences(context)
                pref!!.edit().commit()
            }
            return pref as SharedPreferences
        }

        fun setBoolean(key: String, newValue: Boolean?) {
            val editor = getPreferences().edit()
            editor.putBoolean(key, newValue!!)
            editor.commit()
        }

        fun getBoolean(key: String, defValue: Boolean): Boolean {
            return getPreferences().getBoolean(key, defValue)
        }



    }
}