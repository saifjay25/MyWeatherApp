package com.mycode.weatherapp.di;


import androidx.lifecycle.ViewModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface ViewModelKey {
    //needs to take a class that extends any viewModel and call method value
    //defines the key for marking the viewModels that can be used in the map multibinding
    Class<? extends ViewModel> value();
}
