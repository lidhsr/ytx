package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ytx.R;
import com.ytx.activity.SecondActivity;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.ui.KJFragment;

/**
 * Created by xiezuoyuan on 15/10/27.
 */
public class LogisticsDetailFragment extends KJFragment implements PullToRefreshBase.OnRefreshListener<ListView>{

    private SecondActivity activity;
    private TitleFragment titleFragment;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (SecondActivity) getActivity();
        return View.inflate(getActivity(), R.layout.fragment_logistics_detail, null);
    }

    @Override
    protected void initData() {
        super.initData();
        titleFragment = (TitleFragment) getFragmentManager().findFragmentById(R.id.logistics_detail_title);
        titleFragment.setTitleText("物流详情");
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

    }
}
