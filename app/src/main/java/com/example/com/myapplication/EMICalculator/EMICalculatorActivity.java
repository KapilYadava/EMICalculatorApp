package com.example.com.myapplication.EMICalculator;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.com.myapplication.R;
import com.example.com.myapplication.Utility.CommonMethods;

import com.example.com.myapplication.databinding.ActivityEmiBinding;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class EMICalculatorActivity extends AppCompatActivity implements OnSeekBarChangeListener{

    private ActivityEmiBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_emi);
        Emi emi= new Emi();
        emi.setLoanAmount(2500000);
        emi.setInterestRate(9.35);
        emi.setLoanTenure(15);
        binding.setEmi(emi);

        binding.btnCalculateEmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Emi emi= binding.getEmi();
                int calculatedEMI= CommonMethods.getEMI(emi.getLoanAmount(), emi.getInterestRate(), emi.getLoanTenure());
                int payableAmount= CommonMethods.getTotalPayableAmount(calculatedEMI, emi.getLoanTenure());
                int interest= CommonMethods.getTotalInterest(emi.getLoanAmount(), payableAmount);
                emi.setLoanEMI(calculatedEMI);
                emi.setTotalPayment(payableAmount);
                emi.setTotalInterest(interest);
                binding.setEmi(emi);
                addPieChart(emi);
                CommonMethods.hideKeyboardFrom(EMICalculatorActivity.this, binding.btnCalculateEmi);
            }
        });

        binding.sbLoanAmount.setOnSeekBarChangeListener(this);
        binding.sbRate.setOnSeekBarChangeListener(this);
        binding.sbTenure.setOnSeekBarChangeListener(this);
        binding.loanAmount.addTextChangedListener(amountTextWatcher);
        binding.interestRate.addTextChangedListener(rateTextWatcher);
        binding.loanTenure.addTextChangedListener(tenureTextWatcher);



    }

    private void addPieChart(Emi emi){
        float loanAmountPercentage= (emi.getLoanAmount()* 100)/ emi.getTotalPayment();
        float totalInterestPercentage= 100 - loanAmountPercentage;

        binding.piechart.setUsePercentValues(true);
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(loanAmountPercentage, 0));
        yvalues.add(new Entry(totalInterestPercentage, 1));

        PieDataSet dataSet = new PieDataSet(yvalues, "");
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Principal Loan Amount");
        xVals.add("Total Interest");

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.DKGRAY);
        binding.piechart.setData(data);
        binding.piechart.setDescription("Break-up of Total Payment");
        binding.piechart.setDrawHoleEnabled(true);
        binding.piechart.setTransparentCircleRadius(30f);
        binding.piechart.setHoleRadius(30f);
        binding.piechart.animateXY(1400, 1400);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (b) {
            CommonMethods.showToast(this, "Progress changed!");
            switch (seekBar.getId()) {
                case R.id.sb_loan_amount:
                    binding.loanAmount.setText(String.valueOf(i * 1000000));
                    binding.loanAmount.setSelection(binding.loanAmount.length());
                    break;
                case R.id.sb_rate:
                    binding.interestRate.setText(String.valueOf(i * 2.0f));
                    binding.interestRate.setSelection(binding.interestRate.length());
                    break;
                case R.id.sb_tenure:
                    binding.loanTenure.setText(String.valueOf(i * 3));
                    binding.loanTenure.setSelection(binding.loanTenure.length());
                    break;
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    TextWatcher amountTextWatcher= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            binding.sbLoanAmount.setProgress(Integer.parseInt(charSequence.toString())/1000000);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher rateTextWatcher= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            binding.sbRate.setProgress((int) Double.parseDouble(charSequence.toString())/2);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher tenureTextWatcher= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            binding.sbTenure.setProgress(Integer.parseInt(charSequence.toString())/3);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


}
