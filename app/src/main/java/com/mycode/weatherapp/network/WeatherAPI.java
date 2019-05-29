package com.mycode.weatherapp.network;
import com.mycode.weatherapp.entity.Weather;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherAPI {

    @GET("{key}/{latitude},{longitude},{time}")
    Flowable<Weather> getFutureWeather(
            @Path("key") String key,
            @Path("latitude") Double latitude,
            @Path("longitude") Double longitude,
            @Path("time") String time
    );

    @GET("{key}/{latitude},{longitude}")
    Flowable<Weather> getCurrentWeather(
            @Path("key") String key,
            @Path("latitude") Double latitude,
            @Path("longitude") Double longitude
    );
}
