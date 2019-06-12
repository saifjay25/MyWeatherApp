package com.mycode.weatherapp.di;

import com.mycode.weatherapp.tabs.Tab1;
import com.mycode.weatherapp.tabs.Tab2;
import com.mycode.weatherapp.tabs.Tab3;
import com.mycode.weatherapp.tabs.Tab4;
import com.mycode.weatherapp.tabs.Tab5;
import com.mycode.weatherapp.tabs.Tab6;
import com.mycode.weatherapp.tabs.Tab7;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract Tab1 contributesTab1();

    @ContributesAndroidInjector
    abstract Tab2 contributesTab2();

    @ContributesAndroidInjector
    abstract Tab3 contributesTab3();

    @ContributesAndroidInjector
    abstract Tab4 contributesTab4();

    @ContributesAndroidInjector
    abstract Tab5 contributesTab5();

    @ContributesAndroidInjector
    abstract Tab6 contributesTab6();

    @ContributesAndroidInjector
    abstract Tab7 contributesTab7();
}
