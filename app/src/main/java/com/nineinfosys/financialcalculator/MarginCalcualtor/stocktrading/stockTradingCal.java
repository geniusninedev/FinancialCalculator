package com.nineinfosys.financialcalculator.MarginCalcualtor.stocktrading;

public class stockTradingCal {
	
	private double stockPrice;
	private double numberofShares;
	private double marginRate;
	public double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}
	public double getNumberofShares() {
		return numberofShares;
	}
	public void setNumberofShares(double numberofShares) {
		this.numberofShares = numberofShares;
	}
	public double getMarginRate() {
		return marginRate;
	}
	public void setMarginRate(double marginRate) {
		this.marginRate = marginRate;
	}
	public stockTradingCal(double stockPrice, double numberofShares,
                           double marginRate) {
		super();
		this.stockPrice = stockPrice;
		this.numberofShares = numberofShares;
		this.marginRate = marginRate;
	}
	
	public double calcualteAmountRequired()
	{
		double amountRequired=((stockPrice*numberofShares)*(marginRate/100));
		return amountRequired;
	}

	public static void main(String args[])
	{
		stockTradingCal stocktrading=new stockTradingCal(18.3,100,30);
		double amount=stocktrading.calcualteAmountRequired();
		System.out.print("\n amount="+amount);
	}
}
