package com.ayat.candyapp.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ayat.candyapp.App;
import com.ayat.candyapp.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

import javax.net.ssl.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

/**
 * Created by Ayat khraisat  on 01/05/18
 * akhraisat@blessedtreeit.com
 * <p>
 * Project Name: ActivityCenter
 * <p>
 * Blessed Tree IT
 */

public class CustomOkHttpClient {


    public static OkHttpClient getCustomOkHttpClient(App app) {

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(provideHttpLoggingInterceptor());
            builder.addNetworkInterceptor(new StethoInterceptor());


            return builder.build();
    }


    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Timber.d(message);
                    }
                });
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? BODY : NONE);
        return httpLoggingInterceptor;
    }



}