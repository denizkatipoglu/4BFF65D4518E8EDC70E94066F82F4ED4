package com.spacedelivery.di

import android.content.Context
import com.spacedelivery.base.App
import dagger.Module
import dagger.Provides


@Module
class AppModule {
    @Provides
    fun provideContext(application: App?): Context? {
        return application
    }
}