package com.ayat.candyapp.login.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 *Created by Ayat Khriasat on 26,April,2019 at 10:57 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
object LoginModels {
    data class LoginRequestModel(val username: String, val password: String)
    data class LoginResponseModel(val Authorization: String)
}
