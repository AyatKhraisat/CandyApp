package com.ayat.candyapp.login


import com.ayat.candyapp.UserPreferences
import com.ayat.candyapp.login.model.LoginModels
import com.ayat.candyapp.network.EndPoint
import io.reactivex.Single
import kotlinx.coroutines.Deferred

import javax.inject.Inject
import javax.inject.Singleton

/**
 *Created by Ayat Khriasat on 26,April,2019 at 10:57 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
@Singleton
class UserManagementRepository @Inject
constructor(private val endPoint: EndPoint, private val userPreferences: UserPreferences) {

    var userName: String
        get() = userPreferences.userName
        set(name) {
            userPreferences.userName = name
        }

    var isLoggedIn: Boolean
        get() = userPreferences.isLoggedIn
        set(isLoggedIn) {
            userPreferences.isLoggedIn = isLoggedIn
        }

    fun getLoginDeferrede(userName: String, password: String): Deferred<LoginModels.LoginResponseModel> {
        return endPoint.login(
            LoginModels.LoginRequestModel(userName, password)
        );
    }


}
