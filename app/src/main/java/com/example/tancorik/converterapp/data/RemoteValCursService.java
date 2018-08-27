package com.example.tancorik.converterapp.data;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.example.tancorik.converterapp.domain.IValCursService;
import com.example.tancorik.converterapp.domain.IValCursServiceListener;
import com.example.tancorik.converterapp.presentation.model.ValCurs;

import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RemoteValCursService implements IValCursService {

    private static final String BASE_URL = "http://www.cbr.ru/scripts/XML_daily.asp";
    private Handler mWorkHandler;
    private Handler mMainThreadHandler;

    public RemoteValCursService() {
        HandlerThread handlerThread = new HandlerThread("RemoteValCursServiceThread");
        handlerThread.start();
        mWorkHandler = new Handler(handlerThread.getLooper());
        mMainThreadHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void getValCurse(IValCursServiceListener listener) {
        mWorkHandler.post(() -> handleRequest(listener));
    }

    private void handleRequest(IValCursServiceListener listener) {
        ValCurs valCurs;
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException("Код ответа сервера + " + connection.getResponseCode());
            }
            Reader reader = new InputStreamReader(connection.getInputStream(), "windows-1251");
            Persister persister = new Persister();
            valCurs = persister.read(ValCurs.class, reader);
            valCurs = addRusValute(valCurs);
            ValCurs finalValCurs = valCurs;
            mMainThreadHandler.post(() -> listener.onSuccess(finalValCurs));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            mMainThreadHandler.post(() -> listener.onError(e));
        } catch (IOException e) {
            e.printStackTrace();
            mMainThreadHandler.post(() -> listener.onError(e));
        } catch (Exception e) {
            e.printStackTrace();
            mMainThreadHandler.post(() -> listener.onError(e));
        }
    }

    private ValCurs addRusValute(ValCurs valCurs) {
        ValCurs.Valute valute = new ValCurs.Valute();
        valute.setId("0");
        valute.setName("Российский рубль");
        valute.setCharCode("RUB");
        valute.setNominal(1);
        valute.setValue("1");
        valCurs.getValuteList().set(0, valute);
        return valCurs;
    }
}
