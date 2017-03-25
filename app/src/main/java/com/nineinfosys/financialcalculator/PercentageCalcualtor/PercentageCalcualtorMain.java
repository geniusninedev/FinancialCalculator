package com.nineinfosys.financialcalculator.PercentageCalcualtor;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineinfosys.financialcalculator.R;



/**
 * Created by Divya
 */

public class PercentageCalcualtorMain extends AppCompatActivity implements TextWatcher,View.OnClickListener{
    EditText edittextpercentageX1, edittextpercentageY1, edittextpercentageX2, edittextpercentageY2, edittextpercentageX3, edittextpercentageY3;
    TextView textViewvalueX1, textViewvalueY1, textViewAns1, textViewvalueX2, textViewvalueY2, textViewAns2, textViewvalueX3, textViewvalueY3, textViewAns3;
    LinearLayout linearLayoutOne, linearLayoutTwo, linearLayoutThree;
    Button buttonResetOne, buttonResetTwo, buttonResetThree;
    PercentageCalculator percentageCalculator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percentage_main);

        //keyboard hidden first time
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //customize toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Percentage Calcualtor");

        //Linearlayout declaration
        linearLayoutOne = (LinearLayout) findViewById(R.id.percentageOnelayout);
        linearLayoutTwo = (LinearLayout) findViewById(R.id.percentageTwolayout);
        linearLayoutThree = (LinearLayout) findViewById(R.id.percentagethirdlayout);

        //declaration of layout tools
        edittextpercentageX1 = (EditText) findViewById(R.id.edittextpercentageX1);
        edittextpercentageY1 = (EditText) findViewById(R.id.edittextpercentageY1);
        edittextpercentageX2 = (EditText) findViewById(R.id.edittextpercentageX2);
        edittextpercentageY2 = (EditText) findViewById(R.id.edittextpercentageY2);
        edittextpercentageX3 = (EditText) findViewById(R.id.edittextpercentageX3);
        edittextpercentageY3 = (EditText) findViewById(R.id.edittextpercentageY3);
        textViewvalueX1 = (TextView) findViewById(R.id.textViewvaluex1);
        textViewvalueY1 = (TextView) findViewById(R.id.textViewvaluey1);
        textViewAns1 = (TextView) findViewById(R.id.textViewAnswer1);
        textViewvalueX2 = (TextView) findViewById(R.id.textViewvaluex2);
        textViewvalueY2 = (TextView) findViewById(R.id.textViewvaluey2);
        textViewAns2 = (TextView) findViewById(R.id.textViewAnswer2);
        textViewvalueX3 = (TextView) findViewById(R.id.textViewvaluex3);
        textViewvalueY3 = (TextView) findViewById(R.id.textViewvaluey3);
        textViewAns3 = (TextView) findViewById(R.id.textViewAnswer3);
        buttonResetOne = (Button) findViewById(R.id.buttonPerecentReset1);
        buttonResetTwo = (Button) findViewById(R.id.buttonPerecentReset2);
        buttonResetThree = (Button) findViewById(R.id.buttonPerecentReset3);

        edittextpercentageX1.addTextChangedListener(this);
        edittextpercentageY1.addTextChangedListener(this);
        edittextpercentageX2.addTextChangedListener(this);
        edittextpercentageY2.addTextChangedListener(this);
        edittextpercentageX3.addTextChangedListener(this);
        edittextpercentageY3.addTextChangedListener(this);

        buttonResetOne.setOnClickListener(this);
        buttonResetTwo.setOnClickListener(this);
        buttonResetThree.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        double percentageX1, percentageY1, percentageX2, percentageY2, percentageX3, percentageY3;
        double resultOne, resultTwo, resultThree;

        //first percentage calculation
        try {
            percentageX1 = Double.parseDouble(edittextpercentageX1.getText().toString());
            percentageY1 = Double.parseDouble(edittextpercentageY1.getText().toString());

            //call percentagecalculator class for calculation
            percentageCalculator=new PercentageCalculator(percentageX1,percentageY1);
            resultOne= percentageCalculator.percentCalculateOne();

            //setting alue to textview
            linearLayoutOne.setVisibility(View.VISIBLE);
            textViewvalueX1.setText(Double.toString((double) percentageX1));
            textViewvalueY1.setText(Double.toString((double) percentageY1));
            textViewAns1.setText(Double.toString((double) resultOne));

        } catch (NumberFormatException e) {
            resultOne = 0;

        }

        //second percentage calculation
        try {
            percentageX2 = Double.parseDouble(edittextpercentageX2.getText().toString());
            percentageY2 = Double.parseDouble(edittextpercentageY2.getText().toString());

            //call percentagecalculator class for calculation
            percentageCalculator=new PercentageCalculator(percentageX2,percentageY2);
            resultTwo= percentageCalculator.percentCalculateTwo();

            //setting alue to textview
            linearLayoutTwo.setVisibility(View.VISIBLE);
            textViewvalueX2.setText(Double.toString((double) percentageX2));
            textViewvalueY2.setText(Double.toString((double) percentageY2));
            textViewAns2.setText(Double.toString((double) resultTwo));

        } catch (NumberFormatException e) {
            resultOne = 0;

        }

        //Third percentage calculation
        try {
            percentageX3 = Double.parseDouble(edittextpercentageX3.getText().toString());
            percentageY3 = Double.parseDouble(edittextpercentageY3.getText().toString());

            //call percentagecalculator class for calculation
            percentageCalculator=new PercentageCalculator(percentageX3,percentageY3);
            resultTwo= percentageCalculator.percentCalculateThree();

            //setting alue to textview
            linearLayoutThree.setVisibility(View.VISIBLE);
            textViewvalueX3.setText(Double.toString((double) percentageX3));
            textViewvalueY3.setText(Double.toString((double) percentageY3));
            textViewAns3.setText(Double.toString((double) resultTwo));

        } catch (NumberFormatException e) {
            resultOne = 0;

        }



    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonPerecentReset1:
                edittextpercentageX1.setText(null);
                edittextpercentageY1.setText(null);
                linearLayoutOne.setVisibility(View.GONE);
                break;
            case R.id.buttonPerecentReset2:
                edittextpercentageX2.setText(null);
                edittextpercentageY2.setText(null);
                linearLayoutTwo.setVisibility(View.GONE);
                break;
            case R.id.buttonPerecentReset3:
                edittextpercentageX3.setText(null);
                edittextpercentageY3.setText(null);
                linearLayoutThree.setVisibility(View.GONE);
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