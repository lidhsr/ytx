package com.ytx.fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.ytx.R;
import com.ytx.activity.SecondActivity;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.ui.BindView;

import java.io.Serializable;

/**
 * Created by Augustus on 15/10/18.
 */
public class PayFragment extends TitleBarFragment implements PullToRefreshBase.OnRefreshListener<ListView>, Serializable {

    private SecondActivity activity;
    @BindView(id = R.id.button1, click = true)
    private Button button1;
    @BindView(id = R.id.button2, click = true)
    private Button button2;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (SecondActivity) getActivity();
        return View.inflate(getActivity(), R.layout.fragment_pay, null);
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
            actionBarRes.title = "填写订单";
            actionBarRes.right_txt = "";
        } else {
            setTitle("填写订单");
            setRightText("");
        }
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.button1:
//                activity.replaceFragment(new CouponsFragment());
//                activity.changeFragment(new PaySuccessFragment(),true);
                activity.changeFragment(new LogisticsFragment(),true);
                break;
            case R.id.button2:
//                activity.replaceFragment(new CouponsFragment());
                activity.changeFragment(new PayFailFragment(),true);
                break;
        }
    }

    @Override
    public void onBackClick() {
        super.onBackClick();
        FragmentManager fragmentManager = getFragmentManager();
        int count = fragmentManager.getBackStackEntryCount();
        if(count > 0) {
            fragmentManager.popBackStack();
            return;
        }
        activity.finish();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        Log.e("msg", "initWidget");
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e("msg", "initData");
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

    }
}
