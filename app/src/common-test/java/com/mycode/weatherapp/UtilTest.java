package com.mycode.weatherapp;

import com.mycode.weatherapp.entity.CurrentWeather;
import com.mycode.weatherapp.entity.DailyWeatherData;

public class UtilTest {
    public static final DailyWeatherData dailyWeatherData1 = new DailyWeatherData(1,"s",
            "s","s","s","s","s","s","s",
            "s","s","s","s","s","s","s","s",
            "s","s","s");
    public static final DailyWeatherData dailyWeatherData2 = new DailyWeatherData(2,"a",
            "a","a","a","a","a","a","a",
            "a","a","a","a","a","a","a","a",
            "a","a","a");
    public static final CurrentWeather currentWeather1 = new CurrentWeather(1,"s","s","s","s","s");
    public static final CurrentWeather currentWeather2 = new CurrentWeather(2,"a","s","s","s","s");
}
