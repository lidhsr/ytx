package com.ytx.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ytx.R;
import com.ytx.activity.HomeActivity;
import com.ytx.adapter.MyOrderAdapter;
import com.ytx.adapter.MyPagerAdapter;
import com.ytx.widget.TitleView;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.pulltorefresh.PullToRefreshListView;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.KJFragment;
import org.kymjs.kjframe.widget.PagerSlidingTabStrip;

import java.util.ArrayList;

/**
 * Created by Augustus on 15/10/18.
 */
public class MyFragment extends KJFragment implements ViewPager.OnPageChangeListener {

    private HomeActivity activity;
    @BindView(id = R.id.my_title)
    private TitleView my_title;
    @BindView(id = R.id.pager_tab)
    private PagerSlidingTabStrip pager_tab;
    @BindView(id = R.id.pager)
    private ViewPager pager;
    private MyPagerAdapter adapter;
    private ArrayList<View> mListViews;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (HomeActivity) getActivity();
        return View.inflate(activity, R.layout.fragment_my, null);
    }

    @Override
    protected void initData() {
        super.initData();
        my_title.setTitleText(getString(R.string.bottombar_content5));
        mListViews = new ArrayList<>();
        mListViews.add(getInflateView());
        mListViews.add(getInflateView());
        mListViews.add(getInflateView());
        mListViews.add(getInflateView());
        mListViews.add(getInflateView());
        adapter = new MyPagerAdapter(mListViews);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        pager.setAdapter(adapter);
        int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        pager_tab.setShouldExpand(true);
        pager_tab.setViewPager(pager);
        pager_tab.setIndicatorHeight(4);
        pager_tab.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private View getInflateView(){
        View view = View.inflate(getActivity(), R.layout.fragment_empty, null);
        PullToRefreshListView pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.list);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>(){
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        ListView listView = pullToRefreshListView.getRefreshableView();
        ArrayList<Object> mData = new ArrayList<>();
        mData.add(new Object());
        mData.add(new Object());
        mData.add(new Object());
        mData.add(new Object());
        pullToRefreshListView.setAdapter(new MyOrderAdapter(listView, mData, R.layout.item_my_order));
        return view;
    }
}
