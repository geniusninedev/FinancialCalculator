package com.nineinfosys.financialcalculator.MarginCalcualtor.profitMargin;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.nineinfosys.financialcalculator.R;

import java.text.DecimalFormat;

public class ProfitMarginMain extends Fragment implements View.OnClickListener{



    EditText editTextCost,editTextSaleRevenue,editTextGrossMargin;
    TextView textViewResultCost,textViewResultSalerevenue,textViewResultGrossMargin,textViewResultGrossMarginPercent,textViewResultGrossMarginINR,textViewResultofcostrevenuegross,textViewResultGrossProfit,textViewResultMarkup;
    Button buttonCalcualte,buttonReset;
    profitMarginCalcualtor profitMarginCalcualtor;
    double cost,saleRevenue,grossMargin;

    LinearLayout layoutDisplayResult,layoutWarning;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profit_margin_main, null);


        //keyboard hidden first time
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        MobileAds.initialize(getActivity(), getString(R.string.ads_app_id));
        AdView mAdView = (AdView) v.findViewById(R.id.adViewProfitMarginCalculator);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //customize toolbar
    /*    android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Profit Margin Calcualtor");*/

        //initialization of profitmargin calculation class
        profitMarginCalcualtor=new profitMarginCalcualtor();

        //declarting designing tools
        layoutDisplayResult=(LinearLayout)v.findViewById(R.id.layoutDisplayResult);
        layoutWarning=(LinearLayout)v.findViewById(R.id.layoutWarning);
        editTextCost=(EditText)v.findViewById(R.id.editTextCost);
        editTextSaleRevenue=(EditText)v.findViewById(R.id.editTextSaleRevenue);
        editTextGrossMargin=(EditText)v.findViewById(R.id.editTextGrossMargin);
        textViewResultCost=(TextView)v.findViewById(R.id.textViewResultCost);
        textViewResultSalerevenue=(TextView)v.findViewById(R.id.textViewResultSaleRevenue);
        textViewResultGrossMargin=(TextView)v.findViewById(R.id.textViewResultGrossMargin);
        textViewResultGrossMarginPercent=(TextView)v.findViewById(R.id.textViewInterestperINRpgm);
        textViewResultGrossMarginINR=(TextView)v.findViewById(R.id.textViewInterestperINRA);
        textViewResultofcostrevenuegross=(TextView)v.findViewById(R.id.textViewResultofcostrevenuegross);
        textViewResultGrossProfit=(TextView)v.findViewById(R.id.textViewResultGrossProfit);
        textViewResultMarkup=(TextView)v.findViewById(R.id.textViewResultMarkup);
        buttonCalcualte=(Button)v.findViewById(R.id.buttonCalculate);
        buttonReset=(Button)v.findViewById(R.id.buttonProfitReset);

        buttonCalcualte.setOnClickListener(this);
        buttonReset.setOnClickListener(this);

        return v;
    }
    private void calcualteProfitMargin() {
        if (editTextGrossMargin.getText().toString().trim().equals("") && editTextSaleRevenue.getText().toString().trim().equals("")  && editTextCost.getText().toString().trim().equals("") )
        {
            layoutWarning.setVisibility(View.VISIBLE);
            layoutDisplayResult.setVisibility(View.GONE);

        }
        else if (editTextGrossMargin.getText().toString().trim().equals("") && editTextSaleRevenue.getText().toString().trim().equals(""))
        {
            layoutWarning.setVisibility(View.VISIBLE);
            layoutDisplayResult.setVisibility(View.GONE);
        }
        else if (editTextCost.getText().toString().trim().equals("") && editTextSaleRevenue.getText().toString().trim().equals(""))
        {
            layoutWarning.setVisibility(View.VISIBLE);
            layoutDisplayResult.setVisibility(View.GONE);
        }
        else if (editTextCost.getText().toString().trim().equals("") && editTextGrossMargin.getText().toString().trim().equals(""))
        {
            layoutWarning.setVisibility(View.VISIBLE);
            layoutDisplayResult.setVisibility(View.GONE);
        }
        else if (!(editTextCost.getText().toString().trim().equals("")) && !(editTextGrossMargin.getText().toString().trim().equals("") )
                && !(editTextSaleRevenue.getText().toString().trim().equals("") ))
        {
            layoutWarning.setVisibility(View.VISIBLE);
            layoutDisplayResult.setVisibility(View.GONE);
        }
        else {

            //for hiding keyboard
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

            layoutDisplayResult.setVisibility(View.VISIBLE);
            layoutWarning.setVisibility(View.GONE);

            if (editTextGrossMargin.getText().toString().trim().equals("")) {
                //getting value from edittext
                cost = Double.parseDouble(editTextCost.getText().toString().trim());
                saleRevenue = Double.parseDouble(editTextSaleRevenue.getText().toString().trim());

                double grossMarginresult = profitMarginCalcualtor.calculateGrossMargin(cost, saleRevenue);
                double grossProfitresult = profitMarginCalcualtor.calculateGrossProfit(cost, saleRevenue);
                double grossMarkupresult = profitMarginCalcualtor.calculateMarkup(cost);



                textViewResultGrossMargin.setVisibility(View.VISIBLE);
                textViewResultGrossMarginPercent.setVisibility(View.VISIBLE);
                textViewResultGrossMarginINR.setVisibility(View.GONE);
                textViewResultCost.setVisibility(View.GONE);
                textViewResultSalerevenue.setVisibility(View.GONE);

                textViewResultofcostrevenuegross.setText(new DecimalFormat("##.##").format(grossMarginresult));
                textViewResultGrossProfit.setText(new DecimalFormat("##.##").format(grossProfitresult));
                textViewResultMarkup.setText(new DecimalFormat("##.##").format(grossMarkupresult)+"%");

            }

            else if (editTextSaleRevenue.getText().toString().trim().equals("")) {
                //getting value from edittext
                cost = Double.parseDouble(editTextCost.getText().toString().trim());
                grossMargin = Double.parseDouble(editTextGrossMargin.getText().toString().trim());

                double calculateSalesRevenue = profitMarginCalcualtor.calculateSalesRevenue(cost, grossMargin);
                double calculateSalesRevenueGrossProfit = profitMarginCalcualtor.calculateSalesRevenueGrossProfit(cost);
                double calculateSalesRevenueMarkup = profitMarginCalcualtor.calculateSalesRevenueMarkup(cost);


                textViewResultSalerevenue.setVisibility(View.VISIBLE);
                textViewResultGrossMarginPercent.setVisibility(View.GONE);
                textViewResultGrossMarginINR.setVisibility(View.VISIBLE);
                textViewResultGrossMargin.setVisibility(View.GONE);
                textViewResultCost.setVisibility(View.GONE);


                textViewResultofcostrevenuegross.setText(new DecimalFormat("##.##").format(calculateSalesRevenue));
                textViewResultGrossProfit.setText(new DecimalFormat("##.##").format(calculateSalesRevenueGrossProfit));
                textViewResultMarkup.setText(new DecimalFormat("##.##").format(calculateSalesRevenueMarkup)+"%");
            }

            else if (editTextCost.getText().toString().trim().equals("")) {

                //getting value from edittext
                saleRevenue = Double.parseDouble(editTextSaleRevenue.getText().toString().trim());
                grossMargin = Double.parseDouble(editTextGrossMargin.getText().toString().trim());

                double CostGainPercent = profitMarginCalcualtor.calculateCostGainPercent(saleRevenue, grossMargin);
                double calculateCost = profitMarginCalcualtor.calculateCost(saleRevenue);
                double calculateCostMarkup = profitMarginCalcualtor.calculateCostMarkup();


                textViewResultCost.setVisibility(View.VISIBLE);
                textViewResultGrossMarginPercent.setVisibility(View.GONE);
                textViewResultGrossMarginINR.setVisibility(View.VISIBLE);
                textViewResultSalerevenue.setVisibility(View.GONE);
                textViewResultGrossMargin.setVisibility(View.GONE);

                //setting values to editext
                textViewResultofcostrevenuegross.setText(new DecimalFormat("##.##").format(calculateCost));
                textViewResultGrossProfit.setText(new DecimalFormat("##.##").format(CostGainPercent));
                textViewResultMarkup.setText(new DecimalFormat("##.##").format(calculateCostMarkup)+"%");

            }
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.buttonCalculate:
                calcualteProfitMargin();
                break;

            case R.id.buttonProfitReset:
                layoutDisplayResult.setVisibility(View.GONE);
                layoutWarning.setVisibility(View.GONE);
                editTextCost.setText(null);
                editTextSaleRevenue.setText(null);
                editTextGrossMargin.setText(null);
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
