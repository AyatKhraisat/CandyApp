package com.ayat.candyapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ayat.candyapp.ViewModelFactory
import com.ayat.candyapp.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

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
abstract class FactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindMyViewModel(myViewModel: LoginViewModel): ViewModel

}