package com.example.tancorik.converterapp.presentation.view;

import java.util.List;

public interface IMainScreenView {
    void showSpinners(List<String> valuteNames, String date);
    void showChatCodes(String startCode, String finalCode);
    void showFinalValue(String finalValue);
    void showError(String errorText);
}
