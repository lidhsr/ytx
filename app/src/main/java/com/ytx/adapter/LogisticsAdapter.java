package com.ytx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import com.ytx.R;
import com.ytx.activity.SecondActivity;
import com.ytx.fragment.LogisticsDetailFragment;

import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.Collection;

/**
 * Created by xiezuoyuan on 15/10/27.
 */
public class LogisticsAdapter extends KJAdapter {

    private Context context;

    public LogisticsAdapter(AbsListView view, Collection mDatas, int itemLayoutId) {
        super(view, mDatas, itemLayoutId);
        context = view.getContext();
    }

    @Override
    public void convert(AdapterHolder helper, Object item, boolean isScrolling, int position) {
        helper.setText(R.id.tv_lot, String.valueOf(position + 1));
        helper.getView(R.id.btn_receive).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        LinearLayout ll_goods = helper.getView(R.id.ll_goods);
        ll_goods.removeAllViews();
        for (int i = 0;i < 4 ;i++){
            View view = LayoutInflater.from(context).inflate(R.layout.item_logistics_goods,null);
            ll_goods.addView(view);
        }
        LinearLayout ll_trigger = helper.getView(R.id.ll_trigger);
        ll_trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SecondActivity)context).changeFragment(new LogisticsDetailFragment(),true);
            }
        });
    }
}
