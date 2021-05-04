package com.spacedelivery.di

import android.content.Context
import com.spacedelivery.base.App
import dagger.Module
import dagger.Provides

/**
 * Created by Deniz KATIPOGLU on 17,December,2020
 */
@Module
class AppModule {
    @Provides
    fun provideContext(application: App?): Context? {
        return application
    }
}