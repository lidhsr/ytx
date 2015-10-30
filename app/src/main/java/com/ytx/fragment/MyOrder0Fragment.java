package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ytx.R;
import com.ytx.adapter.MyOrderAdapter;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.pulltorefresh.PullToRefreshListView;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by xiezuoyuan on 15/10/30.
 */
public class MyOrder0Fragment extends SupportFragment implements PullToRefreshBase.OnRefreshListener<ListView>, Serializable {

    @BindView(id = R.id.list)
    private PullToRefreshListView pullToRefreshListView;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return View.inflate(getActivity(), R.layout.fragment_empty, null);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.DISABLED);
        pullToRefreshListView.setOnRefreshListener(this);

    }

    @Override
    protected void initData() {
        super.initData();
        ListView listView = pullToRefreshListView.getRefreshableView();
        ArrayList<Object> mData = new ArrayList<>();
        mData.add(new Object());
        mData.add(new Object());
        mData.add(new Object());
        mData.add(new Object());
        pullToRefreshListView.setAdapter(new MyOrderAdapter(listView, mData, R.layout.item_my_order));
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

    }
}
