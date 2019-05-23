package com.example.com.myapplication.PFCalculator;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.com.myapplication.R;
import com.example.com.myapplication.Utility.CommonMethods;
import com.example.com.myapplication.databinding.FragmentAgeBinding;
import com.example.com.myapplication.databinding.FragmentEmployeeContributionBinding;
import com.example.com.myapplication.databinding.FragmentEmployerContributionBinding;
import com.example.com.myapplication.databinding.FragmentPfRoiBinding;
import com.example.com.myapplication.databinding.FragmentSalaryBinding;
import com.example.com.myapplication.databinding.FragmentSalaryIncreaseExpectedBinding;
import com.example.com.myapplication.databinding.PfResultBinding;


/**
 * Created by yaucheukming on 14/9/2016.
 */

public class PFFragment extends Fragment implements View.OnClickListener {

    private int mKey;
    private PF pf = new PF();


    float yearEndSum= 0;

    public static PFFragment newInstance(int key) {
        PFFragment demoFragment = new PFFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("PFFragment", key);
        demoFragment.setArguments(bundle);
        return demoFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        pf.setPresentAge(28);
        pf.setRetirementAge(30);
        pf.setBasicSalary(32000);
        pf.setPercentageIncrement(8);
        pf.setYourContribution(12);
        pf.setEmployerContribution(12);
        pf.setPfROI(9f);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }

        mKey = bundle.getInt("PFFragment");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        switch (mKey) {
            case 0:
                FragmentAgeBinding mFragmentAgeBinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_age, container, false));
                mFragmentAgeBinding.setPf(pf);
                return mFragmentAgeBinding.getRoot();
            //break;

            case 1:
                FragmentSalaryBinding mFragmentSalaryBinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_salary, container, false));
                mFragmentSalaryBinding.setPf(pf);
                return mFragmentSalaryBinding.getRoot();
            //break;

            case 2:
                FragmentSalaryIncreaseExpectedBinding mFragmentSalaryIncreaseExpectedBinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_salary_increase_expected, container, false));
                mFragmentSalaryIncreaseExpectedBinding.setPf(pf);
                return mFragmentSalaryIncreaseExpectedBinding.getRoot();
            //break;

            case 3:
                FragmentEmployeeContributionBinding mFragmentEmployeeContributionBinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_employee_contribution, container, false));
                mFragmentEmployeeContributionBinding.setPf(pf);
                return mFragmentEmployeeContributionBinding.getRoot();
            //break;

            case 4:
                FragmentEmployerContributionBinding mFragmentEmployerContributionBinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_employer_contribution, container, false));
                mFragmentEmployerContributionBinding.setPf(pf);
                return mFragmentEmployerContributionBinding.getRoot();
            //break;

            case 5:
                FragmentPfRoiBinding mFragmentPfRoiBinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_pf_roi, container, false));
                mFragmentPfRoiBinding.setPf(pf);
                mFragmentPfRoiBinding.btnCalculate.setOnClickListener(this);
                return mFragmentPfRoiBinding.getRoot();

            default:
                FragmentAgeBinding mFragmentAgeBinding1 = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_age, container, false));
                mFragmentAgeBinding1.setPf(pf);
                return mFragmentAgeBinding1.getRoot();
        }

    }

    @Override
    public void onClick(View view) {

        float monthlyContribution = (pf.getBasicSalary() * pf.getYourContribution()) / 100 + (pf.getBasicSalary() * pf.getEmployerContribution()) / 100;
        float totalAnnualInterest= 0;
        float monthlyInterest= 0;

        for (int j=1; j<= (pf.getRetirementAge() - pf.getPresentAge()); j++) {

            for (int i = 1; i <= 12; i++) {
                monthlyInterest =  ((pf.getPfROI() / 12) * ((monthlyContribution * i) + yearEndSum)) / 100;
                totalAnnualInterest = totalAnnualInterest + monthlyInterest;
            }

            yearEndSum = yearEndSum + monthlyContribution * 12 + totalAnnualInterest;
        }

        pf.setAccumulatedAmount((int) yearEndSum);
        CommonMethods.showToast(getActivity(), monthlyContribution + "**" + totalAnnualInterest +"**" + pf.getAccumulatedAmt(pf.getAccumulatedAmount()));
        PFResultFragment fragment= new PFResultFragment();
        Bundle bundle= new Bundle();
        bundle.putParcelable("KEY_PF" , pf);
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main, fragment , "PFResultFragment")
                .addToBackStack(null).commit();
    }


}
