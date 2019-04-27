package com.ayat.candyapp.di

import android.app.Application
import android.content.Context

import com.ayat.candyapp.App
import com.ayat.candyapp.UserPreferences
import com.ayat.candyapp.login.UserManagementRepository
import com.ayat.candyapp.network.EndPoint
import dagger.BindsInstance
import dagger.Component

import javax.inject.Singleton

/**
 *Created by Ayat Khriasat on 27,April,2019 at 8:32 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    val endPoint: EndPoint

    val userPreferences: UserPreferences

    val userManagementRepository: UserManagementRepository

    fun inject(application: App)

    @Qualifiers.ApplicationContext
    fun getApplication(): Context
}
