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
public class PaySuccessFragment extends SupportFragment {

    private SecondActivity activity;

    @BindView(id = R.id.tv_goon,click = true)
    private TextView tv_goon;//继续购物
    @BindView(id = R.id.tv_order , click = true)
    private TextView tv_order;//订单详情
    @BindView(id = R.id.tv_pay_money)
    private TextView tv_pay_money;//支付金额
    @BindView(id = R.id.tv_order_number)
    private TextView tv_order_number;//订单号
    @BindView(id = R.id.pay_success_title)
    private TitleView pay_success_title;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (SecondActivity) getActivity();
        return View.inflate(getActivity(), R.layout.fragment_pay_success, null);
    }

    @Override
    protected void initData() {
        super.initData();
        pay_success_title.setTitleText("支付结果");
        pay_success_title.setLeftImage(getResources().getDrawable(R.drawable.titlebar_back_bg));
        pay_success_title.setLeftClick(this);

    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch(v.getId()) {
            case R.id.titlebar_img_back:
                getFragmentManager().popBackStack();
                break;
        }
    }
}
