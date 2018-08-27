package com.example.tancorik.converterapp.application;

import android.app.Application;

import com.example.tancorik.converterapp.dagger.component.AppComponent;
import com.example.tancorik.converterapp.dagger.component.DaggerAppComponent;

public class ConverterApp extends Application {

    private static AppComponent sComponent;

    public static AppComponent getComponent() {
        return sComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sComponent = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .build();
    }
}
