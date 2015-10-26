package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ytx.R;
import com.ytx.activity.SecondActivity;
import com.ytx.adapter.AddressAdapter;
import com.ytx.data.AddressInfo;
import com.ytx.listener.OnClickListener;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.pulltorefresh.PullToRefreshListView;
import org.kymjs.kjframe.ui.BindView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Augustus on 15/10/18.
 */
public class AddressFragment extends TitleBarFragment implements PullToRefreshBase.OnRefreshListener<ListView>, Serializable, OnClickListener {

    private SecondActivity activity;
    @BindView(id = R.id.list)
    private PullToRefreshListView pullToRefreshListView;
    @BindView(id = R.id.layout_address_add, click = true)
    private LinearLayout layout_address_add;
    private AddressAdapter adapter;
    private ArrayList<AddressInfo> data = new ArrayList<>();

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (SecondActivity) getActivity();
        return View.inflate(getActivity(), R.layout.fragment_address_select, null);
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
            actionBarRes.title = "收货地址选择";
            actionBarRes.right_txt = "";
        } else {
            setTitle("收货地址选择");
            setRightText("");
        }
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.layout_address_add:

                break;
        }
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pullToRefreshListView.setOnRefreshListener(this);
        adapter = new AddressAdapter(pullToRefreshListView.getRefreshableView(), data, R.layout.item_address, this);
        pullToRefreshListView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        super.initData();
        AddressInfo info = new AddressInfo();
        info.name = "Name";
        info.mobile = "18888888888";
        info.address = "外星球吧";
        info.isChecked = false;
        data.add(info);

        AddressInfo info2 = new AddressInfo();
        info2.name = "Name";
        info2.mobile = "18888888888";
        info2.address = "外星球吧";
        info.isChecked = false;
        data.add(info2);
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

    }

    @Override
    public void onClick(int position) {
        int size = data.size();
        for(int i=0; i<size; i++) {
            AddressInfo info = data.get(i);
            info.isChecked = false;
            if(i == position) {
                info.isChecked = true;
            }
        }
        adapter.notifyDataSetChanged();
    }
}
