package com.ayat.candyapp.login.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by: Ahmed Al-Hashimi
 * Created on: Sep. 30, 2018.
 *
 *
 * aalhashimi@blessedtreeit.com
 *
 *
 * Project Name: BTIT-Dashboards-Android-V2.0
 *
 *
 * BTIT
 */

object LoginModels {
    data class LoginRequestModel(val userName: String, val password: String)
    data class LoginResponseModel(val token: String)
}
