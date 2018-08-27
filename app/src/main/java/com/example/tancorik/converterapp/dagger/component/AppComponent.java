package com.example.tancorik.converterapp.dagger.component;

import com.example.tancorik.converterapp.dagger.module.PresenterModule;
import com.example.tancorik.converterapp.dagger.module.ValuteServiceModule;
import com.example.tancorik.converterapp.presentation.presenter.MainScreenPresenter;
import com.example.tancorik.converterapp.presentation.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ValuteServiceModule.class, PresenterModule.class})
@Singleton
public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(MainScreenPresenter presenter);
}
