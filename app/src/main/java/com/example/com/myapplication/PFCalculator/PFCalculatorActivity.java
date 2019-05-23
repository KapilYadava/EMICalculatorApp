package com.example.com.myapplication.PFCalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.com.myapplication.R;

public class PFCalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main, new PFPagerFragment(), "PFPagerFragment")
                .commit();
    }

}