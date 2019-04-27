package com.ayat.candyapp.di

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 *Created by Ayat Khriasat on 27,April,2019 at 8:32 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
@Module
class UiControllerModule(private val context: Context) {

    @Provides
    @Qualifiers.ActivityContext
    @ViewScope
    fun provideContext(): Context {
        return context
    }

}
