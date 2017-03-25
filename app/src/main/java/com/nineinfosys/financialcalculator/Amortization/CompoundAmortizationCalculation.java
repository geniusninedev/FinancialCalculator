package com.nineinfosys.financialcalculator.Amortization;

/**
 * Created by Divya on 28-02-2017.
 */
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Divya on 28-02-2017.
 */

public class CompoundAmortizationCalculation {
    private double loanAmount;
    private double intrestRate;
    private double month;
    private double monthlyPayment;
    Context context;


        double r ;
        public CompoundAmortizationCalculation(double loanAmount,
                                               double intrestRate, double month) {
            super();
            this.loanAmount = loanAmount;
            this.intrestRate = intrestRate;
            this.month = month;

            calculateEMI();


        }


        public void calculateEMI(){
            r = intrestRate/1200;
            double r1 = Math.pow(r+1,month);
            monthlyPayment = (double) ((r+(r/(r1-1))) * loanAmount);

        }

        public ArrayList<AmortizationResults> calculateAmortization()
        {

            ArrayList<AmortizationResults> amortArray = new ArrayList<AmortizationResults>();

            //  double currentBalance = loanAmount;
            double InterestpaidforMonth,AmountofPrincipalPaid,RemainingPrincipalAmount,totalInterest=0;

            for(int i=1;i<=month;i++) {
                AmortizationResults results= new AmortizationResults();

                InterestpaidforMonth = loanAmount * r;
                AmountofPrincipalPaid = monthlyPayment + InterestpaidforMonth;
                totalInterest=totalInterest+InterestpaidforMonth;
                RemainingPrincipalAmount = loanAmount +InterestpaidforMonth;
                loanAmount = RemainingPrincipalAmount;

                results.setId(i);
                results.setIntrest(InterestpaidforMonth);
                results.setPrincipal(totalInterest);
                results.setBalance(RemainingPrincipalAmount);
                results.setEmi(monthlyPayment);
                amortArray.add(results);

            }
            //last month
            AmountofPrincipalPaid = loanAmount;
            InterestpaidforMonth = loanAmount * r;
            monthlyPayment=(double) Math.round(AmountofPrincipalPaid+InterestpaidforMonth);
            RemainingPrincipalAmount=0;



            return amortArray;
        }




        public class AmortizationResults{
              private double id;
                private double emi;
                private double  intrest;
                private double principal;
                private double balance;

                public double getId() {
                    return id;
                }

                public void setId(double id) {
                    this.id = id;
                }

                public double getEmi() {
                    return emi;
                }
                public void setEmi(double emi) {
                    this.emi = emi;
                }
                public double getIntrest() {
                    return intrest;
                }
                public void setIntrest(double intrest) {
                    this.intrest = intrest;
                }
                public double getPrincipal() {
                    return principal;
                }
                public void setPrincipal(double principal) {
                    this.principal = principal;
                }
                public double getBalance() {
                    return balance;
                }
                public void setBalance(double balance) {
                    this.balance = balance;
                }




            }
        }

