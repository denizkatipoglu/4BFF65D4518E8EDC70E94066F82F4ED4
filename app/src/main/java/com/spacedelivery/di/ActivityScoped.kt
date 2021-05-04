package com.spacedelivery.di

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by Deniz KATIPOGLU on 17,December,2020
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityScoped