package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ytx.R;
import com.ytx.activity.SecondActivity;
import com.ytx.data.Coupons;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.pulltorefresh.PullToRefreshListView;
import org.kymjs.kjframe.tools.ToastUtils;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Augustus on 15/10/18.
 */
public class CouponsFragment extends TitleBarFragment implements PullToRefreshBase.OnRefreshListener<ListView>, Serializable {

    private SecondActivity activity;
    @BindView(id = R.id.list)
    private PullToRefreshListView pullToRefreshListView;
    @BindView(id = R.id.layout_coupons_empty)
    private View coupons_empty;
    private ArrayList<Coupons> mData = new ArrayList<>();
    private ArrayList<Coupons> mDataValid = new ArrayList<>();
    private ArrayList<Coupons> mDataInvalid = new ArrayList<>();

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (SecondActivity) getActivity();
        return View.inflate(activity, R.layout.fragment_coupons, null);
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
            actionBarRes.title = "优惠券";
            actionBarRes.right_txt = "";
        } else {
            setTitle("优惠券");
            setRightText("");
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
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pullToRefreshListView.setOnRefreshListener(this);
        pullToRefreshListView.setEmptyView(coupons_empty);

//        Coupons c = new Coupons();
//        c.intro = "单笔订单满999元可用";
//        c.invalidTime = "有效期至 2015-12-30 23:59";
//        c.price = 20;
//        c.title = "女装频道专用抵用券1";
//        c.status = 1;
//        mDataValid.add(c);
//        Coupons c2 = new Coupons();
//        c2.intro = "单笔订单满1000元可用";
//        c2.invalidTime = "有效期至 2015-12-30 23:59";
//        c2.price = 200;
//        c2.title = "女装频道专用抵用券2";
//        c2.status = 1;
//        mDataValid.add(c2);
//        Coupons c1 = new Coupons();
//        c1.intro = "单笔订单满888元可用";
//        c1.invalidTime = "有效期至 2015-09-30 23:59";
//        c1.price = 20;
//        c1.title = "全场通用抵用券1";
//        c1.status = 1;
//        mDataInvalid.add(c1);
//        Coupons c3 = new Coupons();
//        c3.intro = "单笔订单满888元可用";
//        c3.invalidTime = "有效期至 2015-09-30 23:59";
//        c3.price = 20;
//        c3.title = "全场通用抵用券2";
//        c3.status = 1;
//        mDataInvalid.add(c3);
        ListView listView = pullToRefreshListView.getRefreshableView();
        listView.setAdapter(new KJAdapter<Coupons>(listView, mDataValid, R.layout.item_coupon_main) {
            @Override
            public void convert(AdapterHolder helper, Coupons item, boolean isScrolling, int position) {
                helper.setText(R.id.tv_price, "" + item.price);
                helper.setText(R.id.tv_title, item.title);
                helper.setText(R.id.tv_intro, item.intro);
                helper.setText(R.id.tv_invalidtime, item.invalidTime);
                helper.getView(R.id.btn_use).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showMessage(getActivity(), "1111");
                    }
                });
            }
        });
        if (mDataInvalid.size() > 0){
            View sepView = LayoutInflater.from(getActivity()).inflate(R.layout.item_coupon_sep, null);
            listView.addFooterView(sepView);
            for (Coupons coupons : mDataInvalid){
                View footView = LayoutInflater.from(getActivity()).inflate(R.layout.item_coupon_main, null);
                LinearLayout ll_left = (LinearLayout) footView.findViewById(R.id.ll_left);
                ll_left.setBackgroundResource(R.mipmap.coupon_bg_invalid);
                TextView tv_price = (TextView) footView.findViewById(R.id.tv_price);
                TextView tv_title = (TextView) footView.findViewById(R.id.tv_title);
                TextView tv_intro = (TextView) footView.findViewById(R.id.tv_intro);
                TextView tv_invalidtime = (TextView) footView.findViewById(R.id.tv_invalidtime);
                TextView btn_use = (TextView) footView.findViewById(R.id.btn_use);
                btn_use.setVisibility(View.GONE);
                ImageView iv_invalid = (ImageView) footView.findViewById(R.id.iv_invalid);
                iv_invalid.setVisibility(View.VISIBLE);
                tv_price.setText(""+coupons.price);
                tv_title.setText(coupons.title);
                tv_intro.setText(coupons.intro);
                tv_invalidtime.setText(coupons.invalidTime);

                listView.addFooterView(footView);
            }
        }
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

    }
}
