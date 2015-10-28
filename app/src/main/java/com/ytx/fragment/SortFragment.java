package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ytx.R;
import com.ytx.activity.HomeActivity;
import com.ytx.adapter.CartAdapter;
import com.ytx.data.Product;
import com.ytx.data.Shop;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import org.kymjs.kjframe.pulltorefresh.PullToRefreshListView;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.KJFragment;
import org.kymjs.kjframe.utils.StringUtils;

import java.util.ArrayList;

/**
 * Created by Augustus on 15/10/18.
 */
public class SortFragment extends KJFragment implements OnRefreshListener<ListView>{

    private HomeActivity activity;
    @BindView(id = R.id.list)
    private PullToRefreshListView pullToRefreshListView;
    private TitleFragment titleFragment;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (HomeActivity) getActivity();
        return View.inflate(activity, R.layout.fragment_sort, null);
    }

    @Override
    protected void initData() {
        super.initData();
        titleFragment = (TitleFragment) getFragmentManager().findFragmentById(R.id.sort_title);
        titleFragment.setTitleText(getString(R.string.bottombar_content2));
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pullToRefreshListView.setOnRefreshListener(this);

    }

    @Override
    protected void widgetClick(View v) {
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

    }


    public interface AfterSelectedListener {
        public void todo();
    }

}
