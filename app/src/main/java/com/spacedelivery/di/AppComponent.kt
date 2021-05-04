package com.spacedelivery.di

import com.spacedelivery.base.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Deniz KATIPOGLU on 17,December,2020
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    AppPages::class,
    AppViewModels::class
])
interface AppComponent{

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder
        fun build(): AppComponent
    }
    fun inject(application: App?)


}