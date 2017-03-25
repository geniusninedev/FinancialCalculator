package com.nineinfosys.financialcalculator.CompoundInterestCalcualtor;

public class CompoundCalculation {

	/**
	 * Created by Divya on 28-02-2017.
	 */
	
	
	double principalAmount;
	double interestRate;
	double noofterms;
	double months,Amount,r;
	
	public double getPrincipalAmount() {
		return principalAmount;
	}
	public void setPrincipalAmount(double principalAmount) {
		this.principalAmount = principalAmount;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public double getNoofterms() {
		return noofterms;
	}
	public void setNoofterms(double noofterms) {
		this.noofterms = noofterms;
	}
	public double getMonths() {
		return months;
	}
	public void setMonths(double months) {
		this.months = months;
	}
	
	public CompoundCalculation(double principalAmount, double interestRate, double noofterms, double months) {
		super();
		this.principalAmount = principalAmount;
		this.interestRate = interestRate;
		this.noofterms = noofterms;
		this.months = months;
	}
	
	public double CalculateCompoundInterest()
	{
		 r=interestRate/100;
		double m1=(months/12);
		Amount=(principalAmount*(Math.pow((1+(r/noofterms)),(noofterms*m1))));
		return Amount;
		
	}
	
	public double CalculateInterestAmount()
	{
		double interestAmount=Amount-principalAmount;
		return interestAmount;
	}
	public double CalculateAPY()
	{
		double interestAmount=((Math.pow((1+(r/noofterms)),noofterms))-1)*100;
		return interestAmount;
	}
	

}
