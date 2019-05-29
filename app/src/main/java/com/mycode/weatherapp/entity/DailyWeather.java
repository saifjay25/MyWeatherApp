package com.mycode.weatherapp.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyWeather {

    @SerializedName("data")
    List<DailyWeatherData> data = null;

    public List<DailyWeatherData> getData() {
        return data;
    }

    public void setData(List<DailyWeatherData> data) {
        this.data = data;
    }
}
