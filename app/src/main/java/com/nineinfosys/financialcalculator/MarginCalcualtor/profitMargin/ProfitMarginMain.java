package com.nineinfosys.financialcalculator.MarginCalcualtor.profitMargin;

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

public class ProfitMarginMain extends AppCompatActivity implements View.OnClickListener{

    EditText editTextCost,editTextSaleRevenue,editTextGrossMargin;
    TextView textViewResultCost,textViewResultSalerevenue,textViewResultGrossMargin,textViewResultGrossMarginPercent,textViewResultGrossMarginINR,textViewResultofcostrevenuegross,textViewResultGrossProfit,textViewResultMarkup;
    Button buttonCalcualte,buttonReset;
    profitMarginCalcualtor profitMarginCalcualtor;
    double cost,saleRevenue,grossMargin;

    LinearLayout layoutDisplayResult,layoutWarning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profit_margin_main);

        //keyboard hidden first time
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //customize toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Profit Margin Calcualtor");

        //initialization of profitmargin calculation class
        profitMarginCalcualtor=new profitMarginCalcualtor();

        //declarting designing tools
        layoutDisplayResult=(LinearLayout)findViewById(R.id.layoutDisplayResult);
        layoutWarning=(LinearLayout)findViewById(R.id.layoutWarning);
        editTextCost=(EditText)findViewById(R.id.editTextCost);
        editTextSaleRevenue=(EditText)findViewById(R.id.editTextSaleRevenue);
        editTextGrossMargin=(EditText)findViewById(R.id.editTextGrossMargin);
        textViewResultCost=(TextView)findViewById(R.id.textViewResultCost);
        textViewResultSalerevenue=(TextView)findViewById(R.id.textViewResultSaleRevenue);
        textViewResultGrossMargin=(TextView)findViewById(R.id.textViewResultGrossMargin);
        textViewResultGrossMarginPercent=(TextView)findViewById(R.id.textViewInterestperINRpgm);
        textViewResultGrossMarginINR=(TextView)findViewById(R.id.textViewInterestperINRA);
        textViewResultofcostrevenuegross=(TextView)findViewById(R.id.textViewResultofcostrevenuegross);
        textViewResultGrossProfit=(TextView)findViewById(R.id.textViewResultGrossProfit);
        textViewResultMarkup=(TextView)findViewById(R.id.textViewResultMarkup);
        buttonCalcualte=(Button)findViewById(R.id.buttonCalculate);
        buttonReset=(Button)findViewById(R.id.buttonProfitReset);

        buttonCalcualte.setOnClickListener(this);
        buttonReset.setOnClickListener(this);

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
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

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
                textViewResultMarkup.setText(new DecimalFormat("##.##").format(grossMarkupresult));

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
                textViewResultMarkup.setText(new DecimalFormat("##.##").format(calculateSalesRevenueMarkup));
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
                textViewResultMarkup.setText(new DecimalFormat("##.##").format(calculateCostMarkup));

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
