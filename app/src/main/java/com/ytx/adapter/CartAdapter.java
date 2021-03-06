package com.ytx.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ytx.R;
import com.ytx.activity.HomeActivity;
import com.ytx.activity.SecondActivity;
import com.ytx.app.FragmentType;
import com.ytx.data.ActivityInfo;
import com.ytx.data.Shop;
import com.ytx.fragment.SortFragment;
import com.ytx.widget.MyListView;
import com.ytx.widget.SwipeListView;

import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.Collection;

/**
 * Created by xiezuoyuan on 15/10/20.
 */
public class CartAdapter extends KJAdapter<Shop> {
    private Context context;
    private SortFragment.AfterSelectedListener listener;
    private SwipeAdapter adapter;
    private SwipeAdapter.PopupClickListener popupClickListener;
    private KJAdapter<ActivityInfo> kjAdapter;

    public CartAdapter(AbsListView view, Collection mDatas, int itemLayoutId, SortFragment.AfterSelectedListener listener) {
        super(view, mDatas, itemLayoutId);
        context = view.getContext();
        this.listener = listener;
    }

    @Override
    public void convert(final AdapterHolder helper, final Shop item, boolean isScrolling) {
        helper.setText(R.id.tv_shopname, item.name);
        SwipeListView mListView = (SwipeListView) helper.getConvertView().findViewById(R.id.listview);
        adapter = new SwipeAdapter(context, mListView.getRightViewWidth(),
                new SwipeAdapter.IOnItemRightClickListener() {
                    @Override
                    public void onRightClick(View v, int position) {
                        Toast.makeText(context, item.name + position + "right",
                                Toast.LENGTH_SHORT).show();
                    }
                }, new SwipeAdapter.IOnItemLeftClickListener() {
            @Override
            public void onLeftClick(View v, int position) {
                Toast.makeText(context, item.name + position + "left",
                        Toast.LENGTH_SHORT).show();
            }
        }, item.products, listener);
        mListView.setAdapter(adapter);
        if (null != popupClickListener) {
            adapter.setPopupClickListener(popupClickListener);
        }
        MyListView myListView = (MyListView) helper.getConvertView().findViewById(R.id.myListView);
        kjAdapter = new KJAdapter<ActivityInfo>(myListView, item.activityInfo, R.layout.item_cart_activity) {
            @Override
            public void convert(AdapterHolder helper, ActivityInfo activityInfo, boolean isScrolling, int position) {
                helper.setText(R.id.tv_activity_content, activityInfo.content);
            }
        };
        myListView.setAdapter(kjAdapter);
        LinearLayout ll_coupon = (LinearLayout) helper.getConvertView().findViewById(R.id.ll_coupon);
        if (item.coupon == 1) {
            ll_coupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle extras = new Bundle();
                    extras.putInt(FragmentType.FRAGMENT_TYPE, FragmentType.COUPONS_FRAGMENT);
                    ((HomeActivity) context).showActivity(((HomeActivity) context), SecondActivity.class, extras);
                }
            });
        }
        ll_coupon.setVisibility(item.coupon == 1 ? View.VISIBLE : View.GONE);
    }

    public void setPopupClickListener(SwipeAdapter.PopupClickListener listener) {
        this.popupClickListener = listener;
    }

    @Override
    public void refresh(Collection<Shop> datas) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }
}
