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
 *Created by Ayat Khriasat on 27,April,2019 at 8:32 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
@Module
abstract class FactoryModule {

    @Binds
    @ViewScope
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewScope
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindMyViewModel(myViewModel: LoginViewModel): ViewModel

}
