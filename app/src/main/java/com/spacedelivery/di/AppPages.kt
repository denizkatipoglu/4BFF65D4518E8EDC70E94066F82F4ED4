package com.spacedelivery.di

import com.spacedelivery.main.ACMain
import com.spacedelivery.main.ACMainModule
import com.spacedelivery.mission.ACMissionPage
import com.spacedelivery.mission.FRFavorite
import com.spacedelivery.mission.FRMission
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector


@Module(includes = [AndroidInjectionModule::class])
abstract class AppPages {

    /**
     * HOME
     */
    @ActivityScoped
    @ContributesAndroidInjector(modules = [ACMainModule::class])
    abstract fun bindMainActivity(): ACMain

    /**
     * MISSION
     */
    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindACMissionPage(): ACMissionPage

    /**
     * MISSION
     */
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindFRMission(): FRMission

    /**
     * FAVORITE
     */
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindFRFavorite(): FRFavorite



}