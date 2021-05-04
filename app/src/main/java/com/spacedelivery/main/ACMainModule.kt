package com.spacedelivery.main

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Deniz KATIPOGLU on 17,December,2020
 */
@Module
class ACMainModule {

    @Provides
    @Singleton
    fun provide(): TestA {
        return TestA()
    }
}