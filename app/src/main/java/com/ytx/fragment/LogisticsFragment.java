package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ytx.R;
import com.ytx.activity.SecondActivity;
import com.ytx.adapter.LogisticsAdapter;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.pulltorefresh.PullToRefreshListView;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.KJFragment;

import java.util.ArrayList;

/**
 * Created by xiezuoyuan on 15/10/27.
 */
public class LogisticsFragment extends KJFragment implements PullToRefreshBase.OnRefreshListener<ListView>{

    private SecondActivity activity;
    @BindView(id = R.id.list)
    private PullToRefreshListView pullToRefreshListView;
    private LogisticsAdapter adapter;
    private ArrayList mData = new ArrayList();
    private TitleFragment titleFragment;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (SecondActivity) getActivity();
        return View.inflate(getActivity(), R.layout.fragment_main, null);
    }

    @Override
    protected void initData() {
        super.initData();
        titleFragment = (TitleFragment) getFragmentManager().findFragmentById(R.id.fragment_title);
        titleFragment.setTitleText("查看物流");
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        ListView listView = pullToRefreshListView.getRefreshableView();
        mData.add(new Object());
        mData.add(new Object());
        adapter = new LogisticsAdapter(listView,mData,R.layout.item_logistics);
        pullToRefreshListView.setAdapter(adapter);
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

    }
}
