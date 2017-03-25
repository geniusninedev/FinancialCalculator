package com.nineinfosys.financialcalculator.MarginCalcualtor.CurrencyExchange;



/**
 * Created by Divya on 4-02-2017.
 */
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


import com.nineinfosys.financialcalculator.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CurrencyExchangeMain extends AppCompatActivity implements View.OnClickListener{
    EditText edittextExchangeRate, edittextUnits;
    TextView textViewResultAmontRequired;
    LinearLayout layoutDisplayResult,layoutWarning;
    Button buttonCalculate,buttonReset;
    Spinner spinnerMarginRatio;
    currencyExchangeCal currencyExchangeCal;
    double exchangRate,Units;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currencyexchange_main);

        //keyboard hidden first time
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //customize toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Currency Exchange Calcualtor");

        layoutDisplayResult=(LinearLayout)findViewById(R.id.layoutDisplayResult);
        layoutWarning=(LinearLayout)findViewById(R.id.layoutWarning);

        edittextExchangeRate=(EditText)findViewById(R.id.editTextExchangeRate);
        edittextUnits=(EditText)findViewById(R.id.editTextUnit);
        spinnerMarginRatio=(Spinner)findViewById(R.id.spinnerMarginRatio);
        textViewResultAmontRequired=(TextView) findViewById(R.id.textViewResultAmountRequired);
        buttonCalculate=(Button)findViewById(R.id.buttonCalculate);
        buttonReset=(Button)findViewById(R.id.buttoncurrencyReset);

        List<String> marginratio = new ArrayList<String>();
        marginratio.add("1:1");
        marginratio.add("5:1");
        marginratio.add("10:1");
        marginratio.add("20:1");
        marginratio.add("25:1");
        marginratio.add("30:1");
        marginratio.add("40:1");
        marginratio.add("50:1");
        // Creating adapter for spinner
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, marginratio);

        // Drop down layout style - list view with radio button
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerMarginRatio.setAdapter(Adapter);

        buttonCalculate.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
    }
    private void calcualteCurrencyMargin() {
        if (edittextExchangeRate.getText().toString().trim().equals("") && edittextUnits.getText().toString().trim().equals("")) {
            layoutWarning.setVisibility(View.VISIBLE);
            layoutDisplayResult.setVisibility(View.GONE);

        } else if (edittextExchangeRate.getText().toString().trim().equals("") || edittextExchangeRate.getText().toString().isEmpty()) {
            edittextExchangeRate.setError("Please provide a positive exchange rate value.\n");
            layoutWarning.setVisibility(View.GONE);
            layoutDisplayResult.setVisibility(View.GONE);
        } else if (edittextUnits.getText().toString().trim().equals("") || edittextUnits.getText().toString().isEmpty()) {
            edittextUnits.setError( "Please provide a positive units value.\n");
            layoutWarning.setVisibility(View.GONE);
            layoutDisplayResult.setVisibility(View.GONE);
        } else {
            //for hiding keyboard
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

            exchangRate = Double.parseDouble(edittextExchangeRate.getText().toString().trim());
            Units = Double.parseDouble(edittextUnits.getText().toString().trim());
            String alertspinnertax = spinnerMarginRatio.getSelectedItem().toString().trim();

            switch (alertspinnertax) {
                case "1:1":
                    currencyExchangeCal = new currencyExchangeCal(exchangRate, 1, Units);
                    break;
                case "5:1":
                    currencyExchangeCal = new currencyExchangeCal(exchangRate, 5, Units);
                    break;
                case "10:1":
                    currencyExchangeCal = new currencyExchangeCal(exchangRate, 10, Units);
                    break;
                case "20:1":
                    currencyExchangeCal = new currencyExchangeCal(exchangRate, 20, Units);
                    break;
                case "25:1":
                    currencyExchangeCal = new currencyExchangeCal(exchangRate, 25, Units);
                    break;
                case "30:1":
                    currencyExchangeCal = new currencyExchangeCal(exchangRate, 30, Units);
                    break;
                case "40:1":
                    currencyExchangeCal = new currencyExchangeCal(exchangRate, 40, Units);
                    break;
                case "50:1":
                    currencyExchangeCal = new currencyExchangeCal(exchangRate, 50, Units);
                    break;
            }

            double amount = currencyExchangeCal.calculateAmountRequired();

            layoutDisplayResult.setVisibility(View.VISIBLE);
            layoutWarning.setVisibility(View.GONE);

            textViewResultAmontRequired.setText(new DecimalFormat("##.##").format(amount));

        }
    }

    @Override
    public void onClick(View view) {
        //validation
       switch(view.getId())
       {
         case R.id.buttonCalculate :
           calcualteCurrencyMargin();
           break;

           case R.id.buttoncurrencyReset:
               layoutDisplayResult.setVisibility(View.GONE);
               layoutWarning.setVisibility(View.GONE);
               edittextExchangeRate.setText(null);
               edittextUnits.setText(null);
       }

    }
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

        }
    }


}
