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
    public void convert(final AdapterHolder helper, Shop item, boolean isScrolling) {
        helper.setText(R.id.tv_shopname,item.name);
        SwipeListView mListView = (SwipeListView) helper.getConvertView().findViewById(R.id.listview);
        SwipeAdapter adapter = new SwipeAdapter(context, mListView.getRightViewWidth(),
                new SwipeAdapter.IOnItemRightClickListener() {
                    @Override
                    public void onRightClick(View v, int position) {
                        // TODO Auto-generated method stub
                        Toast.makeText(context, "right onclick " + position,
                                Toast.LENGTH_SHORT).show();
                    }
                },item.pList);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "item onclick " + position, Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    @Override
    public void refresh(Collection<Shop> datas) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }
}
