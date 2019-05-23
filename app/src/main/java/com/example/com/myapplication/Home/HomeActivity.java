package com.example.com.myapplication.Home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.com.myapplication.EMICalculator.EMICalculatorActivity;
import com.example.com.myapplication.PFCalculator.PFCalculatorActivity;
import com.example.com.myapplication.R;
import com.example.com.myapplication.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.btnEmi.setOnClickListener(this);
        binding.btnEpf.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_emi:
                Intent intentEMI= new Intent(this, EMICalculatorActivity.class);
                startActivity(intentEMI);
                break;
            case R.id.btn_epf:
                Intent intentPF= new Intent(this, PFCalculatorActivity.class);
                startActivity(intentPF);
                break;
        }
    }
}
