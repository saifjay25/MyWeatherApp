package com.mycode.weatherapp.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.mycode.weatherapp.persistence.Repository;
import com.mycode.weatherapp.entity.CurrentWeather;
import com.mycode.weatherapp.entity.DailyWeatherData;
import com.mycode.weatherapp.entity.Weather;
import com.mycode.weatherapp.network.WeatherAPI;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private WeatherAPI weatherAPI;
    private Repository repository;
    private LiveData<CurrentWeather> allCurrentData;
    private LiveData<List<DailyWeatherData>> allDailyData;

    @Inject
    public MainViewModel(Repository repository, WeatherAPI weatherAPI) {
        this.repository = repository;
        allCurrentData = this.repository.getAllCurrentData();
        allDailyData = this.repository.getAllDailyData();
        this.weatherAPI = weatherAPI;
    }

    public LiveData<Weather> currentAPICall(String key, Double latitude, Double longitude){
        return LiveDataReactiveStreams.fromPublisher(
                weatherAPI.getCurrentWeather(key,latitude, longitude).subscribeOn(Schedulers.io())
        );
    }

    public LiveData<Weather> futureAPICall(String key, Double latitude, Double longitude, String time){
        return LiveDataReactiveStreams.fromPublisher(
                weatherAPI.getFutureWeather(key,latitude, longitude, time).subscribeOn(Schedulers.io())
        );
    }

    public Flowable<Resource<Integer>> addCurrentWeather(CurrentWeather currentWeather){
        return repository.insertCurrentData(currentWeather);
    }

    public Flowable<Resource<Integer>> deleteCurrentWeather(){
        return repository.deleteCurrentData();

    }

    public LiveData<Integer> getcount(){
        return repository.getcount();
    }

    public LiveData<CurrentWeather> getAllCurrentData(){
        return allCurrentData;
    }


    public Flowable<Resource<Integer>> addDailyWeather(DailyWeatherData dailyWeatherData){
        return  repository.addDailyData(dailyWeatherData);
    }

    public Flowable<Resource<Integer>> deleteDailyWeather(){
        return repository.deleteDailyData();
    }


    public LiveData<List<DailyWeatherData>> getAllDailyData(){
        return allDailyData;
    }

}
