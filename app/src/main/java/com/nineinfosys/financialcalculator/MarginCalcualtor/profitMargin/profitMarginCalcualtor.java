package com.nineinfosys.financialcalculator.MarginCalcualtor.profitMargin;

public class profitMarginCalcualtor {


	/**
	 * Created by Divya on 6-02-2017.
	 */
	private double cost,cost2,cost1;
	private double salesRevenue,salerevenuevalue;
	private double grossMargin;
	double gain,gainpercent,salesGrossprofit;
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getSalesRevenue() {
		return salesRevenue;
	}
	public void setSalesRevenue(double salesRevenue) {
		this.salesRevenue = salesRevenue;
	}
	public double getGrossMargin() {
		return grossMargin;
	}
	public void setGrossMargin(double grossMargin) {
		this.grossMargin = grossMargin;
	}
	
	
	/*public double profitMarginCalcualtor(double cost, double salesRevenue) {
		super();
		this.cost = cost;
		this.salesRevenue = salesRevenue;
	}*/
	/*public profitMarginCalcualtor(double cost, double salesRevenue,double grossMargin) {
		super();
		this.cost = cost;
		this.salesRevenue = salesRevenue;
		this.grossMargin = grossMargin;
	}*/
	
	
	//when cost and sales revenue given
	public double calculateGrossProfit(double cost1,double salesRevenue){
		double grossMargin;
		gain=salesRevenue-cost1;
		
		return gain;
				
	}
	public double calculateGrossMargin(double cost1,double salesRevenue){
		double grossMargin;
		gain=salesRevenue-cost1;
		grossMargin=(gain/salesRevenue)*100;		
		return grossMargin;
				
	}
	
	public double calculateMarkup(double cost1)
	{
		double markup=(gain/cost1)*100;
		return markup;
	}
	
	//when salerevenue and gross margin
	public double calculateCostGainPercent(double saleRevenue,double grossMargin)
	{
		gainpercent=saleRevenue*(grossMargin/100);
		//cost2= saleRevenue-gainpercent;
		return gainpercent;
	}
	public double calculateCost(double saleRevenue)
	{
		cost2= saleRevenue-gainpercent;
		return cost2;
	}
	
	public double calculateCostMarkup()
	{
		double costMarkup=(gainpercent/cost2)*100;
		return costMarkup;
	}
	
	
	//when cost and gross margin given
	public double calculateSalesRevenue(double cost3,double grossMargin)
	{
		double grossAmount=grossMargin/100;
		double sp=1;
		double spvalue=sp-grossAmount;
		salerevenuevalue=cost3/spvalue;
		return salerevenuevalue;
	}
	
	public double calculateSalesRevenueGrossProfit(double cost3)
	{
		 salesGrossprofit=salerevenuevalue-cost3;
		return salesGrossprofit;
	}
	public double calculateSalesRevenueMarkup(double cost3)
	{
		double SalesRevenueMarkup=(salesGrossprofit/cost3)*100;
				
	
	  return SalesRevenueMarkup;
	}
	

	

}
