package com.nineinfosys.financialcalculator.PaymentCalcualtor;

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


import com.nineinfosys.financialcalculator.Amortization.LoanAmortization;
import com.nineinfosys.financialcalculator.R;
import com.nineinfosys.financialcalculator.Report.LoanReport;

import java.text.DecimalFormat;



/**
 * Created by Divya
 */
public class PaymentCalculatorMain extends AppCompatActivity implements View.OnClickListener {

    EditText editTextLoanAmount,editTextLoanTerm,editTextInterestRate;
    TextView textViewMonthlyPayment,textViewInterest,textViewAnnualPayment,textViewTotalPayment;
    LinearLayout layoutdisplayresult,layoutWarning;
    paymentCalculator paymentCalculator;
     Button buttonCalculate,buttonAmortization,buttonReport,buttonReset,buttonMail;
    double loanAmount,interest,monthlyPayment,TotalPayment,AnnualPayment,totalInterest,loanterm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_calculator);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Payment Calcualtor");

        //keyboard hidden first time
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //initalization of designing tool
        layoutdisplayresult=(LinearLayout)findViewById(R.id.layoutdisplayresult) ;
        layoutWarning=(LinearLayout)findViewById(R.id.layoutWarning);
        editTextLoanAmount=(EditText)findViewById(R.id.editTextLoanAmount);
        editTextLoanTerm=(EditText)findViewById(R.id.editTextLoanTerm);
        editTextInterestRate=(EditText)findViewById(R.id.editTextLoanInterestRate);

        textViewMonthlyPayment=(TextView)findViewById(R.id.textViewMonthlyPaymentAmount);
        textViewInterest=(TextView)findViewById(R.id.textViewInterestAmount);
        textViewAnnualPayment=(TextView)findViewById(R.id.textViewAnnualPaymentAmount);
        textViewTotalPayment=(TextView)findViewById(R.id.textViewTotalPaymentAmount);

        buttonCalculate=(Button)findViewById(R.id.buttonCalculate);
        buttonAmortization=(Button)findViewById(R.id.buttonAmortization);
        buttonReport=(Button)findViewById(R.id.buttonReport);
        buttonReset=(Button)findViewById(R.id.buttonPaymentReset);
        buttonMail=(Button)findViewById(R.id.buttonPaymentMail);

        buttonCalculate.setOnClickListener(this);
        buttonAmortization.setOnClickListener(this);
        buttonReport.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
        buttonMail.setOnClickListener(this);

    }
    private void calculatePayment() {
        if(editTextLoanAmount.getText().toString().trim().equals("") && editTextInterestRate.getText().toString().trim().equals("")&& editTextInterestRate.getText().toString().trim().equals(""))
        {
            layoutWarning.setVisibility(View.VISIBLE);
            layoutdisplayresult.setVisibility(View.GONE);
        }
        else if (editTextLoanAmount.getText().toString().trim().equals("") || editTextLoanAmount.getText().toString().isEmpty()) {
            editTextLoanAmount.setError("Loan Amount is Required");
            layoutdisplayresult.setVisibility(View.GONE);
            layoutWarning.setVisibility(View.GONE);
        }
        else if (editTextInterestRate.getText().toString().trim().equals("") || editTextInterestRate.getText().toString().isEmpty()) {
            editTextInterestRate.setError("Provide Interest Rate");
            layoutdisplayresult.setVisibility(View.GONE);
            layoutWarning.setVisibility(View.GONE);
        }
        else if (editTextLoanTerm.getText().toString().trim().equals("") || editTextLoanTerm.getText().toString().isEmpty()) {
            editTextLoanTerm.setError("Loan Term is Required");
            layoutdisplayresult.setVisibility(View.GONE);
            layoutWarning.setVisibility(View.GONE);
        }

        else
        {
            //for hiding keyboard
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

            //getting value from edittiext
        loanAmount = Double.parseDouble(editTextLoanAmount.getText().toString());
        loanterm = Double.parseDouble(editTextLoanTerm.getText().toString());
        interest = Double.parseDouble(editTextInterestRate.getText().toString());

        paymentCalculator=new paymentCalculator(loanAmount,interest,loanterm);
         monthlyPayment=paymentCalculator.calculateEMI();
         TotalPayment=paymentCalculator.calculateTotalPayment();
         AnnualPayment=paymentCalculator.calculateAnnualPayment();
         totalInterest=paymentCalculator.calculateTotalInterest();

        layoutWarning.setVisibility(View.GONE);
        layoutdisplayresult.setVisibility(View.VISIBLE);
        textViewMonthlyPayment.setText(new DecimalFormat("##.##").format(monthlyPayment));
        textViewTotalPayment.setText(new DecimalFormat("##.##").format(TotalPayment));
        textViewInterest.setText(new DecimalFormat("##.##").format(totalInterest));
        textViewAnnualPayment.setText(new DecimalFormat("##.##").format(AnnualPayment));
        }



    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.buttonCalculate:
                calculatePayment();
                break;
            case  R.id.buttonAmortization:
                Intent i1=new Intent(this,LoanAmortization.class);
                i1.putExtra("Monthlypayment",monthlyPayment);
                i1.putExtra("Rate",interest);
                i1.putExtra("loanAmount",loanAmount);
                i1.putExtra("loanPeriod",loanterm);
                startActivity(i1);
                break;
            case  R.id.buttonReport:
                Intent i2=new Intent(this,LoanReport.class);
                i2.putExtra("PrincipalAmount",loanAmount);
                i2.putExtra("interestRate",interest);
                i2.putExtra("loanPeriod",loanterm);
                i2.putExtra("LoanMonthlyPayment",monthlyPayment);
                i2.putExtra("LoanInterestAmount",totalInterest);
                i2.putExtra("LoanTotalPayment",TotalPayment);
                i2.putExtra("LoanAnnualPayment",AnnualPayment);
                startActivity(i2);
                break;

            case R.id.buttonPaymentReset:
                layoutWarning.setVisibility(View.GONE);
                layoutdisplayresult.setVisibility(View.GONE);
                editTextLoanAmount.setText(null);
                editTextInterestRate.setText(null);
                editTextLoanTerm.setText(null);
                break;
            case R.id.buttonPaymentMail:
                String message="Loan Amount:"+new DecimalFormat("##.##").format(loanAmount)+"\n Interest Rate:"+new DecimalFormat("##.##").format(interest)+"\n Loan Period:"+new DecimalFormat("##.##").format(loanterm)+"\n Monthly Payment:"+new DecimalFormat("##.##").format(monthlyPayment)+"\n Total Interest Amount:"+new DecimalFormat("##.##").format(totalInterest)+"\n Total Payment:"+new DecimalFormat("##.##").format(TotalPayment)+"\n Annual Payment:"+new DecimalFormat("##.##").format(AnnualPayment);
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
