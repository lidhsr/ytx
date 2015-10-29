package com.ytx.adapter;

import android.content.Context;
import android.widget.AbsListView;

import com.ytx.R;
import com.ytx.widget.MyListView;

import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by xiezuoyuan on 15/10/27.
 */
public class MyOrderAdapter extends KJAdapter {

    private Context context;

    public MyOrderAdapter(AbsListView view, Collection mDatas, int itemLayoutId) {
        super(view, mDatas, itemLayoutId);
        context = view.getContext();
    }

    @Override
    public void convert(AdapterHolder helper, Object item, boolean isScrolling, int position) {

        MyListView myListViewProduct = helper.getView(R.id.myListViewProduct);
        ArrayList<Object> mData = new ArrayList<>();
        mData.add(new Object());
        mData.add(new Object());
        myListViewProduct.setAdapter(new KJAdapter<Object>(myListViewProduct, mData, R.layout.item_my_order_product) {
            @Override
            public void convert(AdapterHolder helper, Object item, boolean isScrolling, int position) {

            }
        });


    }
}
