package com.example.com.myapplication.PFCalculator;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.com.myapplication.R;
import com.example.com.myapplication.Utility.CommonMethods;
import com.example.com.myapplication.databinding.ActivityEmiBinding;
import com.example.com.myapplication.databinding.FragmentPagerBinding;


/**
 * Created by yaucheukming on 16/9/2016.
 */

public class PFPagerFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private FragmentPagerBinding mFragmentPagerBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentPagerBinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_pager, container, false), new PFDataBindingComponent());
        mFragmentPagerBinding.setPagerAdapter(new PFViewPagerAdapter(getChildFragmentManager()));
        mFragmentPagerBinding.btnPrevious.setOnClickListener(this);
        mFragmentPagerBinding.btnNext.setOnClickListener(this);
        mFragmentPagerBinding.demoViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mFragmentPagerBinding.demoViewPager.setOnPageChangeListener(this);
        return mFragmentPagerBinding.getRoot();
    }

    @Override
    public void onClick(View view) {
        //CommonMethods.showToast(getActivity(), "clicked!");
        switch (view.getId()) {
            case R.id.btn_previous:
                mFragmentPagerBinding.demoViewPager.setCurrentItem(mFragmentPagerBinding.demoViewPager.getCurrentItem() - 1);
                break;
            case R.id.btn_next:
                mFragmentPagerBinding.demoViewPager.setCurrentItem(mFragmentPagerBinding.demoViewPager.getCurrentItem() + 1);
                break;
        }

    }

    void onPFCalculate(int position) {
        mFragmentPagerBinding.demoViewPager.setCurrentItem(position);

    }

    void onBackPressed(Activity activity){
            if(mFragmentPagerBinding.demoViewPager.getCurrentItem() != 0)
                mFragmentPagerBinding.demoViewPager.setCurrentItem(mFragmentPagerBinding.demoViewPager.getCurrentItem() - 1);
            else
                activity.finish();
        }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        if(position==5){
            mFragmentPagerBinding.btnNext.setVisibility(View.GONE);
            mFragmentPagerBinding.btnPrevious.setVisibility(View.VISIBLE);
        } else if(position==0){
            mFragmentPagerBinding.btnPrevious.setVisibility(View.GONE);
            mFragmentPagerBinding.btnNext.setVisibility(View.VISIBLE);
        } else {
            mFragmentPagerBinding.btnNext.setVisibility(View.VISIBLE);
            mFragmentPagerBinding.btnPrevious.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}




