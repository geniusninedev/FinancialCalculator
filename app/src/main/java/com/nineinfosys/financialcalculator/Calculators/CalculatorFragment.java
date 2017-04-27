package com.nineinfosys.financialcalculator.Calculators;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


import com.nineinfosys.financialcalculator.BusinessCalcualtor.BusinessCalcualtorMain;
import com.nineinfosys.financialcalculator.CompoundInterestCalcualtor.CompoundCalculatorMain;
import com.nineinfosys.financialcalculator.LoanCalcualtor.LoanCalculatorMain;
import com.nineinfosys.financialcalculator.LoanComaprisonCalcualtor.LoanComparisonCalculatorMain;
import com.nineinfosys.financialcalculator.PaymentCalcualtor.PaymentCalculatorMain;
import com.nineinfosys.financialcalculator.PersonalLoanCalcualtor.PersonalLoanMain;
import com.nineinfosys.financialcalculator.PercentageCalcualtor.PercentageCalcualtorMain;
import com.nineinfosys.financialcalculator.R;

import java.util.ArrayList;


/**
 * Created by Supriya on 19-04-2017.
 */

public class CalculatorFragment extends Fragment {
    private CustomAdapter mAdapter;
    private ArrayList<String> listCalculator;
    private ArrayList<Integer> listCount;
    private GridView gridView;
    FragmentManager mFragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_calculator_fragment, null);
        // prepared arraylist and passed it to the Adapter class

        prepareList();
        mAdapter = new CustomAdapter(getActivity(),listCalculator, listCount);

        // Set custom adapter to gridview
        gridView = (GridView)v.findViewById(R.id.grid);
        gridView.setAdapter(mAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

              if (position == 0){
                 //BMI Calcultor
                    startActivity(new Intent(getActivity(), LoanCalculatorMain.class));
                }
                if (position == 1) {
                    startActivity(new Intent(getActivity(), PaymentCalculatorMain.class));
                }
                if (position == 2) {
                    startActivity(new Intent(getActivity(), CompoundCalculatorMain.class));
                }
                if (position == 3) {
                    startActivity(new Intent(getActivity(), LoanComparisonCalculatorMain.class));
                }
                if (position == 4) {
                   startActivity(new Intent(getActivity(), PersonalLoanMain.class));
                }
                if (position == 5){
                   //Fat Intake
                    startActivity(new Intent(getActivity(), BusinessCalcualtorMain.class));
                }
                if (position == 6) {
                    startActivity(new Intent(getActivity(), PercentageCalcualtorMain.class));
                }
               if (position == 7) {
                    startActivity(new Intent(getActivity(), MarginCalculators.class));
                }


            }
        });
        return v;
    }


    public void prepareList()
    {
        listCalculator = new ArrayList<String>();

        listCalculator.add("Simple Loan Calculator ");
        listCalculator.add("Payment Calculator");
        listCalculator.add("Compound Interest Calculator");
        listCalculator.add("Loan Comparision Calculator");
        listCalculator.add("Personal Loan Calculator");
        listCalculator.add("Business Loan Calculator");
        listCalculator.add("Percentage Calculator");
        listCalculator.add("Margin Calculators");




        listCount = new ArrayList<Integer>();
        listCount.add(R.drawable.ic_loan);
        listCount.add(R.drawable.ic_payment);
        listCount.add(R.drawable.ic_compound);
        listCount.add(R.drawable.ic_comparison1);
        listCount.add(R.drawable.ic_personalloan);
        listCount.add(R.drawable.ic_business);
        listCount.add(R.drawable.ic_percentage);

        listCount.add(R.drawable.ic_margins);

    }

}
