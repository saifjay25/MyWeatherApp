package com.mycode.weatherapp.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mycode.weatherapp.entity.CurrentWeather;
import com.mycode.weatherapp.entity.DailyWeatherData;

import kotlin.jvm.Synchronized;

@Database(entities ={CurrentWeather.class, DailyWeatherData.class}, version = 18)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract WeatherDAO weatherDAO();
    private static WeatherDatabase instance = null;

    @Synchronized
    public static WeatherDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context,
                    WeatherDatabase.class, "database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }


}
