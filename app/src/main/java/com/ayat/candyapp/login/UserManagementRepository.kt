package com.ayat.candyapp.login


import com.ayat.candyapp.UserPreferences
import com.ayat.candyapp.login.model.LoginModels
import com.ayat.candyapp.network.EndPoint
import io.reactivex.Single

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ayat khraisat  on 12/27/18
 * akhraisat@blessedtreeit.com
 *
 *
 * Project Name: ActivityCenter
 *
 *
 * Blessed Tree IT
 */

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

    fun getLoginObservable(userName: String, password: String): Single<LoginModels.LoginResponseModel> {
        return endPoint.login(
            LoginModels.LoginRequestModel(userName, password)
        )
            .doOnSuccess { (token) ->
                //                    if (loginModel.isSuccess()) {
                //                        setUserName(loginModel.getFullName());
                //                    }
            }
    }


}
