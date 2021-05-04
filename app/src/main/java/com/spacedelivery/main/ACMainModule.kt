package com.spacedelivery.main

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ACMainModule {

    @Provides
    @Singleton
    fun provide(): TestA {
        return TestA()
    }
}