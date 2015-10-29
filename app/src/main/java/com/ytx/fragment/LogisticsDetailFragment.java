package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ytx.R;
import com.ytx.activity.SecondActivity;
import com.ytx.widget.TitleView;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.KJFragment;

/**
 * Created by xiezuoyuan on 15/10/27.
 */
public class LogisticsDetailFragment extends KJFragment implements PullToRefreshBase.OnRefreshListener<ListView>{

    private SecondActivity activity;
    @BindView(id = R.id.logistics_detail_title)
    private TitleView logistics_detail_title;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (SecondActivity) getActivity();
        return View.inflate(activity, R.layout.fragment_logistics_detail, null);
    }

    @Override
    protected void initData() {
        super.initData();
        logistics_detail_title.setTitleText("物流详情");
        logistics_detail_title.setLeftImage(getResources().getDrawable(R.drawable.titlebar_back_bg));
        logistics_detail_title.setLeftClick(this);

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
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

    }
}
