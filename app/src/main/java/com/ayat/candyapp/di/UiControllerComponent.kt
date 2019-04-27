package com.ayat.candyapp.di


import com.ayat.candyapp.login.LoginActivity
import dagger.Component
@ViewScope
@Component(dependencies = [ApplicationComponent::class], modules = [UiControllerModule::class,FactoryModule::class])
interface UiControllerComponent {

    fun inject(loginActivity: LoginActivity)

}