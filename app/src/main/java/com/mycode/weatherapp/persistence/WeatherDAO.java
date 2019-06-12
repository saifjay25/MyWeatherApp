package com.mycode.weatherapp.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mycode.weatherapp.entity.CurrentWeather;
import com.mycode.weatherapp.entity.DailyWeatherData;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface WeatherDAO{

    @Query("SELECT * FROM currentWeatherTable")
    LiveData<CurrentWeather> getAllCurrentData();

    @Query("SELECT COUNT(humidity) FROM currentWeatherTable")
    LiveData<Integer> getRowCount();

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
