package com.nineinfosys.financialcalculator.Report;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.nineinfosys.financialcalculator.R;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PersonalLoanReport extends AppCompatActivity implements OnChartValueSelectedListener {
    double PrincipalAmount, ToatalInterest,TotalPayment,interestRate,loanPeriod,LoanMonthlyPayment,LoanInterestAmount,LoanAnnualPayment,totalAll,originationfee;
    private PieChart mChart;
    private double[] mParties;
    private Typeface mTfRegular;
    private Typeface mTfLight;
    Bitmap bmScreen;

    Dialog screenDialog;
    static final int ID_SCREENDIALOG = 1;

    //View screen;
    LinearLayout ll;
    int startmonth,startyear;
    String payoffmonth;
    double payoffyear,insurance,originationamount,LoantotalInsurance,actuallyReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_report);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Loan Report");

        //initialization of designing tools
        ll = (LinearLayout)findViewById(R.id.linear);
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
        payoffyear = getIntent().getExtras().getDouble("payoffyear");


        mParties = new double[]{PrincipalAmount, ToatalInterest,totalAll};

        mChart = (PieChart) findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setCenterTextTypeface(mTfLight);
        mChart.setCenterText(generateCenterSpannableText());

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(55f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);


        // add a selection listener
        mChart.setOnChartValueSelectedListener(this);

        setData(2, 100);


        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        mChart.setEntryLabelColor(Color.BLACK);
        mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(12f);
     //   mChart.setDrawSliceText(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pie, menu);
        getMenuInflater().inflate(R.menu.menu_report, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //switch for full report from menu_report.xml
        switch (item.getItemId()) {
            case R.id.fullreport:
                ll.setDrawingCacheEnabled(false);
                ll.setDrawingCacheEnabled(true);
                bmScreen = ll.getDrawingCache();
                Bitmap bmp=bmScreen;
                showDialog(ID_SCREENDIALOG);
                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                //Compress it before sending it to minimize the size and quality of bitmap.
                bmp.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                byte[] byteArray = bStream.toByteArray();
                Intent i1 = new Intent(this, PersonalLoanFullReport.class);
               i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i1.putExtra("PrincipalAmount", PrincipalAmount);
                i1.putExtra("interestRate", interestRate);
                i1.putExtra("loanPeriod", loanPeriod);
                i1.putExtra("insurance", insurance);
                i1.putExtra("startmonth",startmonth);
                i1.putExtra("startyear",startyear);
                i1.putExtra("originationamount", originationamount);

                i1.putExtra("LoanMonthlyPayment", LoanMonthlyPayment);
                i1.putExtra("LoanInterestAmount", ToatalInterest);
                i1.putExtra("LoanTotalPayment", TotalPayment);
                i1.putExtra("LoanAnnualPayment", LoanAnnualPayment);

                i1.putExtra("LoantotalInsurance", LoantotalInsurance);
                i1.putExtra("originationfee",originationfee);
                i1.putExtra("totalAll", totalAll);
                i1.putExtra("actuallyReceived", actuallyReceived);
                i1.putExtra("payoffmonth", payoffmonth);
                i1.putExtra("payoffyear", payoffyear);
                i1.putExtra("bmp_Image", byteArray);
                startActivity(i1);



        }

        //switch for multiple animation menu for pie.xml
        switch (item.getItemId()) {
            case R.id.actionToggleValues: {
                for (IDataSet<?> set : mChart.getData().getDataSets())
                    set.setDrawValues(!set.isDrawValuesEnabled());
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleHole: {
                if (mChart.isDrawHoleEnabled())
                    mChart.setDrawHoleEnabled(false);
                else
                    mChart.setDrawHoleEnabled(true);
                mChart.invalidate();
                break;
            }
            case R.id.actionDrawCenter: {
                if (mChart.isDrawCenterTextEnabled())
                    mChart.setDrawCenterText(false);
                else
                    mChart.setDrawCenterText(true);
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleXVals: {

                mChart.setDrawEntryLabels(!mChart.isDrawEntryLabelsEnabled());
                mChart.invalidate();
                break;
            }
            case R.id.actionSave: {
                // mChart.saveToGallery("title"+System.currentTimeMillis());
                mChart.saveToPath("title" + System.currentTimeMillis(), "");
                break;
            }
            case R.id.actionTogglePercent:
                mChart.setUsePercentValues(!mChart.isUsePercentValuesEnabled());
                mChart.invalidate();
                break;
            case R.id.animateX: {
                mChart.animateX(1400);
                break;
            }
            case R.id.animateY: {
                mChart.animateY(1400);
                break;
            }
            case R.id.animateXY: {
                mChart.animateXY(1400, 1400);
                break;
            }
            case R.id.actionToggleSpin: {
                mChart.spin(1000, mChart.getRotationAngle(), mChart.getRotationAngle() + 360, Easing.EasingOption
                        .EaseInCubic);
                break;
            }
        }
        return true;
    }


    private void setData(float range, int count) {

        float mult = range;

        List<PieEntry> entries = new ArrayList<>();
      //  Toast.makeText(this, "" + (float) ToatalInterest, Toast.LENGTH_LONG).show();



        entries.add(new PieEntry((float) ToatalInterest, "Interest-" + (new DecimalFormat("##.##").format(ToatalInterest))));
        entries.add(new PieEntry((float) PrincipalAmount, "Principal-" + (new DecimalFormat("##.##").format(PrincipalAmount))));
        entries.add(new PieEntry((float) originationfee, "Fees-" + (new DecimalFormat("##.##").format(originationfee))));
       // entries.add(new PieEntry((float) TotalPayment, "Principal-" + (new DecimalFormat("##.##").format(TotalPayment))));


        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(mTfLight);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    private SpannableString generateCenterSpannableText() {

        String strInterest=new DecimalFormat("##.##").format(ToatalInterest);
        String strFee=new DecimalFormat("##.##").format(originationfee);
        String strprincipal=new DecimalFormat("##.##").format(PrincipalAmount);
        String str=(new DecimalFormat("##.##").format(TotalPayment));
        SpannableString s = new SpannableString("Total Payment\n"+"(Principal)"+strprincipal+"+"+"(Interest)"+strInterest+"="+str);

    //    SpannableString s = new SpannableString("Total Payment\n" + str);
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);

        return s;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

        }
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

}



