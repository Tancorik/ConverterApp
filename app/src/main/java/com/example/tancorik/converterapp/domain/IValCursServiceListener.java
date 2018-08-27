package com.example.tancorik.converterapp.domain;

import com.example.tancorik.converterapp.presentation.model.ValCurs;

public interface IValCursServiceListener {

    void onSuccess(ValCurs valCurs);
    void onError(Throwable e);
}
