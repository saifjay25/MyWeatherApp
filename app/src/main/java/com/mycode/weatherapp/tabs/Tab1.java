package com.mycode.weatherapp.tabs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mycode.weatherapp.R;
import com.mycode.weatherapp.persistence.Repository;
import com.mycode.weatherapp.entity.CurrentWeather;
import com.mycode.weatherapp.entity.DailyWeatherData;
import com.mycode.weatherapp.entity.Weather;
import com.mycode.weatherapp.ui.DatesAndTimes;
import com.mycode.weatherapp.ui.MainViewModel;
import com.mycode.weatherapp.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class Tab1 extends DaggerFragment {

    private OnFragmentInteractionListener mListener;
    private String key;
    private Double latitude;
    private Double longitude;
    private String time;
    private MainViewModel viewModel;
    private Button moreInfo;
    private DatesAndTimes getTime;
    private TextView humidity,dewpoint,windspeed, preciptype, precipProb, tempHigh, tempLow, summary, temp;
    private ImageView imageView;
    private static Weather gweather;
    private DailyWeatherData dailyWeather;
    @Inject
    ViewModelProviderFactory providerFactory;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public Tab1(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this,providerFactory).get(MainViewModel.class);
        getTime = new DatesAndTimes();
        if(getArguments() != null && !Repository.noWifi) {
            key = getArguments().getString("key");
            latitude = getArguments().getDouble("latitude");
            longitude = getArguments().getDouble("longitude");
            time = getArguments().getString("time");
            observingWeather();
        }else if(Repository.noWifi){
           viewModel.getAllCurrentData().observe(this, new Observer<CurrentWeather>() {
               @SuppressLint("SetTextI18n")
               @Override
               public void onChanged(CurrentWeather currentWeather) {
                   if(Repository.noWifi && currentWeather != null) {
                       temp.setText("Temperature: " + currentWeather.getTemperature()+"°");
                       summary.setText("Summary: " + currentWeather.getSummary());
                       setPic(summary.getText().toString());
                       humidity.setText("Humidity: " + convertPercent(currentWeather.getHumidity()));
                       windspeed.setText("Wind Speed: "+currentWeather.getWindSpeed()+" mph");
                       preciptype.setText("Precipitation Type: "+currentWeather.getPrecipType());
                       precipProb.setText("Precipitation Probability: "+currentWeather.getPrecipProbability());
                   }else{
                       Toast.makeText(getContext(),"connect to wifi and restart app", Toast.LENGTH_SHORT).show();
                   }
               }
           });
           viewModel.getAllDailyData().observe(this, new Observer<List<DailyWeatherData>>() {
               @SuppressLint("SetTextI18n")
               @Override
               public void onChanged(List<DailyWeatherData> dailyWeatherData) {
                   if(Repository.noWifi && !dailyWeatherData.isEmpty()) {
                       for(int i=0; i<7; i++) {
                           if(dailyWeatherData.get(i).getTab().equals("Tab1")){
                               dailyWeather = dailyWeatherData.get(i);
                               break;
                           }
                       }
                       dewpoint.setText("DewPoint: "+dailyWeather.getDewPoint()+"°");
                       tempLow.setText("Lowest Temperature: "+dailyWeather.getTemperatureLow()+"°");
                       tempHigh.setText("Highest Temperature: "+dailyWeather.getTemperatureHigh()+"°");
                   }
               }
           });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        temp = view.findViewById(R.id.temp);
        summary = view.findViewById(R.id.summary);
        humidity = view.findViewById(R.id.humid);
        dewpoint = view.findViewById(R.id.dewpoint);
        windspeed = view.findViewById(R.id.windSpeed);
        preciptype = view.findViewById(R.id.precipT);
        precipProb = view.findViewById(R.id.precipP);
        tempHigh = view.findViewById(R.id.tempH);
        tempLow = view.findViewById(R.id.tempL);
        imageView = view.findViewById(R.id.pic1);
        moreInfo = view.findViewById(R.id.moreInfo);
        moreInfo.setOnClickListener(v -> {
            if(!Repository.noWifi) {
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
                                "Cloud Cover: " + convertPercent(gweather.getDailyWeather().getData().get(0).getCloudCover()) + "\n" + "\n" +
                                "Pressure: " + gweather.getDailyWeather().getData().get(0).getPressure() + "\n" + "\n" +
                                "MoonPhase: " + gweather.getDailyWeather().getData().get(0).getMoonPhase() + "\n" + "\n" +
                                "Visibility: " + gweather.getDailyWeather().getData().get(0).getVisibility()+" miles");
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
                                "Cloud Cover: " + convertPercent(dailyWeather.getCloudCover())+ "\n" + "\n" +
                                "Pressure: " + dailyWeather.getPressure() + "\n" + "\n" +
                                "MoonPhase: " + dailyWeather.getMoonPhase() + "\n" + "\n" +
                                "Visibility: " + dailyWeather.getVisibility()+" miles");
            }
        });
        return view;
    }
    private void observingWeather() {
        viewModel.currentAPICall(key,latitude,longitude).observe(this, new Observer<Weather>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Weather weather) {
                mDisposable.add(viewModel.addCurrentWeather(weather.getCurrentWeather())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe());
                temp.setText("Temperature: "+weather.getCurrentWeather().getTemperature()+"°");
                summary.setText("Summary: "+weather.getCurrentWeather().getSummary());
                setPic(summary.getText().toString());
                humidity.setText("Humidity: "+convertPercent(weather.getCurrentWeather().getHumidity()));
                dewpoint.setText("DewPoint: "+weather.getDailyWeather().getData().get(0).getDewPoint()+"°");
                windspeed.setText("Wind Speed: "+weather.getCurrentWeather().getWindSpeed()+" mph");
                preciptype.setText("Precipitation Type: "+weather.getCurrentWeather().getPrecipType());
                precipProb.setText("Precipitation Probability: "+convertPercent(weather.getCurrentWeather().getPrecipProbability()));
                tempHigh.setText("Highest Temperature: "+weather.getDailyWeather().getData().get(0).getTemperatureHigh()+"°");
                tempLow.setText("Lowest Temperature: "+weather.getDailyWeather().getData().get(0).getTemperatureLow()+"°");
            }
        });
        viewModel.futureAPICall(key, latitude, longitude, time).observe(this, weather -> {
            weather.getDailyWeather().getData().get(0).setTab("Tab1");
            mDisposable.add(viewModel.addDailyWeather(weather.getDailyWeather().getData().get(0))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
            gweather = weather;
        });
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

    private void showDialogAnime(int type, String info){
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("More Information");
        alertDialog.setMessage(info);
        alertDialog.getWindow().getAttributes().windowAnimations = type;
        alertDialog.show();
    }

    @Override
    public void onStop() {
        super.onStop();
        mDisposable.clear();
    }

    private String convertPercent(String num){
        Double number = Double.parseDouble(num);
        int percentage = (int) ((1 - number) * 100);
        if (number == 0) {
            percentage = 0;
        }
        return percentage+"%";
    }

    private void setPic(String summary){
        if(summary.toLowerCase().contains("rain")){
            imageView.setImageResource(R.mipmap.rain);
            return;
        }
        if(summary.toLowerCase().contains("partly cloudy")){
            imageView.setImageResource(R.mipmap.partlycloudy);
            return;
        }
        if(summary.toLowerCase().contains("cloudy")){
            imageView.setImageResource(R.mipmap.cloudy);
            return;
        }
        if(summary.toLowerCase().contains("snow")){
            imageView.setImageResource(R.mipmap.snow);
            return;
        }
        if(summary.toLowerCase().contains("clear" ) || summary.toLowerCase().contains("sunny")){
            imageView.setImageResource(R.mipmap.sunny);
            return;
        }
        if(summary.toLowerCase().contains("wind" )|| summary.toLowerCase().contains("breezy")|| summary.toLowerCase().contains("windy")){
            imageView.setImageResource(R.mipmap.wind);
            return;
        }
        imageView.setImageResource(R.mipmap.partlycloudy);
    }
}
