package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ytx.R;
import com.ytx.activity.SecondActivity;
import com.ytx.adapter.AddressAdapter;
import com.ytx.data.AddressInfo;
import com.ytx.listener.OnClickListener;
import com.ytx.widget.DialogTools;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;

import java.io.Serializable;

/**
 * Created by Augustus on 15/10/18.
 */
public class AddressEditorFragment extends TitleBarFragment {

    private SecondActivity activity;
    private int add;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (SecondActivity) getActivity();
        return View.inflate(getActivity(), R.layout.fragment_address_editor, null);
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
        String title = add == 1 ? "添加收货地址" : "修改收货地址"; // 1 是新增
        if(null != actionBarRes) {
            actionBarRes.title = title;
            actionBarRes.right_txt = "保存";
        } else {
            setTitle(title);
            setRightText("保存");
        }
    }

    @Override
    public void onRightTxtClick() {
        super.onRightTxtClick();
        getFragmentManager().popBackStack();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
        }
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
    }

    @Override
    protected void initData() {
        super.initData();
        add = getArguments().getInt("add");
    }

}
