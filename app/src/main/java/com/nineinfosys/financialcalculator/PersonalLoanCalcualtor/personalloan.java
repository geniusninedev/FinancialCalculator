package com.nineinfosys.financialcalculator.PersonalLoanCalcualtor;

public class personalloan {

	private double loanAmount;
    private double intrestRate;
    private int  year;
    private int month;
    private String amountType;
    private double insurance,amount;
    private double startyear;
	private String startMonth;
    double monthlyPayment,annualPayment,totalMonth,totalLoanPayment,totalInterest,totalInsurance,totalfee;
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public double getInsurance() {
		return insurance;
	}
	public void setInsurance(double insurance) {
		this.insurance = insurance;
	}
	
	public String getAmountType() {
		return amountType;
	}
	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public personalloan(double loanAmount, double intrestRate, int year,
                        int month, double insurance, String amountType, double amount, String startMonth, double startyear ) {
		super();
		this.loanAmount = loanAmount;
		this.intrestRate = intrestRate;
		this.year = year;
		this.month = month;
		this.insurance = insurance;
		this.amountType=amountType;
		this.amount = amount;
		this.startMonth=startMonth;
		this.startyear = startyear;
	}
    
	  //monthly payment
    public double calculateEMI(){
       double YeartoMonth=year*12;
       totalMonth=YeartoMonth+month;
        double r = intrestRate/1200;
        double r1 = Math.pow(r+1,totalMonth);
        monthlyPayment = (double) ((r+(r/(r1-1))) * loanAmount);
		double EMIInsurance=monthlyPayment+insurance;
        return EMIInsurance;
    }


    public double calculateMonth()
	{
		double YeartoMonth=year*12;
		double calmonth=YeartoMonth+month;
		return calmonth;
	}
    
   
   
    //total Payment
    public double calculateTotalPayment()
    {
     totalLoanPayment=monthlyPayment*totalMonth;
    	
    	return totalLoanPayment;
    }
    
    //total Interest
    public double calculateTotalInterest()
    {
     totalInterest=( totalLoanPayment-loanAmount);
    return totalInterest;
    }
    
    // Annual Payment
    public double calculateAnnualPayment()
    {
    	annualPayment=monthlyPayment*12;
    	
    	return annualPayment;
    }
    
    //total insurance
    public double calculateTotalInsurance()
    {
    	 totalInsurance=totalMonth*insurance;
    	return totalInsurance;
    	
    }
    //total origination fee
    @SuppressWarnings("unused")
	public double calcualteFee()
    {
  
    	switch(amountType)
    	{
    	case "Percentage" :
    		totalfee=((loanAmount) * (amount/100));
    		break;
    	case "Amount" :
    		totalfee=amount;
    		break;
    		
    	}
    	/*if(amountType.equals("Percentage"))
    	{
    		totalfee=((loanAmount) * (amount/100));
    	}
    	else if(amountType.equals("Amount"))
    	{
    		totalfee=amount;
    	}
    	else
    	{
    		totalfee=amount;
    	}
    		*/
    	return totalfee;
    }
    
    //total interest+total insurance+total fee
    public double calcualteTotalAll()
    {
    	double totalAll=totalInterest+totalInsurance+totalfee;
    	return totalAll;
    }
    
    
    //total actually received amount
    public double calcualteActuallReceived()
    {
    	double actuallyReceived=loanAmount-totalfee;
    	return actuallyReceived;
    }
    
    public double calculatePayoffyear()
    {
    	double payoffyear=startyear+year;
    	return payoffyear;
    }

