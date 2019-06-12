package com.mycode.weatherapp.di;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mycode.weatherapp.ui.MainViewModel;
import com.mycode.weatherapp.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

//this is where all the viewmodel is, its where the dependency for viewmodelproviderfactory or any viewmodels is
@Module
public abstract class ViewModelFactoryModule {

    //dependency for viewmodelproviderfactory
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);
}
