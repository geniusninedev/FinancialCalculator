package com.nineinfosys.financialcalculator.Calculators;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nineinfosys.financialcalculator.MarginCalcualtor.CurrencyExchange.CurrencyExchangeMain;
import com.nineinfosys.financialcalculator.MarginCalcualtor.profitMargin.ProfitMarginMain;
import com.nineinfosys.financialcalculator.MarginCalcualtor.stocktrading.StockTradingCalculator;
import com.nineinfosys.financialcalculator.R;

public class MarginCalculators extends AppCompatActivity {
    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_margin_calculators);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Margin Calcualtor");

        mPagerAdapter = new FragmentPagerAdapter(this.getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[] {
                    new CurrencyExchangeMain(),
                    new ProfitMarginMain(),
                    new StockTradingCalculator(),
            };
            private final String[] mFragmentNames = new String[] {
                    getString(R.string.heading_currency),
                    getString(R.string.heading_profit),
                    getString(R.string.heading_stock)
            };
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }
            @Override
            public int getCount() {
                return mFragments.length;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }
        };
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager)findViewById(R.id.container1);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs1);
        tabLayout.setupWithViewPager(mViewPager);
    }
}
