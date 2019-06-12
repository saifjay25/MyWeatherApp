package com.mycode.weatherapp.di;

import android.app.Application;

import com.mycode.weatherapp.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

//specify base app because this is being injected into the base application
@Singleton
@Component(modules = {AndroidInjectionModule.class,
        ViewModelFactoryModule.class,
        ActivityBuildersModule.class,
        AppModule.class})
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);

        //return that app componenet with the build method
        AppComponent build();
    }
}
