package com.mycode.weatherapp.entity;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "currentWeatherTable")
public class CurrentWeather {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @SerializedName("summary")
    private String summary = "";

    @SerializedName("temperature")
    private String temperature = "";

    @SerializedName("windSpeed")
    private String windSpeed = "";

    @SerializedName("humidity")
    private String humidity = "";

    @SerializedName("apparentTemperature")
    private String apparentTemp = "";

    @SerializedName("precipType")
    private String precipType = "";

    @SerializedName("precipProbability")
    private String precipProbability = "";

    public String getPrecipType() {
        if(precipType==null){
            precipType = "N/A";
        }
        return precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    public String getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(String precipProbability) {
        this.precipProbability = precipProbability;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getApparentTemp() {
        return apparentTemp;
    }

    public void setApparentTemp(String apparentTemp) {
        this.apparentTemp = apparentTemp;
    }
    
    public CurrentWeather(int id, String summary, String humidity, String windSpeed, String temperature, String apparentTemp) {
        this.id = id;
        this.temperature = temperature;
        this.apparentTemp = apparentTemp;
        this.summary = summary;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public CurrentWeather(CurrentWeather currentWeather){
        this.apparentTemp = currentWeather.apparentTemp;
        this.temperature = currentWeather.temperature;
        this.humidity = currentWeather.humidity;
        this.summary = currentWeather.summary;
        this.windSpeed = currentWeather.windSpeed;
        this.id = currentWeather.id;
    }
    
    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == null){
            return false;
        }

        if(getClass() != obj.getClass()){
            return false;
        }

        CurrentWeather data =(CurrentWeather) obj;
        return data.getId() == getId() && data.getWindSpeed().equals(getWindSpeed()) && data.getSummary().equals(getSummary())
                && data.getTemperature().equals(getTemperature()) && data.getApparentTemp().equals(getApparentTemp())
                && data.getHumidity().equals(getHumidity());
    }
}
