package com.ytx.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ytx.R;
import com.ytx.data.Shop;
import com.ytx.widget.SwipeListView;

import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.Collection;

/**
 * Created by xiezuoyuan on 15/10/20.
 */
public class CartAdapter extends KJAdapter<Shop> {
    private Context context;
    public CartAdapter(AbsListView view, Collection mDatas, int itemLayoutId) {
        super(view, mDatas, itemLayoutId);
        context = view.getContext();
    }

    @Override
    public void convert(final AdapterHolder helper, final Shop item, boolean isScrolling) {
        helper.setText(R.id.tv_shopname,item.name);
        SwipeListView mListView = (SwipeListView) helper.getConvertView().findViewById(R.id.listview);
        SwipeAdapter adapter = new SwipeAdapter(context, mListView.getRightViewWidth(),
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
        }, item.pList);
        mListView.setAdapter(adapter);
    }

    @Override
    public void refresh(Collection<Shop> datas) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }
}
