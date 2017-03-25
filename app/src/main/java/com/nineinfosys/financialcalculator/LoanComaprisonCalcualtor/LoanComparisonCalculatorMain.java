package com.nineinfosys.financialcalculator.LoanComaprisonCalcualtor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.nineinfosys.financialcalculator.LoanCalcualtor.loancalculation;
import com.nineinfosys.financialcalculator.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LoanComparisonCalculatorMain extends AppCompatActivity implements View.OnClickListener {
    // declartaion of the designing tool and supported classes
    EditText edittextLaonAmount, edittextInterestRate, edittextLoanMonths, edittextExtraPayment, edittextPropertytax, edittextInsurance, edittextPMI, edittextPropertyPrice, editTextalertpropertyprice, edittextalertdownpayment;
    EditText editTextInterestRateSecond,editTextMonthSecond;
    Button buttonLoanCalculate, butttonLoanAdvanced, butttonLoanBasic, buttonLoanCalcvalue, buttonloanReset, buttonLoanEmail, buttonLoanReport, buttonLoanAortization;
    TextView textViewMonthlyPayment, textViewTotalPayment, textViewTotalInterest, textViewAnnualPayment, textViewMortgageConstant,textViewMonthlyPaymentSecond,textViewTotalPaymentSecond,textViewTotalInterestSecond,textViewAnnualPaymentSecond,textViewMortgageConstantSecond;
    LinearLayout advancedlayout, layoutDisplayResult,layoutwarning;
    Spinner spinneralerttaxtype;
    double alerttoatalLoanAmount=0.0;
    double monthlyPayment, r, loanAmount, loanPeriod, interestRate,interestRateSecond,loanPeriodSecond;
    double LoanMonthlyPayment, LoanAnnualPayment, LoanTotalPayment, mortgageConstant, LoanInterest,LoanMonthlyPaymentSecond,LoanTotalPaymentSecond,LoanInterestSecond,LoanAnnualPaymentSecond,mortgageConstantSecond;

    loancalculation emi,emiSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loancomparisoncalculator);


        //keyboard hidden first time
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //customize toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Loan Comparison Calcualtor");

        //initalization of designing tool
        layoutDisplayResult = (LinearLayout) this.findViewById(R.id.layoutDisplayResult);
        layoutwarning=(LinearLayout)this.findViewById(R.id.layoutWarning);


        //edittext  and button initalization
        edittextLaonAmount = (EditText) findViewById(R.id.editTextLoanAmount);
        edittextInterestRate = (EditText) findViewById(R.id.editTextLoanInterestRate);
        editTextInterestRateSecond=(EditText) findViewById(R.id.editTextLoanInterestRate2);
        editTextMonthSecond=(EditText) findViewById(R.id.editTextloanmonths2);
        edittextLoanMonths = (EditText) findViewById(R.id.editTextloanmonths);

        textViewMonthlyPayment = (TextView) findViewById(R.id.textViewMonthlyPaymentAmount);
        textViewTotalPayment = (TextView) findViewById(R.id.textViewTotalPaymentAmount);
        textViewTotalInterest = (TextView) findViewById(R.id.textViewTotalInterestAmount);
        textViewAnnualPayment = (TextView) findViewById(R.id.textViewAnnualPaymentAmount);
        textViewMortgageConstant = (TextView) findViewById(R.id.textViewmortgageConstant);

        textViewMonthlyPaymentSecond = (TextView) findViewById(R.id.textViewMonthlyPaymentAmountSecond);
        textViewTotalPaymentSecond = (TextView) findViewById(R.id.textViewTotalPaymentAmountSecond);
        textViewTotalInterestSecond = (TextView) findViewById(R.id.textViewTotalInterestAmountSecond);
        textViewAnnualPaymentSecond = (TextView) findViewById(R.id.textViewAnnualPaymentAmountSecond);
        textViewMortgageConstantSecond = (TextView) findViewById(R.id.textViewmortgageConstantSecond);

        buttonLoanCalcvalue = (Button) findViewById(R.id.buttonLoanCalcvalue);
        buttonLoanCalculate = (Button) findViewById(R.id.buttonLoanCalculate);
       // butttonLoanBasic = (Button) findViewById(R.id.buttonLoanBasic);
       // butttonLoanAdvanced = (Button) findViewById(R.id.buttonLoanAdvance);
        buttonloanReset = (Button) findViewById(R.id.buttonLoanReset);
        buttonLoanEmail = (Button) findViewById(R.id.buttonLoanEmail);



        buttonLoanCalculate.setOnClickListener(this);
        buttonLoanCalcvalue.setOnClickListener(this);
        buttonloanReset.setOnClickListener(this);
        buttonLoanEmail.setOnClickListener(this);

    }
    private void CalculateLoan() {
        if(edittextLaonAmount.getText().toString().trim().equals("")&& edittextInterestRate.getText().toString().trim().equals("")&& edittextLoanMonths.getText().toString().trim().equals("")&&editTextInterestRateSecond.getText().toString().trim().equals("")&& editTextMonthSecond.getText().toString().trim().equals("") )
        {
            layoutwarning.setVisibility(View.VISIBLE);
            layoutDisplayResult.setVisibility(LinearLayout.GONE);
        }
      else  if (edittextLaonAmount.getText().toString().trim().equals("")|| edittextLaonAmount.getText().toString().isEmpty()) {
            edittextLaonAmount.setError("Loan Amount is Required");
            layoutwarning.setVisibility(View.GONE);
            layoutDisplayResult.setVisibility(LinearLayout.GONE);
        }
         else if (edittextInterestRate.getText().toString().trim().equals("")|| edittextInterestRate.getText().toString().isEmpty()) {
            edittextInterestRate.setError("Enter Interest Rate");
            layoutwarning.setVisibility(View.GONE);
            layoutDisplayResult.setVisibility(LinearLayout.GONE);
        }
        else if (edittextLoanMonths.getText().toString().trim().equals("")|| edittextLoanMonths.getText().toString().isEmpty()) {
            edittextLoanMonths.setError("Enter Loan term in Months");
            layoutwarning.setVisibility(View.GONE);
            layoutDisplayResult.setVisibility(LinearLayout.GONE);
        }
        else if (editTextInterestRateSecond.getText().toString().trim().equals("")|| editTextInterestRateSecond.getText().toString().isEmpty()) {
            editTextInterestRateSecond.setError("Enter Interest Rate");
            layoutwarning.setVisibility(View.GONE);
            layoutDisplayResult.setVisibility(LinearLayout.GONE);
        }
        else if (editTextMonthSecond.getText().toString().trim().equals("")|| editTextMonthSecond.getText().toString().isEmpty()) {
            editTextMonthSecond.setError("Enter Loan term in Months");
            layoutwarning.setVisibility(View.GONE);
            layoutDisplayResult.setVisibility(LinearLayout.GONE);
        }else {
            //for hiding keyboard
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

            loanAmount = Double.parseDouble(edittextLaonAmount.getText().toString());
            interestRate = Double.parseDouble(edittextInterestRate.getText().toString());
            loanPeriod = Double.parseDouble(edittextLoanMonths.getText().toString());
            interestRateSecond = Double.parseDouble(editTextInterestRateSecond.getText().toString());
            loanPeriodSecond = Double.parseDouble(editTextMonthSecond.getText().toString());

            //calling method from loanCalculation
            emi = new loancalculation(loanAmount, interestRate, loanPeriod);
            emiSecond = new loancalculation(loanAmount, interestRateSecond, loanPeriodSecond);

            //for term first
            LoanMonthlyPayment = emi.calculateEMI();
            LoanTotalPayment = emi.calculateTotalPayment();
            LoanInterest = emi.calculateTotalInterest();
            LoanAnnualPayment = emi.calculateAnnualPayment();
            mortgageConstant = emi.MortgageConstant();

            //for term second
            LoanMonthlyPaymentSecond = emiSecond.calculateEMI();
            LoanTotalPaymentSecond = emiSecond.calculateTotalPayment();
            LoanInterestSecond = emiSecond.calculateTotalInterest();
            LoanAnnualPaymentSecond = emiSecond.calculateAnnualPayment();
            mortgageConstantSecond = emiSecond.MortgageConstant();


            //setting value to textview
            layoutDisplayResult.setVisibility(LinearLayout.VISIBLE);
            layoutwarning.setVisibility(View.GONE);

            //display result term 1
            textViewMonthlyPayment.setText(new DecimalFormat("##.##").format(LoanMonthlyPayment));
            textViewTotalPayment.setText(new DecimalFormat("##.##").format(LoanTotalPayment));
            textViewTotalInterest.setText(new DecimalFormat("##.##").format(LoanInterest));
            textViewAnnualPayment.setText(new DecimalFormat("##.##").format(LoanAnnualPayment));
            textViewMortgageConstant.setText(new DecimalFormat("##.##").format(mortgageConstant));

            //display result term 2
            textViewMonthlyPaymentSecond.setText(new DecimalFormat("##.##").format(LoanMonthlyPaymentSecond));
            textViewTotalPaymentSecond.setText(new DecimalFormat("##.##").format(LoanTotalPaymentSecond));
            textViewTotalInterestSecond.setText(new DecimalFormat("##.##").format(LoanInterestSecond));
            textViewAnnualPaymentSecond.setText(new DecimalFormat("##.##").format(LoanAnnualPaymentSecond));
            textViewMortgageConstantSecond.setText(new DecimalFormat("##.##").format(mortgageConstantSecond));
        }
    }
    private void CalculateCalcValue()
    {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View alertLayout = inflater.inflate(R.layout.dialog_calc_value, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        editTextalertpropertyprice = (EditText) alertLayout.findViewById(R.id.editextalertpropertyprice);
        edittextalertdownpayment = (EditText) alertLayout.findViewById(R.id.edittextalertdownpayment);
        spinneralerttaxtype = (Spinner) alertLayout.findViewById(R.id.spinnertaxtype);

        List<String> timings = new ArrayList<String>();
        timings.add("Amount");
        timings.add("Percent");
        // Creating adapter for spinner
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timings);

        // Drop down layout style - list view with radio button
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinneralerttaxtype.setAdapter(Adapter);

        // this is set the view from XML inside AlertDialog
        alertDialogBuilder.setView(alertLayout);
        alertDialogBuilder.setPositiveButton("ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        double alertpropertyprice=0.0;
                        double alertdownpayment=0.0;
                        if (TextUtils.isEmpty(editTextalertpropertyprice.getText())) {
                            editTextalertpropertyprice.setError("Please Enter Property price .");
                            editTextalertpropertyprice.requestFocus();
                            return;
                        }
                       else if (TextUtils.isEmpty(edittextalertdownpayment.getText())) {
                            edittextalertdownpayment.setError("Please Enter Down payment.");
                            edittextalertdownpayment.requestFocus();
                            return;
                        }
                        else {
                            alertpropertyprice = Integer.parseInt(editTextalertpropertyprice.getText().toString());
                            alertdownpayment = Integer.parseInt(edittextalertdownpayment.getText().toString());
                            String alertspinnertax = spinneralerttaxtype.getSelectedItem().toString().trim();
                          //  Toast.makeText(this, "" + alertspinnertax, Toast.LENGTH_SHORT).show();
                            if (alertspinnertax == "Amount") {
                                alerttoatalLoanAmount = alertpropertyprice - alertdownpayment;

                            } else {
                                double alertLoanAmount = (double) ((alertpropertyprice) * alertdownpayment) / 100;
                                alerttoatalLoanAmount = alertpropertyprice - alertLoanAmount;
                            }
                            edittextLaonAmount.setText(new DecimalFormat("##.##").format(alerttoatalLoanAmount));
                        }
                        }

                });
        alertDialogBuilder.setNegativeButton("Cancle",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonLoanCalculate:
                CalculateLoan();
                break;

            case R.id.buttonLoanCalcvalue:
                CalculateCalcValue();
                break;


            case R.id.buttonLoanReset:
                layoutDisplayResult.setVisibility(View.GONE);
                edittextLaonAmount.setText(null);
                edittextInterestRate.setText(null);
                edittextLoanMonths.setText(null);
                editTextInterestRateSecond.setText(null);
                editTextMonthSecond.setText(null);
                break;

            case R.id.buttonLoanEmail:
                String message=" Loan Term 1"+"\n\nLoan Amount:"+new DecimalFormat("##.##").format(loanAmount)+"\n Interest Rate:"+new DecimalFormat("##.##").format(interestRate)+"\n Loan Period:"+new DecimalFormat("##.##").format(loanPeriod)+"\n Monthly Payment:"+new DecimalFormat("##.##").format(LoanMonthlyPayment)+"\n Total Interest Amount:"+new DecimalFormat("##.##").format(LoanInterest)+"\n Total Payment:"+new DecimalFormat("##.##").format(LoanTotalPayment)+"\nAnnual Payment:"+new DecimalFormat("##.##").format(LoanAnnualPayment)+
                "\n\n Loan Term 2"+"\n\nLoan Amount:"+new DecimalFormat("##.##").format(loanAmount)+"\n Interest Rate:"+new DecimalFormat("##.##").format(interestRateSecond)+"\n Loan Period:"+new DecimalFormat("##.##").format(loanPeriodSecond)+"\n Monthly Payment:"+new DecimalFormat("##.##").format(LoanMonthlyPaymentSecond)+"\n Total Interest Amount:"+new DecimalFormat("##.##").format(LoanInterestSecond)+"\n Total Payment:"+new DecimalFormat("##.##").format(LoanTotalPaymentSecond)+"\nAnnual Payment:"+new DecimalFormat("##.##").format(LoanAnnualPaymentSecond);
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
