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


import com.nineinfosys.financialcalculator.Amortization.CompoundAmortizationAdapter;
import com.nineinfosys.financialcalculator.Amortization.CompoundAmortizationCalculation;
import com.nineinfosys.financialcalculator.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CompoundFullReport extends AppCompatActivity {

    TextView textViewInputLoanAmount, textViewInputInterest, textViewResultAPYAmount,textViewInputLoanPeriod, textViewInputCompoundsperyear, textViewResultInterestAmount, textViewResultTotalInterest, textViewResultCompoundAmount,textViewResultPrincipalAmount;
    ImageView imageviewpiechart;
    RecyclerView recyclerViewAmortizationfullreport;
    double PrincipalAmount, Compoundsperyear,InterestAmount,interestRate,loanPeriod,compoundAmount,LoanInterestAmount,LoanAnnualPayment,APY;
    List<CompoundAmortizationCalculation.AmortizationResults> results = new ArrayList<>();
    CompoundAmortizationAdapter amortizationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound_full_report);

        //customize toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
    //    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     //   getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Loan Full Report");

        //declartion of designing tool
        imageviewpiechart = (ImageView) findViewById(R.id.imageViewPiechart);
        textViewInputLoanAmount = (TextView) findViewById(R.id.textViewLoanAmountfullreport);
        textViewInputInterest = (TextView) findViewById(R.id.textViewinterestratepercentfullreport);
        textViewInputLoanPeriod = (TextView) findViewById(R.id.textViewloantermpercentfullreport);
        textViewInputCompoundsperyear = (TextView) findViewById(R.id.textViewloanterminputcompounds);
        textViewResultPrincipalAmount = (TextView) findViewById(R.id.textViewtotalPrincipalamountfullreport);
        textViewResultInterestAmount = (TextView) findViewById(R.id.textViewtotalpaymentamountfullreport);
        textViewResultCompoundAmount = (TextView) findViewById(R.id.textViewMaturityAmountresultfullreport);
        textViewResultAPYAmount = (TextView) findViewById(R.id.textViewResultAPYAmount);

        recyclerViewAmortizationfullreport = (RecyclerView) findViewById(R.id.recyclerViewAmortizationfullreport);
        recyclerViewAmortizationfullreport.setHasFixedSize(true);
        recyclerViewAmortizationfullreport.setLayoutManager(new GridLayoutManager(this, 1));

        //getting the value from activity  LoanReportChart using intent
        PrincipalAmount = getIntent().getExtras().getDouble("PrincipalAmount");
        interestRate = getIntent().getExtras().getDouble("interestRate");
        loanPeriod = getIntent().getExtras().getDouble("loanPeriod");
        Compoundsperyear = getIntent().getExtras().getDouble("Compoundsperyear");
        InterestAmount = getIntent().getExtras().getDouble("InterestAmount");
        compoundAmount = getIntent().getExtras().getDouble("compoundAmount");
        APY = getIntent().getExtras().getDouble("APY");

        byte[] byteArray = getIntent().getByteArrayExtra("bmp_Image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageviewpiechart.setImageBitmap(bmp);


        //setting value to textview
        textViewInputLoanAmount.setText(new DecimalFormat("##.##").format(PrincipalAmount));
        textViewInputInterest.setText(new DecimalFormat("##.##").format(interestRate) + "%");
        textViewInputLoanPeriod.setText(new DecimalFormat("##.##").format(loanPeriod));
        textViewInputCompoundsperyear.setText(new DecimalFormat("##.##").format(Compoundsperyear));
        textViewResultPrincipalAmount.setText(new DecimalFormat("##.##").format(PrincipalAmount));
        textViewResultInterestAmount.setText(new DecimalFormat("##.##").format(InterestAmount));
        textViewResultCompoundAmount.setText(new DecimalFormat("##.##").format(compoundAmount));
        textViewResultAPYAmount.setText(new DecimalFormat("##.##").format(APY));
      //  textViewResultAnnualPayment.setText(new DecimalFormat("##.##").format(LoanAnnualPayment));

        //calculation method call for amortization
        loanAmortizationCalcualtion();

    }

    private void loanAmortizationCalcualtion() {

        CompoundAmortizationCalculation iA = new CompoundAmortizationCalculation(PrincipalAmount, interestRate, loanPeriod);
        results = iA.calculateAmortization();
        amortizationAdapter = new CompoundAmortizationAdapter(this,results);
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
