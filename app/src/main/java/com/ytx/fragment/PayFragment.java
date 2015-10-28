package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.ytx.R;
import com.ytx.activity.SecondActivity;

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
    private TitleFragment titleFragment;

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
        }
    }

//    @Override
//    public void onBackClick() {
//        super.onBackClick();
//        FragmentManager fragmentManager = getFragmentManager();
//        int count = fragmentManager.getBackStackEntryCount();
//        if(count > 0) {
//            fragmentManager.popBackStack();
//            return;
//        }
//        activity.finish();
//    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
    }

    @Override
    protected void initData() {
        super.initData();
        titleFragment = (TitleFragment) getFragmentManager().findFragmentById(R.id.pay_title);
        titleFragment.setTitleText("支付结果");
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

    }
}
