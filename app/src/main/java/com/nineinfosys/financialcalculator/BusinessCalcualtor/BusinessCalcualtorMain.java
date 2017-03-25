package com.nineinfosys.financialcalculator.BusinessCalcualtor;


/**
 * Created by Divya on 28-02-2017.
 */
import android.content.Context;
import android.content.Intent;
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

import com.nineinfosys.financialcalculator.Amortization.LoanAmortization;
import com.nineinfosys.financialcalculator.R;
import com.nineinfosys.financialcalculator.Report.BusinessLoanReport;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BusinessCalcualtorMain extends AppCompatActivity implements View.OnClickListener {
    EditText editTextLoanAmount,editTextInterestRate,editTextyear,editTextMonth,editTextOriginationFee,editTextCompoundsperyear,editTextDocumentationFee,editTextOtherFee;
    Button buttonCalculate,buttonReset, buttonEmail, buttonReport, buttonAmotization;
    TextView textViewMonthlyPayment,textViewTotalloanPayment,textViewInterest,textViewInterestFee,textViewtotalFee;
    LinearLayout layoutresult,layoutWarning;
    businessCalculator businessCalculator;
    double loanAmount,interestRate,originationfee,documentationfee,otherfee,monthlyPayment,TotalPayment,totalInterest,totalFee,totalAll,totalMonth,LoanAnnualPayment,totalAllfees;
    int year,month,compounds;
    Spinner spinnerMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_main);
        //customize toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Business Loan Calcualtor");

        //keyboard hidden first time
       this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //initalization of designing tools
        layoutresult=(LinearLayout)this.findViewById(R.id.layoutdisplayresult);
        layoutWarning=(LinearLayout)this.findViewById(R.id.layoutWarning);

        editTextLoanAmount=(EditText)findViewById(R.id.editTextLoanAmount) ;
        editTextInterestRate=(EditText)findViewById(R.id.editTextLoanInterestRate) ;
        editTextyear=(EditText)findViewById(R.id.editTextLoantermyear) ;

        editTextDocumentationFee=(EditText)findViewById(R.id.editTextDocumentationFee) ;
        editTextOriginationFee=(EditText)findViewById(R.id.editTextOriginationFee) ;
        editTextOtherFee=(EditText)findViewById(R.id.editTextOtherFee) ;

        textViewMonthlyPayment=(TextView) findViewById(R.id.textViewMonthlyAmount) ;
        textViewTotalloanPayment=(TextView) findViewById(R.id.textViewTotalPrincipalAmount) ;
        textViewInterest=(TextView) findViewById(R.id.textViewInterestAmount) ;
        textViewtotalFee=(TextView) findViewById(R.id.textViewTotalfees) ;
        textViewInterestFee=(TextView) findViewById(R.id.textViewInterestFeeValueAmount) ;

        buttonCalculate=(Button)findViewById(R.id.buttonCalculate);
        buttonReset=(Button)findViewById(R.id.buttonbusinessReset);
        buttonAmotization=(Button)findViewById(R.id.buttonAmortization);
        buttonReport=(Button)findViewById(R.id.buttonReport);
        buttonEmail=(Button)findViewById(R.id.buttonBusinessLoanEmail);

        spinnerMonth=(Spinner)findViewById(R.id.spinnerMonths);

        //adding value to spinner
        List<String> listmonths = new ArrayList<String>();
        listmonths.add("0");
        listmonths.add("1");
        listmonths.add("2");
        listmonths.add("3");
        listmonths.add("4");
        listmonths.add("5");
        listmonths.add("6");
        listmonths.add("7");
        listmonths.add("8");
        listmonths.add("9");
        listmonths.add("10");
        listmonths.add("11");
        listmonths.add("12");
        // Creating adapter for spinner
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item, listmonths);

        // Drop down layout style - list view with radio button
        Adapter.setDropDownViewResource( android.R.layout.simple_spinner_item);

        // attaching data adapter to spinner
        spinnerMonth.setAdapter(Adapter);

        buttonCalculate.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
        buttonReport.setOnClickListener(this);
        buttonAmotization.setOnClickListener(this);
        buttonEmail.setOnClickListener(this);


    }
    private void calculate() {

        if(editTextLoanAmount.getText().toString().trim().equals("") && editTextInterestRate.getText().toString().trim().equals("") && editTextyear.getText().toString().trim().equals("0") && editTextOriginationFee.getText().toString().trim().equals("")
                && editTextDocumentationFee.getText().toString().trim().equals("")&& editTextOtherFee.getText().toString().trim().equals(""))
        {
            layoutWarning.setVisibility(View.VISIBLE);
            layoutresult.setVisibility(View.GONE);
        }

        else if (editTextLoanAmount.getText().toString().trim().equals("") || editTextLoanAmount.getText().toString().isEmpty()) {
            editTextLoanAmount.setError("Loan Amount is Required");
           layoutWarning.setVisibility(View.GONE);
           layoutresult.setVisibility(View.GONE);
        } else if (editTextInterestRate.getText().toString().trim().equals("") || editTextInterestRate.getText().toString().isEmpty()) {
            editTextInterestRate.setError("Enter Interest Rate");
           layoutWarning.setVisibility(View.GONE);
           layoutresult.setVisibility(View.GONE);
        } else if (editTextyear.getText().toString().trim().equals("") || editTextyear.getText().toString().isEmpty()) {
           editTextyear.setError("Enter Compound Peroids in year");
           layoutWarning.setVisibility(View.GONE);
           layoutresult.setVisibility(View.GONE);
        }
        else if (editTextOriginationFee.getText().toString().trim().equals("") || editTextOriginationFee.getText().toString().isEmpty()) {
            editTextOriginationFee.setError("Enter Origination fee per year");
           layoutWarning.setVisibility(View.GONE);
           layoutresult.setVisibility(View.GONE);
        }
        else if (editTextDocumentationFee.getText().toString().trim().equals("") || editTextDocumentationFee.getText().toString().isEmpty()) {
            editTextDocumentationFee.setError("Enter Documentation Fees");
           layoutWarning.setVisibility(View.GONE);
           layoutresult.setVisibility(View.GONE);
        }
        else if (editTextOtherFee.getText().toString().trim().equals("") || editTextOtherFee.getText().toString().isEmpty()) {
            editTextOtherFee.setError("Enter Other Fees");
           layoutWarning.setVisibility(View.GONE);
           layoutresult.setVisibility(View.GONE);
        }else {
           //for hiding keyboard
           InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
           inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

            layoutresult.setVisibility(View.VISIBLE);

            loanAmount = Double.parseDouble(editTextLoanAmount.getText().toString());
            interestRate = Double.parseDouble(editTextInterestRate.getText().toString());
            year = Integer.parseInt(editTextyear.getText().toString());
            month = Integer.parseInt(spinnerMonth.getSelectedItem().toString().trim());

            originationfee = Double.parseDouble(editTextOriginationFee.getText().toString());
            documentationfee = Double.parseDouble(editTextDocumentationFee.getText().toString());
            otherfee = Double.parseDouble(editTextOtherFee.getText().toString());

            businessCalculator = new businessCalculator(loanAmount, interestRate, year, month, originationfee, documentationfee, otherfee);
            monthlyPayment = businessCalculator.calculateEMI();
            TotalPayment = businessCalculator.calculateTotalPayment();
            totalInterest = businessCalculator.calculateTotalInterest();
            totalFee = businessCalculator.calcualteFee();
            totalAll = businessCalculator.calculateTotalAll();
            totalMonth = businessCalculator.calculateMonth();
            LoanAnnualPayment = businessCalculator.calculateAnnualPayment();
           totalAllfees=businessCalculator.totalFee();

            textViewMonthlyPayment.setText(new DecimalFormat("##.##").format(monthlyPayment));
            textViewTotalloanPayment.setText(new DecimalFormat("##.##").format(TotalPayment));
           textViewtotalFee.setText(new DecimalFormat("##.##").format(totalAllfees));
            textViewInterest.setText(new DecimalFormat("##.##").format(totalInterest));
            textViewInterestFee.setText(new DecimalFormat("##.##").format(totalAll));
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonCalculate:
                calculate();
                break;

            case R.id.buttonAmortization:
                Intent i1 = new Intent(this, LoanAmortization.class);
                    i1.putExtra("Monthlypayment", monthlyPayment);
                    i1.putExtra("Rate", interestRate);
                    i1.putExtra("loanAmount", loanAmount);
                    i1.putExtra("loanPeriod", totalMonth);
                    startActivity(i1);
                break;

            case R.id.buttonReport:
                    Intent i2 = new Intent(this, BusinessLoanReport.class);
                    i2.putExtra("PrincipalAmount", loanAmount);
                    i2.putExtra("interestRate", interestRate);
                    i2.putExtra("loanPeriod", totalMonth);
                    i2.putExtra("originationfee", originationfee);
                    i2.putExtra("documentationfee", documentationfee);
                    i2.putExtra("otherfee", otherfee);
                    i2.putExtra("totalAll", totalAll);
                    i2.putExtra("totalAllfees", totalAllfees);
                    i2.putExtra("LoanMonthlyPayment", monthlyPayment);
                    i2.putExtra("LoanInterestAmount", totalInterest);
                    i2.putExtra("LoanTotalPayment", TotalPayment);
                    i2.putExtra("LoanAnnualPayment", LoanAnnualPayment);
                    startActivity(i2);
                break;
            case R.id.buttonbusinessReset:
                layoutresult.setVisibility(View.GONE);
                layoutWarning.setVisibility(View.GONE);
                editTextLoanAmount.setText(null);
                editTextInterestRate.setText(null);
                editTextyear.setText("0");
                 editTextOriginationFee.setText(null);
                editTextDocumentationFee.setText(null);
                editTextOtherFee.setText(null);
                break;
            case R.id.buttonBusinessLoanEmail:
                String message="Loan Amount:"+new DecimalFormat("##.##").format(loanAmount)+"\n Interest Rate:"+new DecimalFormat("##.##").format(interestRate)+"\n Loan Period:"+new DecimalFormat("##.##").format(totalMonth)+"\n Origination Fee:"+new DecimalFormat("##.##").format(originationfee)+"\n Documentation Fee:"+new DecimalFormat("##.##").format(documentationfee)+"\n Other Fee:"+new DecimalFormat("##.##").format(otherfee)+"\n Total of Interest+Fee"+new DecimalFormat("##.##").format(totalAll)+"\n Monthly Payment:"+new DecimalFormat("##.##").format(monthlyPayment)+"\n Total Interest Amount:"+new DecimalFormat("##.##").format(totalInterest)+"\n Total Payment:"+new DecimalFormat("##.##").format(TotalPayment)+"\n Annual Payment:"+new DecimalFormat("##.##").format(LoanAnnualPayment);
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

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

        }
    }
}
