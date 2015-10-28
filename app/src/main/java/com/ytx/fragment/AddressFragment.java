package com.ytx.fragment;

import android.app.AlertDialog;
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
import com.ytx.widget.DialogTools;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.pulltorefresh.PullToRefreshListView;
import org.kymjs.kjframe.tools.ToastUtils;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.KJFragment;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Augustus on 15/10/18.
 */
public class AddressFragment extends KJFragment implements PullToRefreshBase.OnRefreshListener<ListView>, Serializable, OnClickListener {

    private SecondActivity activity;
    @BindView(id = R.id.list)
    private PullToRefreshListView pullToRefreshListView;
    @BindView(id = R.id.layout_address_add, click = true)
    private LinearLayout layout_address_add;
    private AddressAdapter adapter;
    private ArrayList<AddressInfo> data = new ArrayList<>();
    private AlertDialog dialog;
    private TitleFragment titleFragment;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (SecondActivity) getActivity();
        return View.inflate(getActivity(), R.layout.fragment_address_select, null);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.layout_address_add:
                AddressEditorFragment addressEditorFragment = new AddressEditorFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("add", 1);
                addressEditorFragment.setArguments(bundle);
                activity.changeFragment(addressEditorFragment, true);
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
        titleFragment = (TitleFragment) getFragmentManager().findFragmentById(R.id.address_slect_title);
        titleFragment.setTitleText("收货地址选择");

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
    public void onClick(final int position, View v) {
        switch(v.getId()) {
            case R.id.rb_check:
                int size = data.size();
                for(int i=0; i<size; i++) {
                    AddressInfo info = data.get(i);
                    info.isChecked = false;
                    if(i == position) {
                        info.isChecked = true;
                    }
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_edit:
                AddressEditorFragment addressEditorFragment = new AddressEditorFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("add", 2);
                addressEditorFragment.setArguments(bundle);
                activity.changeFragment(addressEditorFragment, true);
                break;
            case R.id.btn_del:
                dialog = DialogTools.showCustomDialog(activity, "确认删除", "确认删除此收货地址吗?",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                switch (v.getId()) {
                                    case R.id.tv_ok:
                                        ToastUtils.showMessage(activity, "position : " + position);
                                        dialog.dismiss();
                                        break;
                                    case R.id.tv_no:
                                        dialog.dismiss();
                                        break;
                                }
                            }
                });
                break;
            case R.id.layout_item_address:
                getFragmentManager().popBackStack();
                break;
        }
    }
}
