package com.mycode.weatherapp.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mycode.weatherapp.entity.CurrentWeather;
import com.mycode.weatherapp.entity.DailyWeather;
import com.mycode.weatherapp.entity.DailyWeatherData;
import com.mycode.weatherapp.entity.Weather;
import com.mycode.weatherapp.network.APIClient;
import com.mycode.weatherapp.network.WeatherAPI;
import com.mycode.weatherapp.ui.Resource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    private int delay = 0;
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public static int counter = 0;
    public static Boolean noWifi = true;
    private WeatherDAO weatherDAO;
    private LiveData<CurrentWeather> allCurrentData;
    private LiveData<List<DailyWeatherData>> allDailyData;
    private WeatherDatabase database;
    public Repository(WeatherDAO weatherDAO) { //for testing
        this.weatherDAO = weatherDAO;
    }

    public Repository(Application app){
        database = WeatherDatabase.getInstance(app);
        weatherDAO = database.weatherDAO();
        allCurrentData = weatherDAO.getAllCurrentData();
        allDailyData = weatherDAO.getAllDailyData();
    }

    public Flowable<Resource<Integer>> insertCurrentData(final CurrentWeather currentWeather){
        return weatherDAO.addCurrentData(currentWeather).delaySubscription(delay, timeUnit)
                .map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long aLong) throws Exception {
                        long l = aLong;
                        return (int)l;
                    }
                })
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) throws Exception {
                        return -1;
                    }
                })
                .map(new Function<Integer, Resource<Integer>>() {
                    @Override
                    public Resource<Integer> apply(Integer integer) throws Exception {
                        if(integer>0){
                            return Resource.success(integer, "success");
                        }
                        return Resource.error(null, "failure");
                    }
                })
                .subscribeOn(Schedulers.io()).toFlowable();
    }

    public Flowable<Resource<Integer>> deleteCurrentData(){
        return weatherDAO.removeAllCurrentData().delaySubscription(delay, timeUnit)
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) throws Exception {
                        return -1;
                    }
                })
                .map(new Function<Integer, Resource<Integer>>() {
                    @Override
                    public Resource<Integer> apply(Integer integer) throws Exception {
                        if(integer>0){
                            return Resource.success(integer, "success");
                        }
                        return Resource.error(null, "failure");
                    }
                })
                .subscribeOn(Schedulers.io()).toFlowable();
    }

    public LiveData<CurrentWeather> getAllCurrentData(){
        return allCurrentData;
    }


    public Flowable<Resource<Integer>> addDailyData(final DailyWeatherData dailyWeather){
        return weatherDAO.addDailyData(dailyWeather).delaySubscription(delay, timeUnit)
                .map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long aLong) throws Exception {
                        long l = aLong;
                        return (int)l;
                    }
                })
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) throws Exception {
                        return -1;
                    }
                })
                .map(new Function<Integer, Resource<Integer>>() {
                    @Override
                    public Resource<Integer> apply(Integer integer) throws Exception {
                        if(integer>0){
                            return Resource.success(integer, "success");
                        }
                        return Resource.error(null, "failure");
                    }
                })
                .subscribeOn(Schedulers.io()).toFlowable();
    }

    public Flowable<Resource<Integer>> deleteDailyData(){
        return weatherDAO.removeAllDailyData().delaySubscription(delay, timeUnit)
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) throws Exception {
                        return -1;
                    }
                })
                .map(new Function<Integer, Resource<Integer>>() {
                    @Override
                    public Resource<Integer> apply(Integer integer) throws Exception {
                        if(integer>0){
                            return Resource.success(integer, "success");
                        }
                        return Resource.error(null, "failure");
                    }
                })
                .subscribeOn(Schedulers.io()).toFlowable();
    }

    public LiveData<List<DailyWeatherData>> getAllDailyData(){
        return allDailyData;
    }

}
