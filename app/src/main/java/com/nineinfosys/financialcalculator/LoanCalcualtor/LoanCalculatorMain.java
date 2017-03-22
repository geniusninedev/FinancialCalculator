package com.nineinfosys.financialcalculator.LoanCalcualtor;

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

import com.nineinfosys.financialcalculator.Amortization.LoanAmortization;
import com.nineinfosys.financialcalculator.R;
import com.nineinfosys.financialcalculator.Report.LoanReport;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LoanCalculatorMain extends AppCompatActivity implements View.OnClickListener {
    // declartaion of the designing tool and supported classes
    EditText edittextLaonAmount, edittextInterestRate, edittextLoanMonths, edittextExtraPayment, edittextPropertytax, edittextInsurance, edittextPMI, edittextPropertyPrice, editTextalertpropertyprice, edittextalertdownpayment;
    Button buttonLoanCalculate, butttonLoanAdvanced, butttonLoanBasic, buttonLoanCalcvalue, buttonloanReset, buttonLoanEmail, buttonLoanReport, buttonLoanAortization;
    TextView textViewMonthlyPayment, textViewTotalPayment, textViewTotalInterest, textViewAnnualPayment, textViewMortgageConstant;
    LinearLayout advancedlayout, layoutDisplayResult,layoutwarning;
    Spinner spinneralerttaxtype;
    double alerttoatalLoanAmount=0.0;
    double monthlyPayment, r, loanAmount, loanPeriod, interestRate;
    double LoanMonthlyPayment, LoanAnnualPayment, LoanTotalPayment, mortgageConstant, LoanInterest;

    loancalculation emi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laoncalculator);

        //keyboard hidden first time
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //customize toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Loan Calcualtor");

        //initalization of designing tool
        advancedlayout = (LinearLayout) this.findViewById(R.id.advancedlayout);
        layoutDisplayResult = (LinearLayout) this.findViewById(R.id.layoutDisplayResult);
        layoutwarning=(LinearLayout)this.findViewById(R.id.layoutWarning);


        //edittext  and button initalization
        edittextLaonAmount = (EditText) findViewById(R.id.editTextLoanAmount);
        edittextInterestRate = (EditText) findViewById(R.id.editTextLoanInterestRate);

        edittextLoanMonths = (EditText) findViewById(R.id.editTextloanmonths);
       // edittextExtraPayment = (EditText) findViewById(R.id.editTextLoanextraPayment);
        edittextPropertytax = (EditText) findViewById(R.id.editTextPropertyTax);
        edittextInsurance = (EditText) findViewById(R.id.editTextInsurance);
        edittextPMI = (EditText) findViewById(R.id.editTextPMI);
        edittextPropertyPrice = (EditText) findViewById(R.id.editTextPropertyprice);
        textViewMonthlyPayment = (TextView) findViewById(R.id.textViewMonthlyPaymentAmount);
        textViewTotalPayment = (TextView) findViewById(R.id.textViewTotalPaymentAmount);
        textViewTotalInterest = (TextView) findViewById(R.id.textViewTotalInterestAmount);
        textViewAnnualPayment = (TextView) findViewById(R.id.textViewAnnualPaymentAmount);
        textViewMortgageConstant = (TextView) findViewById(R.id.textViewmortgageConstant);

        buttonLoanCalcvalue = (Button) findViewById(R.id.buttonLoanCalcvalue);
        buttonLoanCalculate = (Button) findViewById(R.id.buttonLoanCalculate);
       // butttonLoanBasic = (Button) findViewById(R.id.buttonLoanBasic);
       // butttonLoanAdvanced = (Button) findViewById(R.id.buttonLoanAdvance);
        buttonloanReset = (Button) findViewById(R.id.buttonLoanReset);
        buttonLoanAortization = (Button) findViewById(R.id.buttonLoanAmortization);
        buttonLoanEmail = (Button) findViewById(R.id.buttonLoanEmail);
        buttonLoanReport = (Button) findViewById(R.id.buttonLoanReport);


        buttonLoanCalculate.setOnClickListener(this);
        buttonLoanCalcvalue.setOnClickListener(this);
        buttonLoanAortization.setOnClickListener(this);
        buttonLoanReport.setOnClickListener(this);
        buttonloanReset.setOnClickListener(this);
        //butttonLoanBasic.setOnClickListener(this);
        buttonLoanEmail.setOnClickListener(this);

    }
    private void CalculateLoan() {
        if(edittextLaonAmount.getText().toString().trim().equals("")&& edittextInterestRate.getText().toString().trim().equals("")&& edittextLoanMonths.getText().toString().trim().equals(""))
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
        }else {
            //for hiding keyboard
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

            loanAmount = Double.parseDouble(edittextLaonAmount.getText().toString());
            interestRate = Double.parseDouble(edittextInterestRate.getText().toString());
            loanPeriod = Double.parseDouble(edittextLoanMonths.getText().toString());

            //calling method from loanCalculation
            emi = new loancalculation(loanAmount, interestRate, loanPeriod);
            LoanMonthlyPayment = emi.calculateEMI();
            LoanTotalPayment = emi.calculateTotalPayment();
            LoanInterest = emi.calculateTotalInterest();
            LoanAnnualPayment = emi.calculateAnnualPayment();
            mortgageConstant = emi.MortgageConstant();


            //setting value to textview
            layoutDisplayResult.setVisibility(LinearLayout.VISIBLE);
            layoutwarning.setVisibility(View.GONE);
            textViewMonthlyPayment.setText(new DecimalFormat("##.##").format(LoanMonthlyPayment));
            textViewTotalPayment.setText(new DecimalFormat("##.##").format(LoanTotalPayment));
            textViewTotalInterest.setText(new DecimalFormat("##.##").format(LoanInterest));
            textViewAnnualPayment.setText(new DecimalFormat("##.##").format(LoanAnnualPayment));
            textViewMortgageConstant.setText(new DecimalFormat("##.##").format(mortgageConstant));
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
            case R.id.buttonLoanAmortization:
                Intent i1=new Intent(this,LoanAmortization.class);
                i1.putExtra("Monthlypayment",LoanMonthlyPayment);
                i1.putExtra("Rate",interestRate);
                i1.putExtra("loanAmount",loanAmount);
                i1.putExtra("loanPeriod",loanPeriod);
                startActivity(i1);
                break;
            case R.id.buttonLoanReport:
                Intent i2=new Intent(this,LoanReport.class);
               // Toast.makeText(this, " ToatalInterest" + interestRate, Toast.LENGTH_SHORT).show();
                i2.putExtra("PrincipalAmount",loanAmount);
                i2.putExtra("interestRate",interestRate);
                i2.putExtra("loanPeriod",loanPeriod);
                i2.putExtra("LoanMonthlyPayment",LoanMonthlyPayment);
                i2.putExtra("LoanInterestAmount",LoanInterest);
                i2.putExtra("LoanTotalPayment",LoanTotalPayment);
                i2.putExtra("LoanAnnualPayment",LoanAnnualPayment);
                startActivity(i2);
                break;

            case R.id.buttonLoanReset:
                layoutDisplayResult.setVisibility(View.GONE);
                edittextLaonAmount.setText(null);
                edittextInterestRate.setText(null);
                edittextLoanMonths.setText(null);
                break;

            case R.id.buttonLoanEmail:
                String message="Loan Amount:"+loanAmount+"\n Interest Rate:"+interestRate+"\n Loan Period:"+loanPeriod+"\n Monthly Payment:"+LoanMonthlyPayment+"\n Total Interest Amount:"+LoanInterest+"\n Total Payment:"+LoanTotalPayment+"\nAnnual Payment:"+LoanAnnualPayment;
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
