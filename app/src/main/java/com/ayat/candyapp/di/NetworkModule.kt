package com.ayat.candyapp.di

import android.content.Context

import com.ayat.candyapp.App
import com.ayat.candyapp.network.CustomOkHttpClient
import com.ayat.candyapp.network.EndPoint
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.annotations.NonNull
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton
/**
 *Created by Ayat Khriasat on 27,April,2019 at 8:32 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
@Module
class NetworkModule(private val baseUrl: String) {

    @Provides
    @Singleton
    @NonNull
     fun provideOkHttpClient(@Qualifiers.ApplicationContext context: Context): OkHttpClient {
        return CustomOkHttpClient.getCustomOkHttpClient(context as App)
    }


    @Provides
    @Singleton
    @NonNull
     fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())

            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @NonNull
     fun provideMovieService(retrofit: Retrofit): EndPoint {
        return retrofit.create(EndPoint::class.java)

    }
}