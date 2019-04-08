package com.ayat.candyapp.di;

import android.content.Context;

import com.ayat.candyapp.App;
import com.ayat.candyapp.network.CustomOkHttpClient;
import com.ayat.candyapp.network.EndPoint;
import dagger.Module;
import dagger.Provides;
import io.reactivex.annotations.NonNull;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

@Module
public class NetworkModule {


    private String baseUrl;


   public NetworkModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    @NonNull
    OkHttpClient provideOkHttpClient(@Qualifiers.ApplicationContext Context context) {
        return CustomOkHttpClient.getCustomOkHttpClient((App) context);
    }


    @Provides
    @Singleton
    @NonNull
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    @NonNull
    EndPoint provideMovieService(Retrofit retrofit) {
        return retrofit.create(EndPoint.class);

    }
}