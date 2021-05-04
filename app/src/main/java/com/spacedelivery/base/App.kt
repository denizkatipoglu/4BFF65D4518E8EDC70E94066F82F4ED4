package com.spacedelivery.base

import android.app.*
import androidx.room.Room
import com.spacedelivery.database.AppDatabase
import com.spacedelivery.di.AppComponent

import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import com.spacedelivery.di.DaggerAppComponent
import kotlin.math.sqrt

/**
 * Created by Deniz KATIPOGLU on 17,December,2020
 */
class App : Application(), HasActivityInjector {

    lateinit var wikiComponent: AppComponent

    companion object {
        private var instance: App? = null
        var db: AppDatabase? = null

        fun getInstance(): App? {
            return instance
        }
    }

    @Inject
    lateinit var actInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        instance = this

        var appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()

        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "todo-list.db"
        ).fallbackToDestructiveMigration()
            .build()


        appComponent.inject(this)
    }


    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return actInjector
    }

    fun distanceTwoPlanet(x1:Double,x2:Double,y1:Double,y2:Double):Double{

        return  sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1))
    }
}