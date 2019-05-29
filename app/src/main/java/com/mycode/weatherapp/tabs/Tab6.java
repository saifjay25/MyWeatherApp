package com.mycode.weatherapp.tabs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mycode.weatherapp.Database.Repository;
import com.mycode.weatherapp.R;
import com.mycode.weatherapp.entity.DailyWeatherData;
import com.mycode.weatherapp.entity.Weather;
import com.mycode.weatherapp.ui.DatesAndTimes;
import com.mycode.weatherapp.ui.MainViewModel;

import java.util.List;


public class Tab6 extends Fragment {
    private String key;
    private Double latitude;
    private Double longitude;
    private String time;
    private MainViewModel viewModel;
    private TextView humidity,dewpoint,windspeed, preciptype, precipProb, precipaccu , tempLow, summary, temp;
    private Button moreInfo;
    private DailyWeatherData dailyWeather;
    private DatesAndTimes getTime;
    private static Weather gweather;

    private OnFragmentInteractionListener mListener;

    public Tab6() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTime = new DatesAndTimes();
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        if(getArguments() != null) {
            key = getArguments().getString("key");
            System.out.println(key);
            latitude = getArguments().getDouble("latitude");
            longitude = getArguments().getDouble("longitude");
            time = getArguments().getString("time");
            observingWeather();
        }else if(Repository.noWifi){
            viewModel.getAllDailyData().observe(this, new Observer<List<DailyWeatherData>>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onChanged(List<DailyWeatherData> dailyWeatherData) {
                    if(Repository.noWifi && !dailyWeatherData.isEmpty()) {
                        for(int i=0; i<7; i++) {
                            if(dailyWeatherData.get(i).getTab().equals("Tab6")){
                                dailyWeather = dailyWeatherData.get(i);
                                break;
                            }
                        }
                        temp.setText("Highest Temperature: "+dailyWeather.getTemperatureHigh());
                        dewpoint.setText("DewPoint: "+dailyWeather.getDewPoint());
                        summary.setText("Summary: "+dailyWeather.getSummary());
                        humidity.setText("Humidity: "+dailyWeather.getHumidity());
                        windspeed.setText("Wind Speed: "+dailyWeather.getWindSpeed());
                        preciptype.setText("Precipitation Type: "+dailyWeather.getPrecipType());
                        precipProb.setText("Precipitation Probability: "+dailyWeather.getPrecipProbability());
                        tempLow.setText("Lowest Temperature: "+dailyWeather.getTemperatureLow());
                        precipaccu.setText("Wind Bearing: "+dailyWeather.getWindBearing());
                    }
                }
            });
        }else{
            return;
        }
    }

    private void observingWeather() {
        viewModel.futureAPICall(key, latitude, longitude, time).observe(this, new Observer<Weather>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Weather weather) {
                weather.getDailyWeather().getData().get(0).setTab("Tab6");
                viewModel.addDailyWeather(weather.getDailyWeather().getData().get(0));
                gweather = weather;
                temp.setText("Highest Temperature: "+weather.getDailyWeather().getData().get(0).getTemperatureHigh());
                summary.setText("Summary: "+weather.getDailyWeather().getData().get(0).getSummary());
                humidity.setText("Humidity: "+weather.getDailyWeather().getData().get(0).getHumidity());
                dewpoint.setText("DewPoint: "+weather.getDailyWeather().getData().get(0).getDewPoint());
                windspeed.setText("Wind Speed: "+weather.getDailyWeather().getData().get(0).getWindSpeed());
                preciptype.setText("Precipitation Type: "+weather.getDailyWeather().getData().get(0).getPrecipType());
                precipProb.setText("Precipitation Probability: "+weather.getDailyWeather().getData().get(0).getPrecipProbability());
                precipaccu.setText("Wind Bearing: "+weather.getDailyWeather().getData().get(0).getWindBearing());
                tempLow.setText("Lowest Temperature: "+weather.getDailyWeather().getData().get(0).getTemperatureLow());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab6, container, false);
        temp = view.findViewById(R.id.temp);
        summary = view.findViewById(R.id.summary);
        dewpoint = view.findViewById(R.id.dewpoint);
        humidity = view.findViewById(R.id.humid);
        windspeed = view.findViewById(R.id.windSpeed);
        preciptype = view.findViewById(R.id.precipT);
        precipProb = view.findViewById(R.id.precipP);
        precipaccu = view.findViewById(R.id.tempH);
        tempLow = view.findViewById(R.id.tempL);
        moreInfo = view.findViewById(R.id.moreInfo);
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Repository.noWifi) {
                    String precipMaxTime = getTime.getTime(gweather.getDailyWeather().getData().get(0).getPrecipIntensityMaxTime());
                    String sunriseTime = getTime.getTime(gweather.getDailyWeather().getData().get(0).getSunriseTime());
                    String tempHighTime = getTime.getTime(gweather.getDailyWeather().getData().get(0).getTemperatureHighTime());
                    String sunsetTime = getTime.getTime(gweather.getDailyWeather().getData().get(0).getSunsetTime());
                    String tempLowTime = getTime.getTime(gweather.getDailyWeather().getData().get(0).getTemperatureLowTime());
                    showDialogAnime(R.style.dialogSlide,
                            "Precipitation Intensity Maximum Time: " + precipMaxTime + "\n" + "\n" +
                                    "Temperature High Time: " + tempHighTime + "\n" + "\n" +
                                    "Temperature Low Time: " + tempLowTime + "\n" + "\n" +
                                    "Sunrise Time: " + sunriseTime + "\n" + "\n" +
                                    "Sunset Time: " + sunsetTime + "\n" + "\n" +
                                    "Cloud Cover: " + gweather.getDailyWeather().getData().get(0).getCloudCover() + "\n" + "\n" +
                                    "Pressure: " + gweather.getDailyWeather().getData().get(0).getPressure() + "\n" + "\n" +
                                    "MoonPhase: " + gweather.getDailyWeather().getData().get(0).getMoonPhase() + "\n" + "\n" +
                                    "Visibility: " + gweather.getDailyWeather().getData().get(0).getVisibility());
                }else{
                    String precipMaxTime = getTime.getTime(dailyWeather.getPrecipIntensityMaxTime());
                    String sunriseTime = getTime.getTime(dailyWeather.getSunriseTime());
                    String tempHighTime = getTime.getTime(dailyWeather.getTemperatureHighTime());
                    String sunsetTime = getTime.getTime(dailyWeather.getSunsetTime());
                    String tempLowTime = getTime.getTime(dailyWeather.getTemperatureLowTime());
                    showDialogAnime(R.style.dialogSlide,
                            "Precipitation Intensity Maximum Time: " + precipMaxTime + "\n" + "\n" +
                                    "Temperature High Time: " + tempHighTime + "\n" + "\n" +
                                    "Temperature Low Time: " + tempLowTime + "\n" + "\n" +
                                    "Sunrise Time: " + sunriseTime + "\n" + "\n" +
                                    "Sunset Time: " + sunsetTime + "\n" + "\n" +
                                    "Cloud Cover: " + dailyWeather.getCloudCover() + "\n" + "\n" +
                                    "Pressure: " + dailyWeather.getPressure() + "\n" + "\n" +
                                    "MoonPhase: " + dailyWeather.getMoonPhase() + "\n" + "\n" +
                                    "Visibility: " + dailyWeather.getVisibility());
                }
            }
        });
        return view;
    }

    private void showDialogAnime(int type, String info){
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("More Information");
        alertDialog.setMessage(info);
        alertDialog.getWindow().getAttributes().windowAnimations = type;
        alertDialog.show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
