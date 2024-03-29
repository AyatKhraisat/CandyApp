package com.ayat.candyapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ayat.candyapp.ViewModelFactory
import com.ayat.candyapp.user_flow.home.HomeViewModel
import com.ayat.candyapp.user_flow.login.LoginViewModel
import com.ayat.candyapp.user_flow.signup.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 *Created by Ayat Khriasat on 27,April,2019 at 8:32 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
@Module
abstract class ViewModelsModule {

    @Binds
    @ViewScope
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewScope
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewScope
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewScope
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignUpViewModel(signupViewModel: SignUpViewModel): ViewModel


}
