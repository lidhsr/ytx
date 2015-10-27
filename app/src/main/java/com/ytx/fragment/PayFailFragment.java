package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ytx.R;

/**
 * Created by xiezuoyuan on 15/10/27.
 */
public class PayFailFragment extends TitleBarFragment {
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return View.inflate(getActivity(), R.layout.fragment_pay_fail, null);
    }
}
