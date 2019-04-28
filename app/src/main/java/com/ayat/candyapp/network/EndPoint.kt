package com.ayat.candyapp.network


import com.ayat.candyapp.login.model.LoginModels
import io.reactivex.Single
import io.reactivex.annotations.NonNull
import retrofit2.http.Body
import retrofit2.http.POST

/**
 *Created by Ayat Khriasat on 26,April,2019 at 10:57 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
interface EndPoint {

    @POST("login")
    fun login(@NonNull @Body loginRequestModel: LoginModels.LoginRequestModel): Single<LoginModels.LoginResponseModel>

}
