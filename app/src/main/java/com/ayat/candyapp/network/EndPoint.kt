package com.ayat.candyapp.network


import com.ayat.candyapp.login.model.LoginModels
import io.reactivex.Single
import io.reactivex.annotations.NonNull
import retrofit2.http.Body
import retrofit2.http.POST

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
interface EndPoint {

    @POST("api/Account/Login")
    fun login(@NonNull @Body loginRequestModel: LoginModels.LoginRequestModel): Single<LoginModels.LoginResponseModel>

}
