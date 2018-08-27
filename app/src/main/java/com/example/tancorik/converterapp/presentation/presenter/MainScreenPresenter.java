package com.example.tancorik.converterapp.presentation.presenter;

import com.example.tancorik.converterapp.application.ConverterApp;
import com.example.tancorik.converterapp.domain.IValCursService;
import com.example.tancorik.converterapp.domain.IValCursServiceListener;
import com.example.tancorik.converterapp.presentation.model.ValCurs;
import com.example.tancorik.converterapp.presentation.view.IMainScreenView;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

public class MainScreenPresenter {


    @Inject
    @Named("RemoteService")
    public IValCursService mRemoteService;

    private ValCurs mValCurs;
    private IMainScreenView mView;
    private int mStartSpinnerPosition;
    private int mFinalSpinnerPosition;
    private double mFirstValue;


    public MainScreenPresenter () {
        ConverterApp.getComponent().inject(this);
    }

    public void setView(IMainScreenView view) {
        mView = view;
    }

    public void setStartSpinnerPosition(int startSpinnerPosition) {
        mStartSpinnerPosition = startSpinnerPosition;
        changeCharCode();
        updateFirstValue(String.valueOf(mFirstValue));
    }

    public void setFinalSpinnerPosition(int finalSpinnerPosition) {
        mFinalSpinnerPosition = finalSpinnerPosition;
        changeCharCode();
        updateFirstValue(String.valueOf(mFirstValue));
    }

    public void updateFirstValue(String value) {
        if (!value.isEmpty()) {
            mFirstValue = Double.parseDouble(value);
            mView.showFinalValue(String.format(Locale.getDefault(),"%.2f", calculateFinalValue(mFirstValue)));
        }
        else {
            mFirstValue = 0;
            mView.showFinalValue("");
        }
    }

    public void getInfo() {
        if (mValCurs == null) {
            loadValCurs(mRemoteService);
        }
        else {
            mView.showSpinners(getValuteNameList(), mValCurs.getDate());
            changeCharCode();
        }
    }

    private double calculateFinalValue(double value) {
        float firstCourse = mValCurs.getValuteList().get(mStartSpinnerPosition).getValue() /
                mValCurs.getValuteList().get(mStartSpinnerPosition).getNominal();
        float secondCourse = mValCurs.getValuteList().get(mFinalSpinnerPosition).getValue() /
                mValCurs.getValuteList().get(mFinalSpinnerPosition).getNominal();
        return value * firstCourse / secondCourse;
    }

    private void loadValCurs(IValCursService service) {
        service.getValCurse(new IValCursServiceListener() {
            @Override
            public void onSuccess(ValCurs valCurs) {
                mValCurs = valCurs;
                mView.showSpinners(getValuteNameList(), mValCurs.getDate());
                changeCharCode();
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    private void changeCharCode() {
        String startCharCode = mValCurs.getValuteList().get(mStartSpinnerPosition).getCharCode();
        String finalCharCode = mValCurs.getValuteList().get(mFinalSpinnerPosition).getCharCode();
        mView.showChatCodes(startCharCode, finalCharCode);
    }

    private List<String> getValuteNameList() {
        List<String> list = new ArrayList<>();
        for (ValCurs.Valute valute : mValCurs.getValuteList()) {
            list.add(valute.getName());
        }
        return list;
    }
}
