package com.mycode.weatherapp.persistence;

import  android.app.Application;

import androidx.lifecycle.LiveData;

import com.mycode.weatherapp.entity.CurrentWeather;
import com.mycode.weatherapp.entity.DailyWeatherData;
import com.mycode.weatherapp.ui.Resource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class Repository {

    private int delay = 0;
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public static int counter = 0;
    public static Boolean noWifi = true;
    private WeatherDAO weatherDAO;

    //let dagger know that im doing constructor injection and setting the weather dao through a dagger dependency
    @Inject
    public Repository(WeatherDAO weatherDAO) { //for testing
        this.weatherDAO = weatherDAO;
    }

    public Flowable<Resource<Integer>> insertCurrentData(CurrentWeather currentWeather){
        return weatherDAO.addCurrentData(currentWeather)
                .map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long aLong){
                        long l = aLong;
                        return (int)l;
                    }
                })
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable){
                        return -1;
                    }
                })
                .map(new Function<Integer, Resource<Integer>>() {
                    @Override
                    public Resource<Integer> apply(Integer integer) {
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
        return weatherDAO.getAllCurrentData();
    }

    public LiveData<Integer> getcount(){
        return weatherDAO.getRowCount();
    }

    public Flowable<Resource<Integer>> addDailyData(DailyWeatherData dailyWeather){
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
        return weatherDAO.getAllDailyData() ;
    }

}
