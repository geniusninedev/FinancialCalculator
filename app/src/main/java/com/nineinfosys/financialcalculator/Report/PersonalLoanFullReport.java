package com.nineinfosys.financialcalculator.Report;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;


import com.nineinfosys.financialcalculator.Amortization.AmortizationAdapter;
import com.nineinfosys.financialcalculator.Amortization.AmortizationCalculation;
import com.nineinfosys.financialcalculator.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PersonalLoanFullReport extends AppCompatActivity {

    TextView textViewInputLoanAmount, textViewInputInterest, textViewInputLoanPeriod, textViewResultMonthlyPayment, textViewResultTotalPayment,textViewResultFees,textViewInputOriginationFee,textViewInputDocumentation,textViewInputOtherFee, textViewResultTotalInterest, textViewResultAnnualPayment;
    ImageView imageviewpiechart;
    TextView textViewResulPayoffdate,textViewResulActuallyreceived,textViewResultTotalInsurance,textViewInputInsurance,textViewInputStartDate;
    RecyclerView recyclerViewAmortizationfullreport;
    double PrincipalAmount, ToatalInterest,TotalPayment,interestRate,loanPeriod,LoanMonthlyPayment,LoanInterestAmount,LoanAnnualPayment,totalAll,originationfee,documentationfee,otherfee;
    double insurance,originationamount,LoantotalInsurance,actuallyReceived,payoffyear;
    int startmonth,startyear;
    String payoffmonth;
    List<AmortizationCalculation.AmortizationResults> results = new ArrayList<>();
    AmortizationAdapter amortizationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_loan_full_report);

        //customize toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Loan Full Report");

        //declartion of designing tool
        imageviewpiechart = (ImageView) findViewById(R.id.imageViewPiechart);
        textViewInputLoanAmount = (TextView) findViewById(R.id.textViewLoanAmountfullreport);
        textViewInputInterest = (TextView) findViewById(R.id.textViewinterestratepercentfullreport);
        textViewInputLoanPeriod = (TextView) findViewById(R.id.textViewloantermpercentfullreport);
        textViewInputOriginationFee = (TextView) findViewById(R.id.textViewOriginationAmountfullreport);
        textViewInputInsurance = (TextView) findViewById(R.id.textViewInsuranceAmountfullreport);
        textViewInputStartDate = (TextView) findViewById(R.id.textViewStartmonthyearfullreport);
        textViewResultMonthlyPayment = (TextView) findViewById(R.id.textViewLoanMonthlypaymentfullreport);
        textViewResultTotalPayment = (TextView) findViewById(R.id.textViewtotalpaymentamountfullreport);
        textViewResultTotalInterest = (TextView) findViewById(R.id.textViewtotalinterestpercentresultfullreport);
        textViewResultAnnualPayment = (TextView) findViewById(R.id.textViewtotalAnnualpaymentresultfullreport);
        textViewResultTotalInsurance = (TextView) findViewById(R.id.textViewtotalinsuranceamountfullreport);
        textViewResulActuallyreceived = (TextView) findViewById(R.id.textViewtotalactuallyreceivedamountfullreport);
        textViewResulPayoffdate = (TextView) findViewById(R.id.textViewpayoffdate1fullreport);
        textViewResultFees= (TextView) findViewById(R.id.textViewtotalFeesamountfullreport);
        recyclerViewAmortizationfullreport = (RecyclerView) findViewById(R.id.recyclerViewAmortizationfullreport);
        recyclerViewAmortizationfullreport.setHasFixedSize(true);
        recyclerViewAmortizationfullreport.setLayoutManager(new GridLayoutManager(this, 1));


        //getting the value from activity  LoanReportChart using intent
        PrincipalAmount = getIntent().getExtras().getDouble("PrincipalAmount");
        interestRate = getIntent().getExtras().getDouble("interestRate");
        loanPeriod = getIntent().getExtras().getDouble("loanPeriod");
        insurance = getIntent().getExtras().getDouble("insurance");
        startmonth = getIntent().getExtras().getInt("startmonth");
        startyear = getIntent().getExtras().getInt("startyear");
        originationamount = getIntent().getExtras().getDouble("originationamount");

        LoanMonthlyPayment = getIntent().getExtras().getDouble("LoanMonthlyPayment");
        ToatalInterest = getIntent().getExtras().getDouble("LoanInterestAmount");
        TotalPayment = getIntent().getExtras().getDouble("LoanTotalPayment");
        LoanAnnualPayment = getIntent().getExtras().getDouble("LoanAnnualPayment");
        LoantotalInsurance = getIntent().getExtras().getDouble("LoantotalInsurance");
        originationfee = getIntent().getExtras().getDouble("totalFee");
        totalAll = getIntent().getExtras().getDouble("totalAll");

        actuallyReceived = getIntent().getExtras().getDouble("actuallyReceived");
        payoffmonth =getIntent().getExtras().getString("payoffmonth");
       // payoffmonth = getIntent().getExtras().toString("payoffmonth");
        payoffyear = getIntent().getExtras().getDouble("payoffyear");

        byte[] byteArray = getIntent().getByteArrayExtra("bmp_Image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageviewpiechart.setImageBitmap(bmp);


        //setting value to textview
        textViewInputLoanAmount.setText(new DecimalFormat("##.##").format(PrincipalAmount));
        textViewInputInterest.setText(new DecimalFormat("##.##").format(interestRate) + "%");
        textViewInputLoanPeriod.setText(new DecimalFormat("##.##").format(loanPeriod));
        textViewInputInsurance.setText(new DecimalFormat("##.##").format(insurance));
        textViewInputStartDate.setText(startmonth+"/"+new DecimalFormat("##.##").format(startyear));
        textViewInputOriginationFee.setText(new DecimalFormat("##.##").format(originationamount));


        textViewResultMonthlyPayment.setText(new DecimalFormat("##.##").format(LoanMonthlyPayment));
        textViewResultTotalPayment.setText(new DecimalFormat("##.##").format(TotalPayment));
        textViewResultTotalInsurance.setText(new DecimalFormat("##.##").format(LoantotalInsurance));
        textViewResultFees.setText(new DecimalFormat("##.##").format(totalAll));
        textViewResultTotalInterest.setText(new DecimalFormat("##.##").format(ToatalInterest));
        textViewResultAnnualPayment.setText(new DecimalFormat("##.##").format(LoanAnnualPayment));
        textViewResulActuallyreceived.setText(new DecimalFormat("##.##").format(actuallyReceived));
        textViewResulPayoffdate.setText(payoffmonth+" "+new DecimalFormat("##.##").format(payoffyear));

        //calculation method call for amortization
        loanAmortizationCalcualtion();

    }

    private void loanAmortizationCalcualtion() {

        AmortizationCalculation iA = new AmortizationCalculation(PrincipalAmount, interestRate, loanPeriod);
        results = iA.calculateAmortization();
        amortizationAdapter = new AmortizationAdapter(this,results);
        recyclerViewAmortizationfullreport.setAdapter(amortizationAdapter);
    }
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

        }
    }
}
