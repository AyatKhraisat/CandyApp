package com.ayat.candyapp.network


import com.ayat.candyapp.bases.BaseResponse
import com.ayat.candyapp.user_flow.home.models.CandyModel
import com.ayat.candyapp.user_flow.login.model.LoginModels
import com.ayat.candyapp.user_flow.signup.models.SignUpRequestModel
import io.reactivex.annotations.NonNull
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 *Created by Ayat Khriasat on 26,April,2019 at 10:57 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
interface EndPoint {

    @POST("login")
    fun loginAsync(@NonNull @Body loginRequestModel: LoginModels.LoginRequestModel)
            : Deferred<LoginModels.LoginResponseModel>

    @POST("sign-up")
    fun signUpAsync(@NonNull @Body signUpRequestModel: SignUpRequestModel)
            : Deferred<BaseResponse>

    @GET("candy")
    fun getCandyList(@NonNull @Header("Authorization") auth: String)
            : Deferred<List<CandyModel>>

    @POST("candy")
    fun addCandy(@NonNull @Header("Authorization") auth: String,@Body candyModel: CandyModel)
            : Deferred<BaseResponse>

}
