package com.nineinfosys.financialcalculator.PaymentCalcualtor;

public class paymentCalculator {
	private double loanAmount;
    private double intrestRate;
    private int  year;
    private double month;
    double monthlyPayment,annualPayment,totalMonth,totalLoanPayment;
    
    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getIntrestRate() {
        return intrestRate;
    }

    public void setIntrestRate(double intrestRate) {
        this.intrestRate = intrestRate;
    }

    public double getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public paymentCalculator(double loanAmount, double intrestRate, double month) {
        this.loanAmount = loanAmount;
        this.intrestRate = intrestRate;
      
        this.month = month;

    }

    //monthly payment
    public double calculateEMI(){
    //   double YeartoMonth=year*12;
       //totalMonth=YeartoMonth+month;
        double r = intrestRate/1200;
        double r1 = Math.pow(r+1,month);
        monthlyPayment = (double) ((r+(r/(r1-1))) * loanAmount);	        
        return monthlyPayment;
    }
    
   
   
    //total Payment
    public double calculateTotalPayment()
    {
     totalLoanPayment=monthlyPayment*month;
    	
    	return totalLoanPayment;
    }
    
    //total Interest
    public double calculateTotalInterest()
    {
    double totalInterest=( totalLoanPayment-loanAmount);
    return totalInterest;
    }
    
    // Annual Payment
    public double calculateAnnualPayment()
    {
    	annualPayment=monthlyPayment*12;
    	
    	return annualPayment;
    }
    
    //mortgage constant
    public double MortgageConstant()
    {
    	double mortgageConstant=(annualPayment/loanAmount)*100;
    	return mortgageConstant;
    }
   

}
