package com.example.com.myapplication.PFCalculator;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.StrictMode;

/**
 * Created by kapil on 11/12/17.
 */

public class PF implements Parcelable{

    private int presentAge;
    private int retirementAge;
    private int basicSalary;
    private int accumulatedAmount;
    private float yourContribution, percentageIncrement, employerContribution, pfROI;

    public int getAccumulatedAmount() {
        return accumulatedAmount;
    }

    public void setAccumulatedAmount(int accumulatedAmount) {
        this.accumulatedAmount = accumulatedAmount;
    }

    public int getPresentAge() {
        return presentAge;
    }

    public void setPresentAge(int presentAge) {
        this.presentAge = presentAge;
    }

    public int getRetirementAge() {
        return retirementAge;
    }

    public void setRetirementAge(int retirementAge) {
        this.retirementAge = retirementAge;
    }

    public int getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }

    public float getYourContribution() {
        return yourContribution;
    }

    public void setYourContribution(float yourContribution) {
        this.yourContribution = yourContribution;
    }

    public float getPercentageIncrement() {
        return percentageIncrement;
    }

    public void setPercentageIncrement(float percentageIncrement) {
        this.percentageIncrement = percentageIncrement;
    }

    public float getEmployerContribution() {
        return employerContribution;
    }

    public void setEmployerContribution(float employerContribution) {
        this.employerContribution = employerContribution;
    }

    public float getPfROI() {
        return pfROI;
    }

    public void setPfROI(float pfROI) {
        this.pfROI = pfROI;
    }

    public String yearsOfContribution(){
        return (retirementAge - presentAge) +" years";
    }
    public String maturityAfter(){
        return  (60-retirementAge) +" years";
    }

    public String getAccumulatedAmt(int accumulatedAmount){
        return "You will have accumulated ₹"+ accumulatedAmount+ " by the time you retire";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(this.presentAge);
        parcel.writeInt(this.retirementAge);
        parcel.writeInt(this.basicSalary);
        parcel.writeInt(this.accumulatedAmount);
        parcel.writeFloat(this.yourContribution);
        parcel.writeFloat(this.percentageIncrement);
        parcel.writeFloat(this.employerContribution);
        parcel.writeFloat(this.pfROI);

    }

    public static final Parcelable.Creator<PF> CREATOR
            = new Parcelable.Creator<PF>() {
        public PF createFromParcel(Parcel in) {
            return new PF(in);
        }

        public PF[] newArray(int size) {
            return new PF[size];
        }
    };

    public PF (Parcel in){
        this.presentAge= in.readInt();
        this.retirementAge= in.readInt();
        this.basicSalary= in.readInt();
        this.accumulatedAmount= in.readInt();
        this.yourContribution= in.readFloat();

    }

    public PF(){

    }


}
