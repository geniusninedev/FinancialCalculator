package com.nineinfosys.financialcalculator.MarginCalcualtor.CurrencyExchange;



/**
 * Created by Divya on 4-02-2017.
 */
public class currencyExchangeCal {
	private double exchangeRate;
	private double marginRatio;
	private double units;
	public double getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public double getMarginRatio() {
		return marginRatio;
	}
	public void setMarginRatio(double marginRatio) {
		this.marginRatio = marginRatio;
	}
	public double getUnits() {
		return units;
	}
	public void setUnits(double units) {
		this.units = units;
	}
	public currencyExchangeCal(double exchangeRate, double marginRatio,
                               double units) {
		super();
		this.exchangeRate = exchangeRate;
		this.marginRatio = marginRatio;
		this.units = units;
	}
	
	public double calculateAmountRequired()
	{
		double amountRequired=((exchangeRate*units)/marginRatio);
		return amountRequired;
	}

	public static void main(String args[])
	{
		currencyExchangeCal stocktrading=new currencyExchangeCal(1.3,20,30);
		double amount=stocktrading.calculateAmountRequired();
		System.out.print("\n amount="+amount);
	}
}
