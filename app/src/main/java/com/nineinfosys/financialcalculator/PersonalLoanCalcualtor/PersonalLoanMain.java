package com.nineinfosys.financialcalculator.PersonalLoanCalcualtor;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


import com.nineinfosys.financialcalculator.Amortization.LoanAmortization;
import com.nineinfosys.financialcalculator.R;
import com.nineinfosys.financialcalculator.Report.PersonalLoanReport;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PersonalLoanMain extends AppCompatActivity implements View.OnClickListener{

    EditText editTextLoanAmount,editTextInterestRate,editTextInsurance,editTextyear,editTextMonth,editTextOriginationFee,editTextStartYear;
    Button buttonCalculate,buttonReset, buttonEmail, buttonReport, buttonAmotization;
    TextView textViewMonthlyPayment,textViewTotalloanPayment,textViewInterest,textViewInterestFee,textViewResultOriginationAmount,textViewActuallyReceivedAmount,textViewPayoffdate,textViewTotalInsurance,textViewOriginationAmount,textViewOriginationAmountpercent;
    LinearLayout layoutresult,layoutWarning,layoutActuallyreceived;
    Spinner spinnerMonthlist,spinnerPaidType,spinnerIsA,spinnerMonth;
    personalloan personalloan;
    RadioGroup radioGroupPaid,radioGroupIsatype;
    double loanAmount,interestRate,insurance,originationamount,monthlyPayment,totalcalmonth,TotalPayment,AnnualPayment,totalInsurance,totalInterest,totalFee,totalAll,actuallyReceived,payoffyear;
    int year,month,startyear;
    RadioButton radiButton,radioButtonIsA;
    String strPaid,strIsAtype,startmonth;
    String payoffmonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_loan_main);

        //keyboard hidden first time
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Personal Loan Calculator");

        //declaration of designing tool
        layoutresult=(LinearLayout)this.findViewById(R.id.layoutdisplayresult);
        layoutActuallyreceived=(LinearLayout)this.findViewById(R.id.layoutActuallyreceived);
        layoutWarning=(LinearLayout)this.findViewById(R.id.layoutWarning);

        spinnerMonthlist=(Spinner)findViewById(R.id.spinnerMonthlist);
        spinnerPaidType=(Spinner)findViewById(R.id.spinnerPaidtype);
        spinnerIsA=(Spinner)findViewById(R.id.spinnerAmounttype);
        spinnerMonth=(Spinner)findViewById(R.id.spinnerMonths);
       // radioGroupPaid=(RadioGroup)findViewById(R.id.radioGroupPaid);
       // radioButtonIsA=(RadioButton)findViewById(radioGroupIsatype.getCheckedRadioButtonId());

        editTextLoanAmount=(EditText)findViewById(R.id.editTextLoanAmount);
        editTextInsurance=(EditText)findViewById(R.id.editTextInsurance);
        editTextInterestRate=(EditText)findViewById(R.id.editTextLoanInterestRate);
        editTextyear=(EditText)findViewById(R.id.editTextLoantermyear);
       // editTextMonth=(EditText)findViewById(R.id.editTextLoantermmonth);
        editTextStartYear=(EditText)findViewById(R.id.editTextstartyear);
        editTextOriginationFee=(EditText)findViewById(R.id.editTextOriginationFee);


        textViewMonthlyPayment=(TextView) findViewById(R.id.textViewMonthlyAmount) ;
        textViewTotalloanPayment=(TextView) findViewById(R.id.textViewTotalPrincipalAmount) ;
        textViewInterest=(TextView) findViewById(R.id.textViewInterestAmount) ;
        textViewResultOriginationAmount=(TextView) findViewById(R.id.textViewResultOriginationAmount) ;
        textViewInterestFee=(TextView) findViewById(R.id.textViewInterestFeeValueAmount) ;
        textViewTotalInsurance=(TextView) findViewById(R.id.textViewTotalInsuranceAmount) ;
        textViewActuallyReceivedAmount=(TextView) findViewById(R.id.textViewActuallyReceivedAmount) ;
        textViewPayoffdate=(TextView) findViewById(R.id.textViewPayoffDateAmount) ;
        textViewOriginationAmountpercent=(TextView) findViewById(R.id.textViewOriginationAmountpercent) ;
        textViewOriginationAmount=(TextView) findViewById(R.id.textViewOriginationAmount) ;



        buttonCalculate=(Button)findViewById(R.id.buttonCalculate);
        buttonReset=(Button)findViewById(R.id.buttonReset);
        buttonAmotization=(Button)findViewById(R.id.buttonAmortization);
        buttonReport=(Button)findViewById(R.id.buttonReport);
        buttonEmail=(Button)findViewById(R.id.buttonPersonalLoanEmail);


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

        //adding value to spinner
        List<String> liststartmonths = new ArrayList<String>();
        liststartmonths.add("Jan");
        liststartmonths.add("Feb");
        liststartmonths.add("Mar");
        liststartmonths.add("Apr");
        liststartmonths.add("May");
        liststartmonths.add("Jun");
        liststartmonths.add("Jul");
        liststartmonths.add("Aug");
        liststartmonths.add("Sept");
        liststartmonths.add("Oct");
        liststartmonths.add("Nov");
        liststartmonths.add("Dec");
        // Creating adapter for spinner
        ArrayAdapter<String> Adapterstartmnth = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, liststartmonths);

        // Drop down layout style - list view with radio button
        Adapterstartmnth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerMonthlist.setAdapter(Adapterstartmnth);

        //adding value to sppiner paid type
        List<String> listpaidtype = new ArrayList<String>();
        listpaidtype.add("Deduct from Loan");
        listpaidtype.add("Upfront");
        // Creating adapter for spinner
        ArrayAdapter<String> Adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listpaidtype);

        // Drop down layout style - list view with radio button
        Adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerPaidType.setAdapter(Adapter1);


        //adding value to spinner amounttype
        List<String> listamounttype = new ArrayList<String>();
        listamounttype.add("Percentage");
        listamounttype.add("Amount");
        // Creating adapter for spinner
        ArrayAdapter<String> Adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listamounttype);

        // Drop down layout style - list view with radio button
        Adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerIsA.setAdapter(Adapter2);


     /*   spinnerIsA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strIsAtype = spinnerIsA.getSelectedItem().toString().trim();

                switch(strIsAtype)
                {
                    case "Percentage":
                        textViewOriginationAmountpercent.setVisibility(View.VISIBLE);
                        textViewOriginationAmount.setVisibility(View.GONE);
                        break;
                    case "Amount":
                        textViewOriginationAmount.setVisibility(View.VISIBLE);
                        textViewOriginationAmountpercent.setVisibility(View.GONE);
                        break;
                }
            }
        });*/


        buttonCalculate.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
        buttonAmotization.setOnClickListener(this);
        buttonReport.setOnClickListener(this);
        buttonEmail.setOnClickListener(this);

        spinnerIsA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                strIsAtype = spinnerIsA.getSelectedItem().toString().trim();

                switch(strIsAtype)
                {
                    case "Percentage":
                        textViewOriginationAmountpercent.setVisibility(View.VISIBLE);
                        textViewOriginationAmount.setVisibility(View.GONE);
                        break;
                    case "Amount":
                        textViewOriginationAmount.setVisibility(View.VISIBLE);
                        textViewOriginationAmountpercent.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }
    private void calculatePersonalLoan() {
       // radioGroupIsatype=(RadioGroup)findViewById(R.id.radioGroupIs);
      /*  Toast.makeText(this,"radioGroupPaid"+radiButton.getText().toString().trim(),Toast.LENGTH_LONG).show();
        Toast.makeText(this,"radioGroupPaid"+radioButtonIsA.getText().toString().trim(),Toast.LENGTH_LONG).show();*/
       /* strPaid =radiButton.getText().toString().trim();
        strIsAtype=radioButtonIsA.getText().toString().trim();*/
        if(editTextLoanAmount.getText().toString().trim().equals("") && editTextInterestRate.getText().toString().trim().equals("") && editTextInsurance.getText().toString().trim().equals("0") && editTextyear.getText().toString().trim().equals("0")
                && editTextStartYear.getText().toString().trim().equals("")&& editTextOriginationFee.getText().toString().trim().equals("0"))
        {
            layoutWarning.setVisibility(View.VISIBLE);
            layoutresult.setVisibility(View.GONE);
        }
        else if (editTextLoanAmount.getText().toString().trim().equals("") || editTextLoanAmount.getText().toString().isEmpty()) {
            editTextLoanAmount.setError("Loan Amount is Required");
            layoutresult.setVisibility(View.GONE);
            layoutWarning.setVisibility(View.GONE);
        } else if (editTextInterestRate.getText().toString().trim().equals("") || editTextInterestRate.getText().toString().isEmpty()) {
            editTextInterestRate.setError("Enter Interest Rate");
            layoutresult.setVisibility(View.GONE);
            layoutWarning.setVisibility(View.GONE);
        } else if (editTextyear.getText().toString().trim().equals("") || editTextyear.getText().toString().isEmpty()) {
            editTextyear.setError("Enter Compound Peroids in year");
            layoutresult.setVisibility(View.GONE);
            layoutWarning.setVisibility(View.GONE);
        } /*else if (editTextMonth.getText().toString().trim().equals("") || editTextMonth.getText().toString().isEmpty()) {
            editTextMonth.setError("Enter Loan term in Months");
            layoutresult.setVisibility(View.GONE);
            layoutWarning.setVisibility(View.GONE);
        }*/
        else if (editTextInsurance.getText().toString().trim().equals("") || editTextInsurance.getText().toString().isEmpty()) {
            editTextInsurance.setError("Provide Insurance value per month");
            layoutresult.setVisibility(View.GONE);
            layoutWarning.setVisibility(View.GONE);
        }
        else if (editTextStartYear.getText().toString().trim().equals("") || editTextStartYear.getText().toString().isEmpty()) {
            editTextStartYear.setError("Provide Start Year");
            layoutresult.setVisibility(View.GONE);
            layoutWarning.setVisibility(View.GONE);
        }
        else if (editTextOriginationFee.getText().toString().trim().equals("") || editTextOriginationFee.getText().toString().isEmpty()) {
            editTextOriginationFee.setError("Enter Origination fee per year");
            layoutresult.setVisibility(View.GONE);
            layoutWarning.setVisibility(View.GONE);
        }
       else {
            //for hiding keyboard
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

            layoutresult.setVisibility(View.VISIBLE);
            layoutWarning.setVisibility(View.GONE);
            loanAmount = Double.parseDouble(editTextLoanAmount.getText().toString());
            interestRate = Double.parseDouble(editTextInterestRate.getText().toString());
            insurance = Double.parseDouble(editTextInsurance.getText().toString());
            year = Integer.parseInt(editTextyear.getText().toString());
           // month = Integer.parseInt(editTextMonth.getText().toString());
            startyear = Integer.parseInt(editTextStartYear.getText().toString());
            originationamount = Double.parseDouble(editTextOriginationFee.getText().toString());
            month = Integer.parseInt(spinnerMonth.getSelectedItem().toString().trim());
            startmonth = spinnerMonthlist.getSelectedItem().toString().trim();
            strIsAtype = spinnerIsA.getSelectedItem().toString().trim();
            strPaid = spinnerPaidType.getSelectedItem().toString().trim();

            personalloan = new personalloan(loanAmount, interestRate, year, month, insurance, strIsAtype, originationamount, startmonth, startyear);
             monthlyPayment = personalloan.calculateEMI();
             TotalPayment = personalloan.calculateTotalPayment();
             AnnualPayment = personalloan.calculateAnnualPayment();
             totalInsurance = personalloan.calculateTotalInsurance();
             totalInterest = personalloan.calculateTotalInterest();
             totalFee = personalloan.calcualteFee();
             totalAll = personalloan.calcualteTotalAll();
             actuallyReceived = personalloan.calcualteActuallReceived();
             payoffmonth = personalloan.calculatePayoffMonth();
             totalcalmonth=personalloan.calculateMonth();
             payoffyear = personalloan.calculatePayoffyear();

            switch (strPaid) {
                case "Deduct from Loan":
                    layoutActuallyreceived.setVisibility(View.VISIBLE);
                    textViewMonthlyPayment.setText(new DecimalFormat("##.##").format(monthlyPayment));
                    textViewTotalloanPayment.setText(new DecimalFormat("##.##").format(TotalPayment));
                    textViewInterest.setText(new DecimalFormat("##.##").format(totalInterest));
                    textViewTotalInsurance.setText(new DecimalFormat("##.##").format(totalInsurance));
                    textViewResultOriginationAmount.setText(new DecimalFormat("##.##").format(totalFee));
                    textViewInterestFee.setText(new DecimalFormat("##.##").format(totalAll));
                    textViewActuallyReceivedAmount.setText(new DecimalFormat("##.##").format(actuallyReceived));
                    textViewPayoffdate.setText(payoffmonth + " " + new DecimalFormat("##.##").format(payoffyear));
                    break;

                case "Upfront":
                    layoutActuallyreceived.setVisibility(View.GONE);
                    textViewMonthlyPayment.setText(new DecimalFormat("##.##").format(monthlyPayment));
                    textViewTotalloanPayment.setText(new DecimalFormat("##.##").format(TotalPayment));
                    textViewInterest.setText(new DecimalFormat("##.##").format(totalInterest));
                    textViewTotalInsurance.setText(new DecimalFormat("##.##").format(totalInsurance));
                    textViewResultOriginationAmount.setText(new DecimalFormat("##.##").format(totalFee));
                    textViewInterestFee.setText(new DecimalFormat("##.##").format(totalAll));
                    textViewActuallyReceivedAmount.setText(new DecimalFormat("##.##").format(actuallyReceived));
                    textViewPayoffdate.setText(payoffmonth + " " + new DecimalFormat("##.##").format(payoffyear));
                    break;
            }
        }
    }
    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.buttonCalculate:
                calculatePersonalLoan();
                break;
            case R.id.buttonReset:
                layoutresult.setVisibility(View.GONE);
                layoutWarning.setVisibility(View.GONE);
                editTextLoanAmount.setText(null);
                editTextInterestRate.setText(null);
                editTextyear.setText("0");
               // editTextMonth.setText("0");
                editTextOriginationFee.setText("0");
                editTextStartYear.setText(null);
                editTextInsurance.setText("0");
                break;
            case R.id.buttonAmortization:
                Intent i1 = new Intent(this, LoanAmortization.class);

                i1.putExtra("Monthlypayment", monthlyPayment);
                i1.putExtra("Rate", interestRate);
                i1.putExtra("loanAmount", loanAmount);
                i1.putExtra("loanPeriod", totalcalmonth);
                i1.putExtra("startmonth",startmonth);
                i1.putExtra("startyear",startyear);
                startActivity(i1);
               break;
            case R.id.buttonReport:
                Intent i2 = new Intent(this, PersonalLoanReport.class);
                // Toast.makeText(this, " ToatalInterest" + interestRate, Toast.LENGTH_SHORT).show();
                i2.putExtra("PrincipalAmount", loanAmount);
                i2.putExtra("interestRate", interestRate);
                i2.putExtra("loanPeriod", totalcalmonth);
                i2.putExtra("insurance", insurance);
                i2.putExtra("startmonth",startmonth);
                i2.putExtra("startyear",startyear);
                i2.putExtra("originationamount", originationamount);
                i2.putExtra("LoanMonthlyPayment", monthlyPayment);
                i2.putExtra("LoanInterestAmount", totalInterest);
                i2.putExtra("LoanTotalPayment", TotalPayment);
                i2.putExtra("LoanAnnualPayment", AnnualPayment);
                i2.putExtra("LoantotalInsurance", totalInsurance);
                i2.putExtra("totalFee", totalFee);
                i2.putExtra("totalAll", totalAll);
                i2.putExtra("actuallyReceived", actuallyReceived);
                i2.putExtra("payoffmonth", payoffmonth);
                i2.putExtra("payoffyear", payoffyear);
                startActivity(i2);
                break;

            case R.id.buttonPersonalLoanEmail:
                String message="Loan Amount:"+new DecimalFormat("##.##").format(loanAmount)+"\n Interest Rate:"+new DecimalFormat("##.##").format(interestRate)+"\n Loan Period:"+new DecimalFormat("##.##").format(totalcalmonth)+"\n Insurance:"+new DecimalFormat("##.##").format(insurance)+"\n Start Month:"+new DecimalFormat("##.##").format(startmonth)+"\n Start Year:"+new DecimalFormat("##.##").format(startyear)+
                        "\n Origination Amount:"+new DecimalFormat("##.##").format(originationamount)+ "\n Monthly Payment:"+new DecimalFormat("##.##").format(monthlyPayment)+"\n Total Interest Amount:"+new DecimalFormat("##.##").format(totalInterest)+"\n Total Insurance Amount:"+new DecimalFormat("##.##").format(totalInsurance)+"\n Total Origination Amount:"+new DecimalFormat("##.##").format(totalFee)+
                        "\n Total Interest+Insurance :"+new DecimalFormat("##.##").format(totalAll)+"\n Total Payment:"+new DecimalFormat("##.##").format(TotalPayment)+"Annual Payment:"+new DecimalFormat("##.##").format(AnnualPayment)+ "\n Actually Received Amount:"+new DecimalFormat("##.##").format(actuallyReceived)+"\n Pay off Month :"+payoffmonth +"\n Pay off Year :"+payoffyear;
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
