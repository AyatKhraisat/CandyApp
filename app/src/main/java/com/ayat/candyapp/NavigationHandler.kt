package com.ayat.candyapp

import android.content.Context
import android.content.Intent
import com.ayat.candyapp.di.Qualifiers
import com.ayat.candyapp.user_flow.login.HomeActivity
import javax.inject.Singleton

/**
 *Created by Ayat Khriasat on 27,April,2019 at 8:32 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
@Singleton
class NavigationHandler constructor(
    @Qualifiers.ApplicationContext private val context: Context) {

    fun launchLoginActivity(context: Context) {
        val intent = Intent(context, HomeActivity::class.java)
        context.startActivity(intent)
    }

}