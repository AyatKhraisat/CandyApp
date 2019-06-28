package com.ayat.candyapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

import com.ayat.candyapp.App
import com.ayat.candyapp.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

import javax.net.ssl.*
import java.io.File
import java.util.concurrent.TimeUnit

import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE

/**
 *Created by Ayat Khriasat on 26,April,2019 at 10:57 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
object CustomOkHttpClient {


    fun getCustomOkHttpClient(app: App): OkHttpClient {

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(provideHttpLoggingInterceptor())


        return builder.build()
    }


    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.d(message) })
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) BODY else NONE
        return httpLoggingInterceptor
    }


}