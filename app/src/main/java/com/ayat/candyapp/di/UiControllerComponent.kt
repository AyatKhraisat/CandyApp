package com.ayat.candyapp.di


import com.ayat.candyapp.login.LoginActivity
import com.ayat.candyapp.signup.SignupActivity
import dagger.Component
/**
 *Created by Ayat Khriasat on 27,April,2019 at 8:32 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
@ViewScope
@Component(dependencies = [ApplicationComponent::class], modules = [UiControllerModule::class,ViewModelsModule::class])
interface UiControllerComponent {

    fun inject(loginActivity: LoginActivity)
    fun inject(signupActivity: SignupActivity)

}