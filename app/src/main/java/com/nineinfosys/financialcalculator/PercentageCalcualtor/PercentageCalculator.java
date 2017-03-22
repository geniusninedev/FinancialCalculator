package com.nineinfosys.financialcalculator.PercentageCalcualtor;

public class PercentageCalculator {
	double valueOne;
	double valueTwo;
	

	    public double getValueTwo() {
	        return valueTwo;
	    }

	    public void setValueTwo(double valueTwo) {
	        this.valueTwo = valueTwo;
	    }

	    public double getValueOne() {
	        return valueOne;
	    }

	    public void setValueOne(double valueOne) {
	        this.valueOne = valueOne;
	    }
	    
	    
	    public PercentageCalculator(double valueOne, double valueTwo) {
	        this.valueOne = valueOne;
	        this.valueTwo = valueTwo;
	    }

	    public double percentCalculateOne()
	    {
	    	double percentvalue=(valueOne/100)*valueTwo;
	    	return percentvalue;
	    }
	    public double percentCalculateTwo()
	    {
	    	double percentvalue=(valueOne/valueTwo)*100;
	    	return percentvalue;
	    }
	    public double percentCalculateThree()
	    {
	    	double percentvalue=((valueTwo - valueOne) / valueOne) * 100;
	    	return percentvalue;
	    }

}