    //calcualte payoff month
	public String calculatePayoffMonth()
	{
		String monthtype= null;;
		switch(startMonth)
		{
			case "Jan" :
				switch(month)
				{
					case 0 :
						monthtype="Jan";
						break;
					case 1 :
						monthtype="Feb";
						break;
					case 2 :
						monthtype="Mar";
						break;
					case 3 :
						monthtype="Apr";
						break;
					case 4 :
						monthtype="May";
						break;
					case 5 :
						monthtype="Jun";
						break;
					case 6 :
						monthtype="Jul";
						break;
					case 7 :
						monthtype="Aug";
						break;
					case 8 :
						monthtype="Sept";
						break;
					case 9 :
						monthtype="Oct";
						break;
					case 10 :
						monthtype="Nov";
						break;
					case 11 :
						monthtype="Dec";
						break;
					case 12 :
						monthtype="Jan";
						break;
				}
				break;

			case "Feb" :
				switch(month)
				{
					case 0 :
						monthtype="Feb";
						break;
					case 1 :
						monthtype="Mar";
						break;
					case 2 :
						monthtype="Apr";
						break;
					case 3 :
						monthtype="May";
						break;
					case 4 :
						monthtype="Jun";
						break;
					case 5 :
						monthtype="Jul";
						break;
					case 6 :
						monthtype="Aug";
						break;
					case 7 :
						monthtype="Sept";
						break;
					case 8 :
						monthtype="Oct";
						break;
					case 9 :
						monthtype="Nov";
						break;
					case 10 :
						monthtype="Dec";
						break;
					case 11 :
						monthtype="Jan";
						break;
					case 12 :
						monthtype="Feb";
						break;
				}
				break;

			case "Mar" :
				switch(month)
				{
					case 0 :
						monthtype="Mar";
						break;
					case 1 :
						monthtype="Apr";
						break;
					case 2 :
						monthtype="May";
						break;
					case 3 :
						monthtype="Jun";
						break;
					case 4 :
						monthtype="Jul";
						break;
					case 5 :
						monthtype="Aug";
						break;
					case 6 :
						monthtype="Sept";
						break;
					case 7 :
						monthtype="Oct";
						break;
					case 8 :
						monthtype="Nov";
						break;
					case 9 :
						monthtype="Dec";
						break;
					case 10 :
						monthtype="Jan";
						break;
					case 11 :
						monthtype="Feb";
						break;
					case 12 :
						monthtype="Mar";
						break;

				}
				break;

			case "Apr" :
				switch(month)
				{

					case 0 :
						monthtype="Apr";
						break;
					case 1 :
						monthtype="May";
						break;
					case 2 :
						monthtype="Jun";
						break;
					case 3 :
						monthtype="Jul";
						break;
					case 4 :
						monthtype="Aug";
						break;
					case 5 :
						monthtype="Sept";
						break;
					case 6 :
						monthtype="Oct";
						break;
					case 7 :
						monthtype="Nov";
						break;
					case 8 :
						monthtype="Dec";
						break;
					case 9 :
						monthtype="Jan";
						break;
					case 10 :
						monthtype="Feb";
						break;
					case 11 :
						monthtype="Mar";
						break;
					case 12 :
						monthtype="Apr";
						break;

				}
				break;

			case "May" :
				switch(month)
				{
					case 0 :
						monthtype="May";
						break;
					case 1 :
						monthtype="Jun";
						break;
					case 2 :
						monthtype="Jul";
						break;
					case 3 :
						monthtype="Aug";
						break;
					case 4 :
						monthtype="Sept";
						break;
					case 5 :
						monthtype="Oct";
						break;
					case 6 :
						monthtype="Nov";
						break;
					case 7 :
						monthtype="Dec";
						break;
					case 8 :
						monthtype="Jan";
						break;
					case 9 :
						monthtype="Feb";
						break;
					case 10 :
						monthtype="Mar";
						break;
					case 11 :
						monthtype="Apr";
						break;
					case 12 :
						monthtype="May";
						break;

				}
				break;

			case "Jun" :
				switch(month)
				{
					case 0 :
						monthtype="Jun";
						break;
					case 1 :
						monthtype="Jul";
						break;
					case 2 :
						monthtype="Aug";
						break;
					case 3 :
						monthtype="Sept";
						break;
					case 4 :
						monthtype="Oct";
						break;
					case 5 :
						monthtype="Nov";
						break;
					case 6 :
						monthtype="Dec";
						break;
					case 7 :
						monthtype="Jan";
						break;
					case 8 :
						monthtype="Feb";
						break;
					case 9 :
						monthtype="Mar";
						break;
					case 10 :
						monthtype="Apr";
						break;
					case 11 :
						monthtype="May";
						break;
					case 12 :
						monthtype="Jun";
						break;

				}
				break;

			case "Jul" :
				switch(month)
				{
					case 0 :
						monthtype="Jul";
						break;
					case 1 :
						monthtype="Aug";
						break;
					case 2 :
						monthtype="Sept";
						break;
					case 3 :
						monthtype="Oct";
						break;
					case 4 :
						monthtype="Nov";
						break;
					case 5 :
						monthtype="Dec";
						break;
					case 6 :
						monthtype="Jan";
						break;
					case 7 :
						monthtype="Feb";
						break;
					case 8 :
						monthtype="Mar";
						break;
					case 9 :
						monthtype="Apr";
						break;
					case 10 :
						monthtype="May";
						break;
					case 11 :
						monthtype="Jun";
						break;

					case 12 :
						monthtype="Jul";
						break;

				}
				break;

			case "Aug" :
				switch(month)
				{
					case 0 :
						monthtype="Aug";
						break;
					case 1 :
						monthtype="Sept";
						break;
					case 2 :
						monthtype="Oct";
						break;
					case 3 :
						monthtype="Nov";
						break;
					case 4 :
						monthtype="Dec";
						break;
					case 5 :
						monthtype="Jan";
						break;
					case 6 :
						monthtype="Feb";
						break;
					case 7 :
						monthtype="Mar";
						break;
					case 8 :
						monthtype="Apr";
						break;
					case 9 :
						monthtype="May";
						break;
					case 10 :
						monthtype="Jun";
						break;

					case 11 :
						monthtype="Jul";
						break;
					case 12 :
						monthtype="Aug";
						break;

				}
				break;

			case "Sept" :
				switch(month)
				{
					case 0 :
						monthtype="Sept";
						break;
					case 1 :
						monthtype="Oct";
						break;
					case 2 :
						monthtype="Nov";
						break;
					case 3 :
						monthtype="Dec";
						break;
					case 4 :
						monthtype="Jan";
						break;
					case 5 :
						monthtype="Feb";
						break;
					case 6 :
						monthtype="Mar";
						break;
					case 7 :
						monthtype="Apr";
						break;
					case 8 :
						monthtype="May";
						break;
					case 9 :
						monthtype="Jun";
						break;

					case 10 :
						monthtype="Jul";
						break;
					case 11 :
						monthtype="Aug";
						break;
					case 12 :
						monthtype="Sept";
						break;
				}
				break;

			case "Oct" :
				switch(month)
				{
					case 0 :
						monthtype="Oct";
						break;
					case 1 :
						monthtype="Nov";
						break;
					case 2 :
						monthtype="Dec";
						break;
					case 3 :
						monthtype="Jan";
						break;
					case 4 :
						monthtype="Feb";
						break;
					case 5 :
						monthtype="Mar";
						break;
					case 6 :
						monthtype="Apr";
						break;
					case 7 :
						monthtype="May";
						break;
					case 8 :
						monthtype="Jun";
						break;

					case 9 :
						monthtype="Jul";
						break;
					case 10 :
						monthtype="Aug";
						break;
					case 11 :
						monthtype="Sept";
						break;
					case 12 :
						monthtype="Oct";
						break;
				}
				break;

			case "Nov" :
				switch(month)
				{

					case 0 :
						monthtype="Nov";
						break;
					case 1 :
						monthtype="Dec";
						break;
					case 2 :
						monthtype="Jan";
						break;
					case 3 :
						monthtype="Feb";
						break;
					case 4 :
						monthtype="Mar";
						break;
					case 5 :
						monthtype="Apr";
						break;
					case 6 :
						monthtype="May";
						break;
					case 7 :
						monthtype="Jun";
						break;

					case 8 :
						monthtype="Jul";
						break;
					case 9 :
						monthtype="Aug";
						break;
					case 10 :
						monthtype="Sept";
						break;
					case 11 :
						monthtype="Oct";
						break;
					case 12 :
						monthtype="Nov";
						break;
				}
				break;


			case "Dec" :
				switch(month)
				{

					case 0 :
						monthtype="Dec";
						break;
					case 1 :
						monthtype="Jan";
						break;
					case 2 :
						monthtype="Feb";
						break;
					case 3 :
						monthtype="Mar";
						break;
					case 4 :
						monthtype="Apr";
						break;
					case 5 :
						monthtype="May";
						break;
					case 6 :
						monthtype="Jun";
						break;

					case 7 :
						monthtype="Jul";
						break;
					case 8 :
						monthtype="Aug";
						break;
					case 9 :
						monthtype="Sept";
						break;

					case 10 :
						monthtype="Oct";
						break;
					case 11 :
						monthtype="Nov";
						break;
					case 12 :
						monthtype="Dec";
						break;
				}

				break;



		}

		return monthtype;
	}
}