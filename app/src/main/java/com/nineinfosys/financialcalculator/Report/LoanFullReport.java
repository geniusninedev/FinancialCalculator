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

public class LoanFullReport extends AppCompatActivity {

    TextView textViewInputLoanAmount, textViewInputInterest, textViewInputLoanPeriod, textViewResultMonthlyPayment, textViewResultTotalPayment, textViewResultTotalInterest, textViewResultAnnualPayment;
    ImageView imageviewpiechart;
    RecyclerView recyclerViewAmortizationfullreport;
    double PrincipalAmount, ToatalInterest,TotalPayment,interestRate,loanPeriod,LoanMonthlyPayment,LoanInterestAmount,LoanAnnualPayment;
    List<AmortizationCalculation.AmortizationResults> results = new ArrayList<>();
    AmortizationAdapter amortizationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_full_report);

        //customize toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Loan Full Report");

        //declartion of designing tool
        imageviewpiechart = (ImageView) findViewById(R.id.imageViewPiechart);
        textViewInputLoanAmount = (TextView) findViewById(R.id.textViewLoanAmountfullreport);
        textViewInputInterest = (TextView) findViewById(R.id.textViewinterestratepercentfullreport);
        textViewInputLoanPeriod = (TextView) findViewById(R.id.textViewloantermpercentfullreport);
        textViewResultMonthlyPayment = (TextView) findViewById(R.id.textViewLoanMonthlypaymentfullreport);
        textViewResultTotalPayment = (TextView) findViewById(R.id.textViewtotalpaymentamountfullreport);
        textViewResultTotalInterest = (TextView) findViewById(R.id.textViewtotalinterestpercentresultfullreport);
        textViewResultAnnualPayment = (TextView) findViewById(R.id.textViewtotalAnnualpaymentresultfullreport);

        recyclerViewAmortizationfullreport = (RecyclerView) findViewById(R.id.recyclerViewAmortizationfullreport);
        recyclerViewAmortizationfullreport.setHasFixedSize(true);
        recyclerViewAmortizationfullreport.setLayoutManager(new GridLayoutManager(this, 1));


        //getting the value from activity  LoanReportChart using intent
        PrincipalAmount = getIntent().getExtras().getDouble("PrincipalAmount");
        interestRate = getIntent().getExtras().getDouble("interestRate");
        loanPeriod = getIntent().getExtras().getDouble("loanPeriod");
        LoanMonthlyPayment = getIntent().getExtras().getDouble("LoanMonthlyPayment");
        ToatalInterest = getIntent().getExtras().getDouble("LoanInterestAmount");
        TotalPayment = getIntent().getExtras().getDouble("LoanTotalPayment");
        LoanAnnualPayment = getIntent().getExtras().getDouble("LoanAnnualPayment");

        byte[] byteArray = getIntent().getByteArrayExtra("bmp_Image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageviewpiechart.setImageBitmap(bmp);


        //setting value to textview
        textViewInputLoanAmount.setText(new DecimalFormat("##.##").format(PrincipalAmount));
        textViewInputInterest.setText(new DecimalFormat("##.##").format(interestRate) + "%");
        textViewInputLoanPeriod.setText(new DecimalFormat("##.##").format(loanPeriod));
        textViewResultMonthlyPayment.setText(new DecimalFormat("##.##").format(LoanMonthlyPayment));
        textViewResultTotalPayment.setText(new DecimalFormat("##.##").format(TotalPayment));
        textViewResultTotalInterest.setText(new DecimalFormat("##.##").format(ToatalInterest));
        textViewResultAnnualPayment.setText(new DecimalFormat("##.##").format(LoanAnnualPayment));

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
            //Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            //Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
}
