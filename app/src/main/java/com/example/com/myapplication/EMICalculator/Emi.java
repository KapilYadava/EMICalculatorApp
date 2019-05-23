package com.example.com.myapplication.EMICalculator;

import android.databinding.InverseMethod;

/**
 * Created by kapil on 06/12/17.
 */

public class Emi {
    private int loanAmount, loanTenure, LoanEMI, totalInterest, totalPayment;
    private double interestRate;

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getLoanTenure() {
        return loanTenure;
    }

    public void setLoanTenure(int loanTenure) {
        this.loanTenure = loanTenure;
    }

    public int getLoanEMI() {
        return LoanEMI;
    }

    public void setLoanEMI(int loanEMI) {
        LoanEMI = loanEMI;
    }

    public int getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(int totalInterest) {
        this.totalInterest = totalInterest;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(int totalPayment) {
        this.totalPayment = totalPayment;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }


    @InverseMethod(value = "convertToInt")
    public String convertToString(int i){
        return String.valueOf(i);
    }

    public int convertToInt(String s){
        try {
            return Integer.parseInt(s);
        }catch (Exception e){
            return 0;
        }

    }

    @InverseMethod(value = "convertToDouble")
    public String convertToStringD(double d){
        return String.valueOf(d);
    }

    public double convertToDouble(String s){
        try {
            return Double.parseDouble(s);
        }catch (Exception e) {
            return 0;
        }
    }

    public String formattedAmount(int amount){
        return "Rs. "+ amount;
    }
}
