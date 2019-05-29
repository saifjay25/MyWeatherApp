package com.mycode.weatherapp.entity;

import com.mycode.weatherapp.UtilTest;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class CurrentWeatherTest {

    @Test
    void isCurrentWeatherNotEqual_differentIds_returnTrue() throws Exception{
        CurrentWeather currentWeather1 = new CurrentWeather(UtilTest.currentWeather1);
        CurrentWeather currentWeather2 = new CurrentWeather(UtilTest.currentWeather2);
        Assert.assertNotEquals(currentWeather1, currentWeather2);
    }

}