package com.mycode.weatherapp.Database;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.mycode.weatherapp.entity.Weather;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public abstract class WeatherDatabaseTest {

    private WeatherDatabase weatherDatabase;

    public WeatherDAO getWeatherDAO(){
        return weatherDatabase.weatherDAO();
    }

    @Before
    public void init(){
        //mock database temporary for testing calling in memory database builder which will construct a database
        //only as long as the application is alive
        //application provider references application context
        weatherDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                WeatherDatabase.class).build();
    }

    @After
    public void finish(){
        weatherDatabase.close();
    }

}