package com.mycode.weatherapp.network;

import com.mycode.weatherapp.UtilTest;
import com.mycode.weatherapp.entity.Weather;
import com.mycode.weatherapp.ui.Resource;

import org.intellij.lang.annotations.Flow;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import io.reactivex.Flowable;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.eq;

class WeatherAPITest {

    private final String key = "97357a1cc59a1a427e9ade54f72a10b9";
    private final String time = "[YYYY]-[MM]-[DD]T[HH]:[MM]:[SS]";
    @Mock
    WeatherAPI weatherAPI;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCurrentWeatherAPITest(){
        final Weather weather = new Weather();
        final Flowable<Weather> returnData = Flowable.just(weather);
        Mockito.when(weatherAPI.getCurrentWeather(eq(key),Mockito.anyDouble(),Mockito.anyDouble())).thenReturn(returnData);

        final Weather returnVal = weatherAPI.getCurrentWeather(key,0.0,0.0).blockingFirst();

        Mockito.verify(weatherAPI).getCurrentWeather(eq(key),Mockito.anyDouble(),Mockito.anyDouble());
        Mockito.verifyNoMoreInteractions(weatherAPI);

        Assertions.assertNotNull(returnVal);
    }

    @Test
    public void getDailyWeatherAPITest(){
        final Weather weather = new Weather();
        final Flowable<Weather> returnData = Flowable.just(weather);
        Mockito.when(weatherAPI.getFutureWeather(eq(key),Mockito.anyDouble(),Mockito.anyDouble(),Mockito.anyString())).thenReturn(returnData);

        final Weather returnVal = weatherAPI.getFutureWeather(key,0.0,0.0,time).blockingFirst();

        Mockito.verify(weatherAPI).getFutureWeather(eq(key),Mockito.anyDouble(),Mockito.anyDouble(),eq(time));
        Mockito.verifyNoMoreInteractions(weatherAPI);

        Assertions.assertNotNull(returnVal);
    }

}