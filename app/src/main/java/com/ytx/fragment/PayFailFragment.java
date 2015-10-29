package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ytx.R;
import com.ytx.activity.SecondActivity;
import com.ytx.widget.TitleView;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;

/**
 * Created by xiezuoyuan on 15/10/27.
 */
public class PayFailFragment extends SupportFragment {

    private SecondActivity activity;

    @BindView(id = R.id.tv_btn, click = true)
    private TextView tv_btn;
    @BindView(id = R.id.tv_reason)
    private TextView tv_reason;
    @BindView(id = R.id.pay_fail_title)
    private TitleView pay_fail_title;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (SecondActivity) getActivity();
        return View.inflate(getActivity(), R.layout.fragment_pay_fail, null);
    }

    @Override
    protected void initData() {
        super.initData();
        pay_fail_title.setTitleText("支付结果");
        pay_fail_title.setLeftImage(getResources().getDrawable(R.drawable.titlebar_back_bg));
        pay_fail_title.setLeftClick(this);

    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.titlebar_img_back:
                getFragmentManager().popBackStack();
                break;
        }
    }

}
