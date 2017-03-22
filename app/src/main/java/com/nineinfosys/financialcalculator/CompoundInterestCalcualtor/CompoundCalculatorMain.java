package com.nineinfosys.financialcalculator.CompoundInterestCalcualtor;

import android.content.Context;
import android.content.Intent;
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


import com.nineinfosys.financialcalculator.Amortization.AmortizationCompoundTable;
import com.nineinfosys.financialcalculator.R;
import com.nineinfosys.financialcalculator.Report.CompoundReport;

import java.text.DecimalFormat;

public class CompoundCalculatorMain extends AppCompatActivity implements View.OnClickListener{
    EditText editTextCompounPrincipal,editTextCompounMonthlyDeposite,editTextCompounPeroidMonth,editTextCompounAnnualrate,editTextCompoundsperyear;
    Button buttoncompoundCalculate,buttoncompoundReset, buttonCompoundEmail, buttonCompoundReport, buttonCompoundAortization;
    TextView textViewTotalPrincipalAmount,textViewInterestAmount,textViewMaturityValueAmount,textViewAPY;
    LinearLayout layoutcompoundresult,layoutWarning;
    CompoundCalculation compoundCalculation;

    double interestRate,PrincipalAmount,PeriodMonth,Compoundsperyear,compoundAmount,InterestAmount,APY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound_main);

        //keyboard hidden first time
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //customize toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Compound Calcualtor");

        //declaration of dsesigning tools
        layoutcompoundresult=(LinearLayout)this.findViewById(R.id.layoutcompoundresult);
        layoutWarning=(LinearLayout)this.findViewById(R.id.layoutWarning);

       // spinnerCompoundinglist=(Spinner)findViewById(R.id.spinnerinterestCompounding);
        editTextCompounPrincipal=(EditText)findViewById(R.id.editTextCompounPrincipal) ;
       // editTextCompounMonthlyDeposite=(EditText)findViewById(R.id.editTextCompounMonthlyDeposite);
        editTextCompounPeroidMonth=(EditText) findViewById(R.id.editTextCompounPeroidMonth);
        editTextCompoundsperyear=(EditText) findViewById(R.id.editTextCompounds);
        editTextCompounAnnualrate=(EditText)findViewById(R.id.editTextCompounAnnualrate);
        buttoncompoundCalculate=(Button)findViewById(R.id.buttonCompoundCalculate);
        buttoncompoundReset=(Button)findViewById(R.id.buttonCompoundReset);
        buttonCompoundAortization = (Button) findViewById(R.id.buttonCompoundAmortization);
        buttonCompoundEmail = (Button) findViewById(R.id.buttonLoanEmail);
        buttonCompoundReport = (Button) findViewById(R.id.buttonLoanReport);

        textViewTotalPrincipalAmount=(TextView)findViewById(R.id.textViewTotalPrincipalAmount);
        textViewInterestAmount=(TextView)findViewById(R.id.textViewInterestAmount);
        textViewMaturityValueAmount=(TextView)findViewById(R.id.textViewMaturityValueAmount);
        textViewAPY=(TextView)findViewById(R.id.textViewAPYAmount);

        buttoncompoundCalculate.setOnClickListener(this);
        buttonCompoundAortization.setOnClickListener(this);
        buttonCompoundReport.setOnClickListener(this);
        buttoncompoundReset.setOnClickListener(this);
        buttonCompoundEmail.setOnClickListener(this);
    }

    private void CalculateCompound() {


        if(editTextCompounPrincipal.getText().toString().trim().equals("") && editTextCompounAnnualrate.getText().toString().trim().equals("") && editTextCompoundsperyear.getText().toString().trim().equals("")
                && editTextCompounPeroidMonth.getText().toString().trim().equals("") )
        {
            layoutWarning.setVisibility(View.VISIBLE);
            layoutcompoundresult.setVisibility(View.GONE);
        }
        else if (editTextCompounPrincipal.getText().toString().trim().equals("")|| editTextCompounPrincipal.getText().toString().isEmpty()) {
            editTextCompounPrincipal.setError("Loan Amount is Required");
            layoutWarning.setVisibility(View.GONE);
            layoutcompoundresult.setVisibility(View.GONE);
        }
        else if (editTextCompounAnnualrate.getText().toString().trim().equals("")|| editTextCompounAnnualrate.getText().toString().isEmpty()) {
            editTextCompounAnnualrate.setError("Enter Interest Rate");
            layoutWarning.setVisibility(View.GONE);
            layoutcompoundresult.setVisibility(View.GONE);
        }
        else if (editTextCompoundsperyear.getText().toString().trim().equals("")|| editTextCompoundsperyear.getText().toString().isEmpty()) {
            editTextCompoundsperyear.setError("Enter Compound Peroids in year");
            layoutWarning.setVisibility(View.GONE);
            layoutcompoundresult.setVisibility(View.GONE);
        }
        else if (editTextCompounPeroidMonth.getText().toString().trim().equals("")|| editTextCompounPeroidMonth.getText().toString().isEmpty()) {
            editTextCompounPeroidMonth.setError("Enter Loan term in Months");
            layoutWarning.setVisibility(View.GONE);
            layoutcompoundresult.setVisibility(View.GONE);
        }else {

            //for hiding keyboard
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

            PrincipalAmount = Double.parseDouble(editTextCompounPrincipal.getText().toString());
            interestRate =  Double.parseDouble(editTextCompounAnnualrate.getText().toString());
            // MonthlyDeposite = (Integer.parseInt(editTextCompounMonthlyDeposite.getText().toString()));
            Compoundsperyear =  Double.parseDouble(editTextCompoundsperyear.getText().toString());
            PeriodMonth =  Double.parseDouble(editTextCompounPeroidMonth.getText().toString());

            compoundCalculation = new CompoundCalculation(PrincipalAmount, interestRate, Compoundsperyear, PeriodMonth);
            compoundAmount = compoundCalculation.CalculateCompoundInterest();
            InterestAmount = compoundCalculation.CalculateInterestAmount();
             APY = compoundCalculation.CalculateAPY();
            layoutcompoundresult.setVisibility(View.VISIBLE);
            layoutWarning.setVisibility(View.GONE);
            textViewTotalPrincipalAmount.setText(new DecimalFormat("##.##").format(PrincipalAmount));
            textViewInterestAmount.setText(new DecimalFormat("##.##").format(InterestAmount));
            textViewMaturityValueAmount.setText(new DecimalFormat("##.##").format(compoundAmount));
            textViewAPY.setText(new DecimalFormat("##.####").format(APY));
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCompoundCalculate:
                CalculateCompound();
                break;


            case R.id.buttonCompoundReset:
                layoutWarning.setVisibility(View.GONE);
                layoutcompoundresult.setVisibility(View.GONE);
                editTextCompounPrincipal.setText(null);
                editTextCompoundsperyear.setText(null);
                editTextCompounPeroidMonth.setText(null);
                editTextCompounAnnualrate.setText(null);
                break;

            case R.id.buttonCompoundAmortization:
                Intent i1=new Intent(this,AmortizationCompoundTable.class);
                //i1.putExtra("Monthlypayment",LoanMonthlyPayment);
                i1.putExtra("Rate",interestRate);
                i1.putExtra("loanAmount",PrincipalAmount);
                i1.putExtra("loanPeriod",PeriodMonth);
                startActivity(i1);
                break;

            case R.id.buttonLoanReport:
                Intent i2=new Intent(this,CompoundReport.class);
                // Toast.makeText(this, " ToatalInterest" + interestRate, Toast.LENGTH_SHORT).show();
                i2.putExtra("PrincipalAmount",PrincipalAmount);
                i2.putExtra("interestRate",interestRate);
                i2.putExtra("loanPeriod",PeriodMonth);
                i2.putExtra("Compoundsperyear",Compoundsperyear);
                i2.putExtra("InterestAmount",InterestAmount);
                i2.putExtra("compoundAmount",compoundAmount);
                i2.putExtra("APY",APY);
                startActivity(i2);
                break;
            case R.id.buttonLoanEmail:
                String message="Loan Amount:"+PrincipalAmount+"\n Interest Rate:"+interestRate+"\n Loan Period:"+PeriodMonth+"\n Compounds per year:"+Compoundsperyear+"\n Interest Amount:"+InterestAmount+"\n Maturity Value:"+compoundAmount+"APY:"+APY;
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ });
                email.putExtra(Intent.EXTRA_SUBJECT, "Loan Details");
                email.putExtra(Intent.EXTRA_TEXT,message );
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Select Email Client"));
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
