package com.mycode.weatherapp.entity;
import com.mycode.weatherapp.UtilTest;
import com.mycode.weatherapp.entity.CurrentWeather;
import com.mycode.weatherapp.entity.DailyWeatherData;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class DailyWeatherDataTest {

    @Test
    void isDailyWeatherNotEqual_differentIds_returnTrue() throws Exception{
        DailyWeatherData dailyWeatherData1 = new DailyWeatherData(UtilTest.dailyWeatherData1);
        DailyWeatherData dailyWeatherData2 = new DailyWeatherData(UtilTest.dailyWeatherData2);
        Assert.assertNotEquals(dailyWeatherData1, dailyWeatherData2);

    }
}
