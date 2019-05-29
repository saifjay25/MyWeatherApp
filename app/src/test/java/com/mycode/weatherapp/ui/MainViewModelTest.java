package com.mycode.weatherapp.ui;

import android.app.Application;

import com.mycode.weatherapp.Database.Repository;
import com.mycode.weatherapp.InstantExecutorExtends;
import com.mycode.weatherapp.LiveDataTest;
import com.mycode.weatherapp.UtilTest;
import com.mycode.weatherapp.entity.CurrentWeather;
import com.mycode.weatherapp.entity.DailyWeatherData;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Flowable;
import io.reactivex.internal.operators.single.SingleToFlowable;

@ExtendWith(InstantExecutorExtends.class)
class MainViewModelTest {

    private MainViewModel mainViewModel;
    @Mock
    private Repository repository;
    @Mock
    private Application application;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        mainViewModel = new MainViewModel(repository, application);
    }
    @Test
    void insertCurrentWeatherReturnNull() throws Exception{
        CurrentWeather currentWeather = new CurrentWeather(UtilTest.currentWeather1);
        LiveDataTest<Resource<Integer>> liveDataTest = new LiveDataTest<>();
        final int insertRow = 1;
        Flowable<Resource<Integer>> returnData = SingleToFlowable.just(Resource.success(insertRow,"success"));
        Mockito.when(repository.insertCurrentData(Mockito.any(CurrentWeather.class))).thenReturn(returnData);

        Resource<Integer> returnVal = liveDataTest.getValue(mainViewModel.addCurrentWeather(currentWeather));

        Assertions.assertEquals(Resource.success(insertRow,"success"),returnVal);
    }

    @Test
    void deleteCurrentWeatherReturnNull() throws Exception{
        LiveDataTest<Resource<Integer>> liveDataTest = new LiveDataTest<>();
        final int insertRow = 1;
        Flowable<Resource<Integer>> returnData = SingleToFlowable.just(Resource.success(insertRow,"success"));
        Mockito.when(repository.deleteCurrentData()).thenReturn(returnData);

        Resource<Integer> returnVal = liveDataTest.getValue(mainViewModel.deleteCurrentWeather());

        Assertions.assertEquals(Resource.success(insertRow,"success"),returnVal);
    }

    @Test
    void insertDailyWeatherReturnNull() throws Exception{
        DailyWeatherData dailyWeatherData = new DailyWeatherData(UtilTest.dailyWeatherData1);
        LiveDataTest<Resource<Integer>> liveDataTest = new LiveDataTest<>();
        final int insertRow = 1;
        Flowable<Resource<Integer>> returnData = SingleToFlowable.just(Resource.success(insertRow,"success"));
        Mockito.when(repository.addDailyData(Mockito.any(DailyWeatherData.class))).thenReturn(returnData);

        Resource<Integer> returnVal = liveDataTest.getValue(mainViewModel.addDailyWeather(dailyWeatherData));

        Assertions.assertEquals(Resource.success(insertRow,"success"),returnVal);
    }

    @Test
    void deleteDailyWeatherReturnNull() throws Exception{
        LiveDataTest<Resource<Integer>> liveDataTest = new LiveDataTest<>();
        final int insertRow = 1;
        Flowable<Resource<Integer>> returnData = SingleToFlowable.just(Resource.success(insertRow,"success"));
        Mockito.when(repository.deleteDailyData()).thenReturn(returnData);

        Resource<Integer> returnVal = liveDataTest.getValue(mainViewModel.deleteDailyWeather());

        Assertions.assertEquals(Resource.success(insertRow,"success"),returnVal);
    }

}