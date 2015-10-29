package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.ytx.R;
import com.ytx.activity.SecondActivity;
import com.ytx.widget.TitleView;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.KJFragment;

import java.io.Serializable;

/**
 * Created by Augustus on 15/10/18.
 */
public class PayFragment extends KJFragment implements PullToRefreshBase.OnRefreshListener<ListView>, Serializable {

    private SecondActivity activity;
    @BindView(id = R.id.button1, click = true)
    private Button button1;
    @BindView(id = R.id.button2, click = true)
    private Button button2;
    @BindView(id = R.id.button3, click = true)
    private Button button3;
    @BindView(id = R.id.pay_title)
    private TitleView pay_title;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (SecondActivity) getActivity();
        return View.inflate(getActivity(), R.layout.fragment_pay, null);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.button1:
                activity.changeFragment(new LogisticsFragment(),true);
                break;
            case R.id.button2:
                activity.changeFragment(new PayFailFragment(),true);
                break;
            case R.id.button3:
                activity.changeFragment(new OrderDetailFragment(),true);
                break;
            case R.id.titlebar_img_back:
                getFragmentManager().popBackStack();
                break;
        }
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
    }

    @Override
    protected void initData() {
        super.initData();
        pay_title.setTitleText("支付结果");
        pay_title.setLeftImage(getResources().getDrawable(R.drawable.titlebar_back_bg));
        pay_title.setLeftClick(this);

    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

    }
}
