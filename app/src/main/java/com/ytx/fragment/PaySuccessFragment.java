package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ytx.R;
import com.ytx.activity.SecondActivity;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by xiezuoyuan on 15/10/27.
 */
public class PaySuccessFragment extends TitleBarFragment {

    private SecondActivity activity;

    @BindView(id = R.id.tv_goon,click = true)
    private TextView tv_goon;//继续购物
    @BindView(id = R.id.tv_order , click = true)
    private TextView tv_order;//订单详情
    @BindView(id = R.id.tv_pay_money)
    private TextView tv_pay_money;//支付金额
    @BindView(id = R.id.tv_order_number)
    private TextView tv_order_number;//订单号

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (SecondActivity) getActivity();
        return View.inflate(getActivity(), R.layout.fragment_pay_success, null);
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        super.setActionBarRes(actionBarRes);
        setTitleBar(actionBarRes);
        actionBarRes.backImageDrawable = getResources().getDrawable(R.drawable.titlebar_back_bg);
    }

    @Override
    public void onChange() {
        super.onChange();
        setTitleBar(null);
    }

    private void setTitleBar(ActionBarRes actionBarRes) {
        if(null != actionBarRes) {
            actionBarRes.title = "支付宝支付";
            actionBarRes.right_txt = "";
        } else {
            setTitle("支付宝支付");
            setRightText("");
        }
    }

    @Override
    public void onBackClick() {
        super.onBackClick();
        activity.finish();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
    }
}
