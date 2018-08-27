package com.example.tancorik.converterapp.dagger.module;

import com.example.tancorik.converterapp.presentation.presenter.MainScreenPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    public MainScreenPresenter provideMainScreenPresenter() {
        return new MainScreenPresenter();
    }
}
