package com.spacedelivery.di

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by Deniz Katipoğlu on 29.07.2018.
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class FragmentScoped