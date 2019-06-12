package com.mycode.weatherapp.persistence;

import com.mycode.weatherapp.UtilTest;
import com.mycode.weatherapp.entity.CurrentWeather;
import com.mycode.weatherapp.entity.DailyWeatherData;
import com.mycode.weatherapp.ui.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;

class RepositoryTest {

    private static final CurrentWeather weather = new CurrentWeather(UtilTest.currentWeather1);
    private static final DailyWeatherData dailytweather = new DailyWeatherData(UtilTest.dailyWeatherData1);
    private Repository repository;
    @Mock
    private WeatherDAO weatherDAO;
    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        repository = new Repository(weatherDAO);
    }

    @Test
    void insertCurrentData_returnRow() throws Exception{
        final long insertRow  =1L;
        final Single<Long> returnData = Single.just(insertRow);
        Mockito.when(weatherDAO.addCurrentData(Mockito.any(CurrentWeather.class))).thenReturn(returnData);

        final Resource<Integer> valueReturn = repository.insertCurrentData(weather).blockingFirst();

        Mockito.verify(weatherDAO).addCurrentData(Mockito.any(CurrentWeather.class));
        Mockito.verifyNoMoreInteractions(weatherDAO);

        System.out.println(valueReturn.data);
        Assertions.assertEquals(Resource.success(1,"success"), valueReturn);
    }

    @Test
    void deleteCurrentData_returnRow() throws Exception{
        final int deleted= 1;
        Mockito.when(weatherDAO.removeAllCurrentData()).thenReturn(Single.just(deleted));

        final Resource<Integer> returnVal = repository.deleteCurrentData().blockingFirst();

        Mockito.verify(weatherDAO).removeAllCurrentData();
        Mockito.verifyNoMoreInteractions(weatherDAO);

        Assertions.assertEquals(Resource.success(1,"success"), returnVal);
    }

    @Test
    void addDailyData_returnRow() throws Exception{
        final long insert= 1;
        final Single<Long> returnData = Single.just(insert);
        Mockito.when(weatherDAO.addDailyData(Mockito.any(DailyWeatherData.class))).thenReturn(returnData);

        final Resource<Integer> returnVal = repository.addDailyData(dailytweather).blockingFirst();

        Mockito.verify(weatherDAO).addDailyData(Mockito.any(DailyWeatherData.class));
        Mockito.verifyNoMoreInteractions(weatherDAO);

        Assertions.assertEquals(Resource.success(1,"success"), returnVal);
    }

    @Test
    void deleteDailyData_returnRow() throws Exception{
        final int deleted= 1;
        Mockito.when(weatherDAO.removeAllDailyData()).thenReturn(Single.just(deleted));

        final Resource<Integer> returnVal = repository.deleteDailyData().blockingFirst();

        Mockito.verify(weatherDAO).removeAllDailyData();
        Mockito.verifyNoMoreInteractions(weatherDAO);

        Assertions.assertEquals(Resource.success(1,"success"), returnVal);
    }
}