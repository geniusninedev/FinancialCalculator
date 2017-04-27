package com.nineinfosys.financialcalculator.MarginCalcualtor.CurrencyExchange;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.nineinfosys.financialcalculator.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CurrencyExchangeMain extends Fragment implements View.OnClickListener{
    EditText edittextExchangeRate, edittextUnits;
    TextView textViewResultAmontRequired;
    LinearLayout layoutDisplayResult,layoutWarning;
    Button buttonCalculate,buttonReset;
    Spinner spinnerMarginRatio;
    currencyExchangeCal currencyExchangeCal;
    double exchangRate,Units;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.currencyexchange_main, null);


        //keyboard hidden first time
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        MobileAds.initialize(getActivity(), getString(R.string.ads_app_id));
        AdView mAdView = (AdView) v.findViewById(R.id.adViewCurrencyCalculator);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

       /* //customize toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Currency Exchange Calcualtor");
*/
        layoutDisplayResult=(LinearLayout)v.findViewById(R.id.layoutDisplayResult);
        layoutWarning=(LinearLayout)v.findViewById(R.id.layoutWarning);

        edittextExchangeRate=(EditText)v.findViewById(R.id.editTextExchangeRate);
        edittextUnits=(EditText)v.findViewById(R.id.editTextUnit);
        spinnerMarginRatio=(Spinner)v.findViewById(R.id.spinnerMarginRatio);
        textViewResultAmontRequired=(TextView) v.findViewById(R.id.textViewResultAmountRequired);
        buttonCalculate=(Button)v.findViewById(R.id.buttonCalculate);
        buttonReset=(Button)v.findViewById(R.id.buttoncurrencyReset);

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
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, marginratio);

        // Drop down layout style - list view with radio button
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerMarginRatio.setAdapter(Adapter);

        buttonCalculate.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
        return v;
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
            InputMethodManager inputManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

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
            // Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            // Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }


}
