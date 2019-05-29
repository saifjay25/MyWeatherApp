package com.mycode.weatherapp.ui;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import com.mycode.weatherapp.Database.Repository;
import com.mycode.weatherapp.entity.CurrentWeather;
import com.mycode.weatherapp.entity.DailyWeatherData;
import com.mycode.weatherapp.entity.Weather;
import com.mycode.weatherapp.network.APIClient;
import com.mycode.weatherapp.network.WeatherAPI;

import java.util.List;

import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private WeatherAPI weatherAPI;
    private Repository repository;
    private LiveData<CurrentWeather> allCurrentData;
    private LiveData<List<DailyWeatherData>> allDailyData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allCurrentData = repository.getAllCurrentData();
        allDailyData = repository.getAllDailyData();
        this.weatherAPI = APIClient.getClient().create(WeatherAPI.class);
    }

    public MainViewModel(Repository repository, Application application) {
        super(application);
        this.repository = repository;
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

    public LiveData<Resource<Integer>> addCurrentWeather(CurrentWeather currentWeather){
        return LiveDataReactiveStreams.fromPublisher(
                repository.insertCurrentData(currentWeather)
        );
    }

    public LiveData<Resource<Integer>> deleteCurrentWeather(){
        return LiveDataReactiveStreams.fromPublisher(
                repository.deleteCurrentData()
        );
    }

    public LiveData<CurrentWeather> getAllCurrentData(){
        return allCurrentData;
    }


    public LiveData<Resource<Integer>> addDailyWeather(DailyWeatherData dailyWeatherData){
        return LiveDataReactiveStreams.fromPublisher(
                repository.addDailyData(dailyWeatherData)
        );
    }

    public LiveData<Resource<Integer>> deleteDailyWeather(){
        return LiveDataReactiveStreams.fromPublisher(
                repository.deleteDailyData()
        );
    }


    public LiveData<List<DailyWeatherData>> getAllDailyData(){
        return allDailyData;
    }

}
