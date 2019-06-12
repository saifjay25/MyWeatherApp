package com.mycode.weatherapp.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.mycode.weatherapp.R;
import com.mycode.weatherapp.persistence.Repository;
import com.mycode.weatherapp.adapter.PagerAdapter;
import com.mycode.weatherapp.internetConnection.CheckConnection;
import com.mycode.weatherapp.tabs.Tab1;
import com.mycode.weatherapp.tabs.Tab2;
import com.mycode.weatherapp.tabs.Tab3;
import com.mycode.weatherapp.tabs.Tab4;
import com.mycode.weatherapp.tabs.Tab5;
import com.mycode.weatherapp.tabs.Tab6;
import com.mycode.weatherapp.tabs.Tab7;
import com.mycode.weatherapp.viewmodels.ViewModelProviderFactory;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends DaggerAppCompatActivity implements Tab1.OnFragmentInteractionListener, Tab2.OnFragmentInteractionListener,
Tab3.OnFragmentInteractionListener, Tab4.OnFragmentInteractionListener, Tab5.OnFragmentInteractionListener, Tab6.OnFragmentInteractionListener ,
Tab7.OnFragmentInteractionListener{

    private LocationManager locationManager;
    private DatesAndTimes sevenDates = new DatesAndTimes();
    private PagerAdapter pagerAdapter;
    private final String key = "97357a1cc59a1a427e9ade54f72a10b9";
    private ProgressDialog progressDialog;
    private String APITime;
    private String APITime2;
    private String APITime3;
    private String APITime4;
    private String APITime5;
    private String APITime6;
    private String APITime7;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel viewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel.class);
        viewModel.getcount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                System.out.println("hellloooo  "+ integer);
            }
        });
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Fetching Location");
        progressDialog.setCancelable(false);
        progressDialog.show();
        CheckConnection checkConnection = new CheckConnection(this);
        Calendar now = Calendar.getInstance();
        Date date = now.getTime();
        APITime = sevenDates.getWholeDate(date);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(sevenDates.getMonthDate(now.getTime())));
        now.add(Calendar.HOUR, 24);
        Date date2 = now.getTime();
        APITime2 = sevenDates.getWholeDate(date2);
        System.out.println("time "+APITime2);
        tabLayout.addTab(tabLayout.newTab().setText(sevenDates.getMonthDate(now.getTime())));
        now.add(Calendar.HOUR, 24);
        Date date3 = now.getTime();
        APITime3 = sevenDates.getWholeDate(date3);
        tabLayout.addTab(tabLayout.newTab().setText(sevenDates.getMonthDate(now.getTime())));
        now.add(Calendar.HOUR, 24);
        Date date4 = now.getTime();
        APITime4 = sevenDates.getWholeDate(date4);
        tabLayout.addTab(tabLayout.newTab().setText(sevenDates.getMonthDate(now.getTime())));
        now.add(Calendar.HOUR, 24);
        Date date5 = now.getTime();
        APITime5 = sevenDates.getWholeDate(date5);
        tabLayout.addTab(tabLayout.newTab().setText(sevenDates.getMonthDate(now.getTime())));
        now.add(Calendar.HOUR, 24);
        Date date6 = now.getTime();
        APITime6 = sevenDates.getWholeDate(date6);
        tabLayout.addTab(tabLayout.newTab().setText(sevenDates.getMonthDate(now.getTime())));
        now.add(Calendar.HOUR, 24);
        Date date7 = now.getTime();
        APITime7 = sevenDates.getWholeDate(date7);
        tabLayout.addTab(tabLayout.newTab().setText(sevenDates.getMonthDate(now.getTime())));

        final ViewPager viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new PagerAdapter(this.getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(7);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        if (checkConnection.isOnline()) {
            mDisposable.add(viewModel.deleteCurrentWeather()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
            mDisposable.add(viewModel.deleteDailyWeather()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
            Repository.noWifi = false;
            googleApi();
        }else{
            progressDialog.dismiss();
        }
    }

    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            progressDialog.dismiss();
            if(Repository.counter == 0 ) {
                Repository.counter++;
                Bundle bundle = new Bundle();
                bundle.putDouble("latitude", location.getLatitude());
                bundle.putDouble("longitude", location.getLongitude());
                bundle.putString("key", key);
                bundle.putString("time", APITime);
                progressDialog.dismiss();
                Tab1 data = new Tab1();
                data.setArguments(bundle);
                FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
                tr.replace(R.id.frag1, data);
                tr.commit();

                Bundle bundle2 = new Bundle();
                bundle2.putDouble("latitude", location.getLatitude());
                bundle2.putDouble("longitude", location.getLongitude());
                bundle2.putString("key", key);
                bundle2.putString("time", APITime2);
                Tab2 data2 = new Tab2();
                data2.setArguments(bundle2);
                FragmentTransaction tr2 = getSupportFragmentManager().beginTransaction();
                tr2.replace(R.id.frag2, data2);
                tr2.commit();

                Bundle bundle3 = new Bundle();
                bundle3.putDouble("latitude", location.getLatitude());
                bundle3.putDouble("longitude", location.getLongitude());
                bundle3.putString("key", key);
                bundle3.putString("time", APITime3);
                Tab3 data3 = new Tab3();
                data3.setArguments(bundle3);
                FragmentTransaction tr3 = getSupportFragmentManager().beginTransaction();
                tr3.replace(R.id.frag3, data3);
                tr3.commit();

                Bundle bundle4 = new Bundle();
                bundle4.putDouble("latitude", location.getLatitude());
                bundle4.putDouble("longitude", location.getLongitude());
                bundle4.putString("key", key);
                bundle4.putString("time", APITime4);
                Tab4 data4 = new Tab4();
                data4.setArguments(bundle4);
                FragmentTransaction tr4 = getSupportFragmentManager().beginTransaction();
                tr4.replace(R.id.frag4, data4);
                tr4.commit();

                Bundle bundle5 = new Bundle();
                bundle5.putDouble("latitude", location.getLatitude());
                bundle5.putDouble("longitude", location.getLongitude());
                bundle5.putString("key", key);
                bundle5.putString("time", APITime5);
                Tab5 data5 = new Tab5();
                data5.setArguments(bundle5);
                FragmentTransaction tr5 = getSupportFragmentManager().beginTransaction();
                tr5.replace(R.id.frag5, data5);
                tr5.commit();

                Bundle bundle6 = new Bundle();
                bundle6.putDouble("latitude", location.getLatitude());
                bundle6.putDouble("longitude", location.getLongitude());
                bundle6.putString("key", key);
                bundle6.putString("time", APITime6);
                Tab6 data6 = new Tab6();
                data6.setArguments(bundle6);
                FragmentTransaction tr6 = getSupportFragmentManager().beginTransaction();
                tr6.replace(R.id.frag6, data6);
                tr6.commit();

                Bundle bundle7 = new Bundle();
                bundle7.putDouble("latitude", location.getLatitude());
                bundle7.putDouble("longitude", location.getLongitude());
                bundle7.putString("key", key);
                bundle7.putString("time", APITime7);
                Tab7 data7 = new Tab7();
                data7.setArguments(bundle7);
                FragmentTransaction tr7 = getSupportFragmentManager().beginTransaction();
                tr7.replace(R.id.frag7, data7);
                tr7.commit();

            }

        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };

    private void googleApi() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            assert locationManager != null;
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, locationListener);
        } else {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 12);
            }
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 13);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        assert locationManager != null;
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, locationListener);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onFragmentInteraction(Uri uri) { }
    @Override
    public void onStop() {
        super.onStop();
        Repository.counter = 0;
        mDisposable.clear();
    }
}
