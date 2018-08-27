package com.example.tancorik.converterapp.dagger.module;

import com.example.tancorik.converterapp.data.RemoteValCursService;
import com.example.tancorik.converterapp.domain.IValCursService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ValuteServiceModule {

    @Provides
    @Singleton
    @Named("RemoteService")
    public IValCursService provideRemoteValCursService() {
        return new RemoteValCursService();
    }
}
