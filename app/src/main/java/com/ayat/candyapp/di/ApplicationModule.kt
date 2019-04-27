package com.ayat.candyapp.di

import android.content.Context
import com.ayat.candyapp.App
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class ApplicationModule(private val application: App) {


    @Singleton
    @Qualifiers.ApplicationContext
    @Provides
    fun provideApplicationContext(): Context = application

}
