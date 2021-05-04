package com.spacedelivery.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spacedelivery.main.ACMainViewModel
import com.spacedelivery.mission.ACMissionViewModel
import com.spacedelivery.mission.FRFavoriteViewModel
import com.spacedelivery.mission.FRMissionViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Deniz KATIPOGLU on 17,December,2020
 */
@Module
abstract class AppViewModels {


    /**
     * HOME
     */
    @Binds
    @IntoMap
    @ViewModelKey(ACMainViewModel::class)
    abstract fun bindMainViewModel(repoViewModel: ACMainViewModel): ViewModel

    /**
     * MISSION
     */
    @Binds
    @IntoMap
    @ViewModelKey(ACMissionViewModel::class)
    abstract fun bindACMissionViewModel(repoViewModel: ACMissionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FRMissionViewModel::class)
    abstract fun bindFRMissionViewModel(repoViewModel: FRMissionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FRFavoriteViewModel::class)
    abstract fun bindFRFavoriteViewModel(repoViewModel: FRFavoriteViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}