package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ytx.R;
import com.ytx.activity.SecondActivity;
import com.ytx.adapter.OrderDetailAdapter;
import com.ytx.widget.MyListView;
import com.ytx.widget.TitleView;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.KJFragment;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Augustus on 15/10/18.
 */
public class OrderDetailFragment extends KJFragment implements PullToRefreshBase.OnRefreshListener<ListView>, Serializable {

    private SecondActivity activity;
    @BindView(id = R.id.myListView)
    private MyListView myListView;
    private ArrayList<Object> mData = new ArrayList<Object>();
    @BindView(id = R.id.order_detail_title)
    private TitleView order_detail_title;
    private OrderDetailAdapter orderDetailAdapter;
    @BindView(id = R.id.btn_receive,click = true)
    private TextView btn_receive;
    @BindView(id = R.id.btn_check,click = true)
    private TextView btn_check;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (SecondActivity) getActivity();
        return View.inflate(activity, R.layout.fragment_order_detail, null);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        if(v.getId() == R.id.titlebar_img_back) {
            activity.finish();
        }
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        mData.add(new Object());
        mData.add(new Object());
        mData.add(new Object());
        orderDetailAdapter = new OrderDetailAdapter(myListView,mData,R.layout.item_order_detail);
        myListView.setAdapter(orderDetailAdapter);

    }

    @Override
    protected void initData() {
        super.initData();
        order_detail_title.setTitleText("订单详情");
        order_detail_title.setLeftImage(getResources().getDrawable(R.drawable.titlebar_back_bg));
        order_detail_title.setLeftClick(this);

    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

    }
}
