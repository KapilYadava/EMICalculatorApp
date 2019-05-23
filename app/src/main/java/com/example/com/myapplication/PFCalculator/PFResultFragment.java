package com.example.com.myapplication.PFCalculator;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.com.myapplication.R;
import com.example.com.myapplication.databinding.PfResultBinding;

/**
 * Created by kapil on 03/01/18.
 */

public class PFResultFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PfResultBinding mPfResultBinding = DataBindingUtil.bind(inflater.inflate(R.layout.pf_result, container, false));
        PF pf= getArguments().getParcelable("KEY_PF");
        mPfResultBinding.setPf(pf);
        return mPfResultBinding.getRoot();
    }
}
