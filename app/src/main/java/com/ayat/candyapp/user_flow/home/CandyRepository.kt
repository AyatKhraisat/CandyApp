package com.ayat.candyapp.user_flow.home


import com.ayat.candyapp.UserPreferences
import com.ayat.candyapp.bases.BaseResponse
import com.ayat.candyapp.user_flow.login.model.LoginModels
import com.ayat.candyapp.network.EndPoint
import com.ayat.candyapp.user_flow.signup.models.SignUpRequestModel
import kotlinx.coroutines.Deferred

import javax.inject.Inject
import javax.inject.Singleton

/**
 *Created by Ayat Khriasat on 26,April,2019 at 10:57 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
@Singleton
class CandyRepository @Inject
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

//    fun getCandyList(: Deferred<LoginModels.LoginResponseModel> {
//        return endPoint.loginAsync(
//            LoginModels.LoginRequestModel(userName, password)
//        );
//    }




}
