package com.ytx.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ytx.R;
import com.ytx.activity.HomeActivity;
import com.ytx.adapter.MyPagerAdapter;
import com.ytx.widget.TitleView;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;
import org.kymjs.kjframe.widget.PagerSlidingTabStrip;

import java.util.ArrayList;

/**
 * Created by Augustus on 15/10/18.
 */
public class MyFragment extends SupportFragment implements ViewPager.OnPageChangeListener {

    private HomeActivity activity;
    @BindView(id = R.id.my_title)
    private TitleView my_title;
    @BindView(id = R.id.pager_tab)
    private PagerSlidingTabStrip pager_tab;
    @BindView(id = R.id.pager)
    private ViewPager pager;
    private MyPagerAdapter adapter;
    private ArrayList<SupportFragment> mListViews;

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
        mListViews.add(new MyOrder0Fragment());
        mListViews.add(new EmptyFragment());
        mListViews.add(new EmptyFragment());
        mListViews.add(new EmptyFragment());
        mListViews.add(new EmptyFragment());
        adapter = new MyPagerAdapter(getFragmentManager(), mListViews);
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

}
