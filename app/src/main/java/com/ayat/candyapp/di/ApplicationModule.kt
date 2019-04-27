package com.ayat.candyapp.di

import android.content.Context
import com.ayat.candyapp.App
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

/**
 *Created by Ayat Khriasat on 27,April,2019 at 8:32 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
@Module
class ApplicationModule(private val application: App) {


    @Singleton
    @Qualifiers.ApplicationContext
    @Provides
    fun provideApplicationContext(): Context = application

}
