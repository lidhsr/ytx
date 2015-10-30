package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ytx.R;
import com.ytx.activity.HomeActivity;
import com.ytx.widget.TitleView;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.pulltorefresh.PullToRefreshListView;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;

/**
 * Created by Augustus on 15/10/18.
 */
public class MainFragment extends SupportFragment implements PullToRefreshBase.OnRefreshListener<ListView> {

    private HomeActivity activity;
    @BindView(id = R.id.list)
    private PullToRefreshListView pullToRefreshListView;
    @BindView(id = R.id.fragment_title)
    private TitleView fragment_title;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (HomeActivity) getActivity();
        return View.inflate(activity, R.layout.fragment_main, null);
    }

    @Override
    protected void initData() {
        super.initData();
        fragment_title.setTitleText(getString(R.string.bottombar_content1));
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pullToRefreshListView.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

    }
}
