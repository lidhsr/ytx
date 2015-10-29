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
import com.ytx.widget.TitleView;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.KJFragment;

import java.io.Serializable;

/**
 * Created by Augustus on 15/10/18.
 */
public class AddressEditorFragment extends KJFragment {

    private SecondActivity activity;
    private int add;
    @BindView(id = R.id.address_editor_title)
    private TitleView address_editor_title;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (SecondActivity) getActivity();
        return View.inflate(getActivity(), R.layout.fragment_address_editor, null);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.titlebar_img_back:
                getFragmentManager().popBackStack();
                break;
            case R.id.titlebar_right_txt:
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
        add = getArguments().getInt("add");
        String title = add == 1 ? "添加收货地址" : "修改收货地址"; // 1 是新增
        address_editor_title.setTitleText(title);
        address_editor_title.setRightText("保存");
        address_editor_title.setLeftImage(getResources().getDrawable(R.drawable.titlebar_back_bg));
        address_editor_title.setLeftClick(this);
        address_editor_title.setRightTextClick(this);
    }

}
