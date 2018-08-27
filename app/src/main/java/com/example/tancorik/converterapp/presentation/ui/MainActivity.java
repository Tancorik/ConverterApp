package com.example.tancorik.converterapp.presentation.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tancorik.converterapp.R;
import com.example.tancorik.converterapp.application.ConverterApp;
import com.example.tancorik.converterapp.presentation.presenter.MainScreenPresenter;
import com.example.tancorik.converterapp.presentation.view.IMainScreenView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements IMainScreenView{

    private static final int START_SECTION_NUMBER = 0;
    private static final int FINAL_SECION_NUBMER = 1;

    @Inject
    public MainScreenPresenter mPresenter;

    private Spinner mStartSpinner;
    private Spinner mFinalSpinner;
    private EditText mStartValueEditText;
    private ArrayAdapter<String> mStartAdapter;
    private ArrayAdapter<String> mFinalAdapter;
    private List<String> mSpinnerContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConverterApp.getComponent().inject(this);

        init();
        mPresenter.setView(this);
        mPresenter.getInfo();
    }

    @Override
    public void showSpinners(List<String> valuteNames, String date) {
        mSpinnerContent.clear();
        mSpinnerContent.addAll(valuteNames);
        mStartAdapter.notifyDataSetChanged();
        mFinalAdapter.notifyDataSetChanged();
        TextView dateText = findViewById(R.id.date_text_view);
        dateText.append(date);
    }

    @Override
    public void showChatCodes(String startCode, String finalCode) {
        TextView firstCodeText = findViewById(R.id.char_code_start_valute);
        firstCodeText.setText(startCode);
        TextView secondCodeText = findViewById(R.id.char_code_finish_valute);
        secondCodeText.setText(finalCode);
    }

    @Override
    public void showFinalValue(String finalValue) {
        EditText editText = findViewById(R.id.finish_valute_edit_text);
        editText.setText(finalValue);
    }

    @Override
    public void showError(String errorText) {

    }

    private void init() {
        mSpinnerContent = new ArrayList<>();
        mStartValueEditText = findViewById(R.id.start_valute_edit_text);
        mStartValueEditText.addTextChangedListener(new ValueTextWatcher());

        mStartSpinner = findViewById(R.id.start_valute_spinner);
        mStartAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, R.id.spinner_text_view, mSpinnerContent);
        mStartSpinner.setAdapter(mStartAdapter);
        mStartSpinner.setOnItemSelectedListener(new OnSpinnerListener(START_SECTION_NUMBER));

        mFinalSpinner = findViewById(R.id.finish_valute_spinner);
        mFinalAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, R.id.spinner_text_view, mSpinnerContent);
        mFinalSpinner.setAdapter(mFinalAdapter);
        mFinalSpinner.setOnItemSelectedListener(new OnSpinnerListener(FINAL_SECION_NUBMER));
    }

    private class OnSpinnerListener implements AdapterView.OnItemSelectedListener {

        private int mNumberSection;

        OnSpinnerListener(int numberSection) {
            mNumberSection = numberSection;
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
           if (mNumberSection == START_SECTION_NUMBER) {
               mPresenter.setStartSpinnerPosition(mStartSpinner.getSelectedItemPosition());
           }
           else if (mNumberSection == FINAL_SECION_NUBMER) {
               mPresenter.setFinalSpinnerPosition(mFinalSpinner.getSelectedItemPosition());
           }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private class ValueTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            mPresenter.updateFirstValue(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
