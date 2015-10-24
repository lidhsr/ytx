package com.ytx.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ytx.R;
import com.ytx.activity.HomeActivity;
import com.ytx.activity.SecondActivity;
import com.ytx.app.FragmentType;
import com.ytx.data.ActivityInfo;
import com.ytx.data.Product;
import com.ytx.data.Shop;
import com.ytx.widget.MyListView;

import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.Collection;

/**
 * Created by xiezuoyuan on 15/10/20.
 */
public class OrderCoinfirmAdapter extends KJAdapter<Shop> {
    private Context context;
    private KJAdapter<Product> productKJAdapter;
    private KJAdapter<ActivityInfo> kjAdapter;

    public OrderCoinfirmAdapter(AbsListView view, Collection mDatas, int itemLayoutId) {
        super(view, mDatas, itemLayoutId);
        context = view.getContext();
    }

    @Override
    public void convert(final AdapterHolder helper, final Shop item, boolean isScrolling) {
        helper.setText(R.id.tv_shopname, item.name);
        MyListView myListViewProduct = (MyListView) helper.getConvertView().findViewById(R.id.myListViewProduct);
        productKJAdapter = new KJAdapter<Product>(myListViewProduct, item.products, R.layout.item_order_product) {

            @Override
            public void convert(AdapterHolder helper, Product productInfo, boolean isScrolling, int position) {
                helper.setText(R.id.item_left_txt, productInfo.pName);
                helper.setText(R.id.tv_price, String.valueOf(productInfo.price));
                helper.setText(R.id.tv_color, "颜色:" + productInfo.color);
                helper.setText(R.id.tv_size, "尺寸:" + productInfo.size);
                helper.setText(R.id.tv_num, "x " + productInfo.productNum);
                helper.setText(R.id.tv_coupons, "已优惠 ¥" + productInfo.preferential);
                TextView tv = helper.getView(R.id.tv_price_origin);
                tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                tv.setText(String.valueOf(productInfo.priceOrigin));
            }
        };
        myListViewProduct.setAdapter(productKJAdapter);

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

    @Override
    public void refresh(Collection<Shop> datas) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }
}
