package com.mycode.weatherapp.persistence;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.mycode.weatherapp.LiveDataTest;
import com.mycode.weatherapp.UtilTest;
import com.mycode.weatherapp.entity.CurrentWeather;
import com.mycode.weatherapp.entity.DailyWeatherData;

import org.junit.Rule;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class WeatherDAOTest extends WeatherDatabaseTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Test
    public void insertGetRemoveAllForDailyWeatherData() throws Exception{
        DailyWeatherData dailyWeatherData = new DailyWeatherData(UtilTest.dailyWeatherData1);
        //add data
        getWeatherDAO().addDailyData(dailyWeatherData);
        //get data
        LiveDataTest<List<DailyWeatherData>> liveDataTest = new LiveDataTest<>();
        List<DailyWeatherData> insertedNotes = liveDataTest.getValue(getWeatherDAO().getAllDailyData());

        assertNotNull(insertedNotes);

        dailyWeatherData.setId(insertedNotes.get(0).getId());
        assertEquals(dailyWeatherData, insertedNotes.get(0));

        //removeAll
        getWeatherDAO().removeAllDailyData();
        insertedNotes = liveDataTest.getValue(getWeatherDAO().getAllDailyData());
        assertEquals(0, insertedNotes.size());

    }

    @Test
    public void insertGetRemoveAllForCurrentWeatherData() throws Exception{
        CurrentWeather currentWeather = new  CurrentWeather(UtilTest.currentWeather1);
        //add data
        getWeatherDAO().addCurrentData(currentWeather).blockingGet();

        //get data
        LiveDataTest<CurrentWeather> liveDataTest = new LiveDataTest<>();
        CurrentWeather insertedNotes = liveDataTest.getValue(getWeatherDAO().getAllCurrentData());

        assertNotNull(insertedNotes);

        currentWeather.setId(insertedNotes.getId());
        assertEquals(currentWeather, insertedNotes);

        //removeAll
        getWeatherDAO().removeAllCurrentData().blockingGet();
        insertedNotes = liveDataTest.getValue(getWeatherDAO().getAllCurrentData());
        assertNull(insertedNotes);

    }
}