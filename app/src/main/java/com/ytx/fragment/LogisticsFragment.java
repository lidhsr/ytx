package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ytx.R;
import com.ytx.activity.SecondActivity;
import com.ytx.adapter.LogisticsAdapter;
import com.ytx.widget.TitleView;

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
    @BindView(id = R.id.fragment_title)
    private TitleView fragment_title;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (SecondActivity) getActivity();
        return View.inflate(getActivity(), R.layout.fragment_main, null);
    }

    @Override
    protected void initData() {
        super.initData();
        fragment_title.setTitleText("查看物流");
        fragment_title.setLeftImage(getResources().getDrawable(R.drawable.titlebar_back_bg));
        fragment_title.setLeftClick(this);

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
