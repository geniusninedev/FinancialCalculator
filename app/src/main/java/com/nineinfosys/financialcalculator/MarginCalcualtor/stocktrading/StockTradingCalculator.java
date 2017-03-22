package com.nineinfosys.financialcalculator.MarginCalcualtor.stocktrading;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.nineinfosys.financialcalculator.R;

import java.text.DecimalFormat;

public class StockTradingCalculator extends AppCompatActivity implements View.OnClickListener {
    EditText editTextStockPrice,editTextNoofShares,editTextMarginRate;
    TextView textViewResultAmontRequired;
    LinearLayout layoutDisplayResult,layoutWarning;
    Button buttonCalculate,buttonReset;
    stockTradingCal stocktrading;
    double stockPrice,noofShares,marginRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stocktrading_main);

        //keyboard hidden first time
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //customize toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Stock Trading Calcualtor");

        layoutDisplayResult=(LinearLayout)findViewById(R.id.layoutDisplayResult);
        layoutWarning=(LinearLayout)findViewById(R.id.layoutWarning);
        editTextStockPrice=(EditText)findViewById(R.id.editTextStockPrice);
        editTextNoofShares=(EditText)findViewById(R.id.editTextNumberofShares);
        editTextMarginRate=(EditText)findViewById(R.id.editTextMarginRate);
        textViewResultAmontRequired=(TextView) findViewById(R.id.textViewResultAmountRequired);
        buttonCalculate=(Button)findViewById(R.id.buttonCalculate);
        buttonReset=(Button)findViewById(R.id.buttonStockReset);

        buttonCalculate.setOnClickListener(this);
        buttonReset.setOnClickListener(this);

    }
    private void calculateStockMargin() {
        if(editTextStockPrice.getText().toString().trim().equals("")&& editTextNoofShares.getText().toString().trim().equals("")&& editTextMarginRate.getText().toString().trim().equals(""))
        {
            layoutWarning.setVisibility(View.VISIBLE);
            layoutDisplayResult.setVisibility(View.GONE);

        }
        else if (editTextStockPrice.getText().toString().trim().equals("")|| editTextStockPrice.getText().toString().isEmpty()) {
            editTextStockPrice.setError("Enter Stock Price Value.");
            layoutWarning.setVisibility(View.GONE);
            layoutDisplayResult.setVisibility(View.GONE);
        }
        else if (editTextNoofShares.getText().toString().trim().equals("")|| editTextNoofShares.getText().toString().isEmpty()) {
            editTextNoofShares.setError("Enter Positive Number of Shares Value.");
            layoutWarning.setVisibility(View.GONE);
            layoutDisplayResult.setVisibility(View.GONE);
        }
        else if (editTextMarginRate.getText().toString().trim().equals("")|| editTextMarginRate.getText().toString().isEmpty()) {
            editTextMarginRate.setError("Enter Margin Rate Value");
            layoutWarning.setVisibility(View.GONE);
            layoutDisplayResult.setVisibility(View.GONE);
        }else {
            //for hiding keyboard
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

            //getting value from edittext
            stockPrice = Double.parseDouble(editTextStockPrice.getText().toString().trim());
            noofShares = Double.parseDouble(editTextNoofShares.getText().toString().trim());
            marginRate = Double.parseDouble(editTextMarginRate.getText().toString().trim());

            stocktrading = new stockTradingCal(stockPrice, noofShares, marginRate);
            double amount = stocktrading.calcualteAmountRequired();

            layoutDisplayResult.setVisibility(View.VISIBLE);
            layoutWarning.setVisibility(View.GONE);

            textViewResultAmontRequired.setText(new DecimalFormat("##.##").format(amount));
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonCalculate:
                calculateStockMargin();
                break;
            case R.id.buttonStockReset:
                layoutDisplayResult.setVisibility(View.GONE);
                layoutWarning.setVisibility(View.GONE);
                editTextStockPrice.setText(null);
                editTextNoofShares.setText(null);
                editTextMarginRate.setText(null);

                break;

        }
    }
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            // Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

}
