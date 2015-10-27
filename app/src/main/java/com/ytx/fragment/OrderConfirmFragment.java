package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ytx.R;
import com.ytx.activity.SecondActivity;
import com.ytx.adapter.OrderCoinfirmAdapter;
import com.ytx.data.ActivityInfo;
import com.ytx.data.Product;
import com.ytx.data.Shop;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.pulltorefresh.PullToRefreshListView;
import org.kymjs.kjframe.ui.BindView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Augustus on 15/10/18.
 */
public class OrderConfirmFragment extends TitleBarFragment implements PullToRefreshBase.OnRefreshListener<ListView>, Serializable {

    private SecondActivity activity;
    @BindView(id = R.id.list)
    private PullToRefreshListView pullToRefreshListView;
    private View order_head;
    private View order_foot;
    private ArrayList<Shop> mData = new ArrayList<Shop>();
    private OrderCoinfirmAdapter orderCoinfirmAdapter;
    @BindView(id = R.id.tv_operate,click = true)
    private TextView tv_operate;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (SecondActivity) getActivity();
        order_head = LayoutInflater.from(activity).inflate(R.layout.fragment_order_head, null);
        order_foot = LayoutInflater.from(activity).inflate(R.layout.fragment_order_foot, null);
        order_head.setOnClickListener(this);
        return View.inflate(getActivity(), R.layout.fragment_order_confirm, null);
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
            actionBarRes.title = "填写订单";
            actionBarRes.right_txt = "";
        } else {
            setTitle("填写订单");
            setRightText("");
        }
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        if(v == order_head) {
            activity.changeFragment(new AddressFragment(), true);
        }
        if (v == tv_operate){
            activity.changeFragment(new PayFragment(), true);
        }
    }

    @Override
    public void onBackClick() {
        super.onBackClick();
        activity.finish();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.DISABLED);
        pullToRefreshListView.getRefreshableView().addHeaderView(order_head);
        pullToRefreshListView.getRefreshableView().addFooterView(order_foot);

        for (int i = 0; i < 3; i++) {
            Shop shop = new Shop();
            shop.name = "商家" + i;
            ArrayList<Product> list = new ArrayList<Product>();
            for (int t = 0; t < 2; t++) {
                Product product = new Product();
                product.pName = shop.name + "的产品" + t;
                product.price = 1;
                product.priceOrigin = 2;
                product.productNum = 1;
                product.color = "白色";
                product.size = "MS";
                product.preferential = 1;
                list.add(product);
            }
            for (int t = 0; t < 2; t++) {
                ActivityInfo info = new ActivityInfo();
                info.content = "活动商品已满足［满500元换购］活动" + t;
                shop.activityInfo.add(info);
            }
            shop.coupon = i;
            shop.products.addAll(list);
            mData.add(shop);
        }

        ListView listView = pullToRefreshListView.getRefreshableView();
        orderCoinfirmAdapter = new OrderCoinfirmAdapter(listView, mData, R.layout.item_order_confirm);
        pullToRefreshListView.setAdapter(orderCoinfirmAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

    }
}
