package com.mycode.weatherapp.entity;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "dailyWeatherTable")
public class DailyWeatherData {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    private String tab ="";

    @SerializedName("summary")
    private String summary = "";

    @SerializedName("humidity")
    private String humidity = "";

    @SerializedName("dewPoint")
    private String dewPoint = "";

    @SerializedName("windSpeed")
    private String windSpeed = "";

    @SerializedName("precipType")
    private String precipType = "";

    @SerializedName("precipProbability")
    private String precipProbability = "";

    @SerializedName("temperatureHigh")
    private String temperatureHigh = "";

    @SerializedName("temperatureLow")
    private String temperatureLow = "";

    //hidden
    @SerializedName("windBearing")
    private String windBearing = "";

    @SerializedName("pressure")
    private String pressure = "";

    @SerializedName("visibility")
    private String visibility = "";

    @SerializedName("moonPhase")
    private String moonPhase = "";

    @SerializedName("sunriseTime")
    private String sunriseTime = "";

    @SerializedName("sunsetTime")
    private String sunsetTime = "";

    @SerializedName("cloudCover")
    private String cloudCover = "";

    @SerializedName("temperatureHighTime")
    private String temperatureHighTime = "";

    @SerializedName("temperatureLowTime")
    private String temperatureLowTime = "";

    @SerializedName("precipIntensityMaxTime")
    private String precipIntensityMaxTime = "";

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(String dewPoint) {
        this.dewPoint = dewPoint;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getPrecipType() {
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

    public String getTemperatureHigh() {
        return temperatureHigh;
    }

    public void setTemperatureHigh(String temperatureHigh) {
        this.temperatureHigh = temperatureHigh;
    }

    public String getTemperatureLow() {
        return temperatureLow;
    }

    public void setTemperatureLow(String temperatureLow) {
        this.temperatureLow = temperatureLow;
    }

    public String getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(String windBearing) {
        this.windBearing = windBearing;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(String moonPhase) {
        this.moonPhase = moonPhase;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(String sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(String sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(String cloudCover) {
        this.cloudCover = cloudCover;
    }

    public String getTemperatureHighTime() {
        return temperatureHighTime;
    }

    public void setTemperatureHighTime(String temperatureHighTime) {
        this.temperatureHighTime = temperatureHighTime;
    }

    public String getTemperatureLowTime() {
        return temperatureLowTime;
    }

    public void setTemperatureLowTime(String temperatureLowTime) {
        this.temperatureLowTime = temperatureLowTime;
    }

    public String getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    public void setPrecipIntensityMaxTime(String precipIntensityMaxTime) {
        this.precipIntensityMaxTime = precipIntensityMaxTime;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTab(){
        return tab;
    }

    public void setTab(String tab){
        this.tab = tab;
    }
    
    public DailyWeatherData(int id, String tab, String summary, String humidity, String dewPoint, String windSpeed, String precipType, String precipProbability, String temperatureHigh, String temperatureLow, String windBearing, String pressure, String visibility, String moonPhase, String sunriseTime, String sunsetTime, String cloudCover, String temperatureHighTime, String temperatureLowTime, String precipIntensityMaxTime) {
        this.id = id;
        this.tab = tab;
        this.summary = summary;
        this.humidity = humidity;
        this.dewPoint = dewPoint;
        this.windSpeed = windSpeed;
        this.precipType = precipType;
        this.precipProbability = precipProbability;
        this.temperatureHigh = temperatureHigh;
        this.temperatureLow = temperatureLow;
        this.windBearing = windBearing;
        this.pressure = pressure;
        this.visibility = visibility;
        this.moonPhase = moonPhase;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.cloudCover = cloudCover;
        this.temperatureHighTime = temperatureHighTime;
        this.temperatureLowTime = temperatureLowTime;
        this.precipIntensityMaxTime = precipIntensityMaxTime;
    }
    
    public DailyWeatherData(DailyWeatherData dailyWeatherData1) {
        this.id = dailyWeatherData1.id;
        this.tab = dailyWeatherData1.tab;
        this.summary = dailyWeatherData1.summary;
        this.humidity = dailyWeatherData1.humidity;
        this.dewPoint = dailyWeatherData1.dewPoint;
        this.windSpeed = dailyWeatherData1.windSpeed;
        this.precipType = dailyWeatherData1.precipType;
        this.precipProbability = dailyWeatherData1.precipProbability;
        this.temperatureHigh = dailyWeatherData1.temperatureHigh;
        this.temperatureLow = dailyWeatherData1.temperatureLow;
        this.windBearing = dailyWeatherData1.windBearing;
        this.pressure = dailyWeatherData1.pressure;
        this.visibility = dailyWeatherData1.visibility;
        this.moonPhase = dailyWeatherData1.moonPhase;
        this.sunriseTime = dailyWeatherData1.sunriseTime;
        this.sunsetTime = dailyWeatherData1.sunsetTime;
        this.cloudCover = dailyWeatherData1.cloudCover;
        this.temperatureHighTime = dailyWeatherData1.temperatureHighTime;
        this.temperatureLowTime = dailyWeatherData1.temperatureLowTime;
        this.precipIntensityMaxTime = dailyWeatherData1.precipIntensityMaxTime;
    }
    
    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == null){
            return false;
        }

        if(getClass() != obj.getClass()){
            return false;
        }

        DailyWeatherData data =(DailyWeatherData) obj;
        return data.getId() == getId() && data.getTab().equals(getTab()) && data.getCloudCover().equals(getCloudCover())
                && data.getDewPoint().equals(getDewPoint()) && data.getHumidity().equals(getHumidity()) &&
                data.getMoonPhase().equals(getMoonPhase()) && data.getPrecipIntensityMaxTime().equals(getPrecipIntensityMaxTime())
                && data.getPrecipProbability().equals(getPrecipProbability()) && data.getPrecipType().equals(getPrecipType())
                && data.getPressure().equals(getPressure()) && data.getSummary().equals(getSummary()) &&
                data.getTemperatureLow().equals(getTemperatureLow()) && data.getTemperatureHigh().equals(getTemperatureHigh())
                && data.getTemperatureHighTime().equals(getTemperatureHighTime()) && data.getTemperatureLowTime().equals(getTemperatureLow())
                && data.getSunriseTime().equals(getSunriseTime()) &&  data.getVisibility().equals(getVisibility()) &&
                data.getWindBearing().equals(getWindBearing()) && data.getWindSpeed().equals(getWindSpeed())
                && data.getSunsetTime().equals(getSunsetTime());
    }
}
