package com.ayat.candyapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

import com.ayat.candyapp.App
import com.ayat.candyapp.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

import javax.net.ssl.*
import java.io.File
import java.util.concurrent.TimeUnit

import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE

/**
 * Created by Ayat khraisat  on 01/05/18
 * akhraisat@blessedtreeit.com
 *
 *
 * Project Name: ActivityCenter
 *
 *
 * Blessed Tree IT
 */

object CustomOkHttpClient {


    fun getCustomOkHttpClient(app: App): OkHttpClient {

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(provideHttpLoggingInterceptor())
        builder.addNetworkInterceptor(StethoInterceptor())


        return builder.build()
    }


    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.d(message) })
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) BODY else NONE
        return httpLoggingInterceptor
    }


}