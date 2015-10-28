package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ytx.R;
import com.ytx.activity.SecondActivity;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.KJFragment;

/**
 * Created by xiezuoyuan on 15/10/27.
 */
public class PayFailFragment extends KJFragment {

    private SecondActivity activity;

    @BindView(id = R.id.tv_btn, click = true)
    private TextView tv_btn;
    @BindView(id = R.id.tv_reason)
    private TextView tv_reason;
    private TitleFragment titleFragment;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (SecondActivity) getActivity();
        return View.inflate(getActivity(), R.layout.fragment_pay_fail, null);
    }

    @Override
    protected void initData() {
        super.initData();
        titleFragment = (TitleFragment) getFragmentManager().findFragmentById(R.id.pay_fail_title);
        titleFragment.setTitleText("支付结果");
    }

//    @Override
//    public void onBackClick() {
//        super.onBackClick();
//        activity.finish();
//    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
    }
}
