package com.example.com.myapplication.Utility;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by kapil on 06/12/17.
 */

public class CommonMethods {

     public static int getEMI(int amount, double interest, int tenure){
        float r= (float) ((interest/12)/100);
        int n= tenure * 12;
        int p= amount;
        int e= (int) (p * r * Math.pow((1+r), n)/((Math.pow((1+r), n))-1));
        return e;
    }

    public static int getTotalPayableAmount(int emi, int tenure){
        return emi * tenure * 12;
    }

    public static int getTotalInterest(int amount, int totalPayable){
        return totalPayable - amount;
    }
    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG ).show();
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
