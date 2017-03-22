package com.nineinfosys.financialcalculator.LoanCalcualtor;


//loan calcultion using loanAmount,loanInterestRate and month
public class loancalculation {
	    
		private double loanAmount;
	    private double intrestRate;
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



	    public loancalculation(double loanAmount, double intrestRate,double month) {
	        this.loanAmount = loanAmount;
	        this.intrestRate = intrestRate;
	       // this.year=year;
	        this.month = month;

	    }

	    //monthly payment
	    public double calculateEMI(){
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


