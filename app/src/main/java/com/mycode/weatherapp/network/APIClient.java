package com.mycode.weatherapp.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {


    public static Retrofit getClient()
    {
        HttpLoggingInterceptor intercept = new HttpLoggingInterceptor();
        intercept.setLevel((HttpLoggingInterceptor.Level.BODY)) ;
        OkHttpClient client  = new OkHttpClient.Builder().addInterceptor(intercept).build();
        Retrofit retro = new Retrofit.Builder()
                .baseUrl("https://api.darksky.net/forecast/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        return retro;
    }
}
