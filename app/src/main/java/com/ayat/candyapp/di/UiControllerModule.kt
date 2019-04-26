package com.ayat.candyapp.di

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Ayat khraisat  on 12/18/18
 * akhraisat@blessedtreeit.com
 *
 *
 * Project Name: PocMvp
 *
 *
 * Blessed Tree IT
 */

@Module
class UiControllerModule(private val context: Context) {

    @Provides
    @Qualifiers.ActivityContext
    @ViewScope
     fun provideContext(): Context {
        return context
    }

}
