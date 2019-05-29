package com.mycode.weatherapp.Database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mycode.weatherapp.entity.CurrentWeather;
import com.mycode.weatherapp.entity.DailyWeather;
import com.mycode.weatherapp.entity.DailyWeatherData;
import com.mycode.weatherapp.entity.Weather;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface WeatherDAO{

    @Query("SELECT * FROM currentWeatherTable")
    LiveData<CurrentWeather> getAllCurrentData();

    @Insert
    Single<Long> addCurrentData(CurrentWeather weather);

    @Query("DELETE FROM currentWeatherTable")
    Single<Integer> removeAllCurrentData();

    @Query("SELECT * FROM dailyWeatherTable")
    LiveData<List<DailyWeatherData>> getAllDailyData();

    @Insert
    Single<Long> addDailyData(DailyWeatherData weather);

    @Query("DELETE FROM dailyWeatherTable")
    Single<Integer> removeAllDailyData();

}
