package com.ytx.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ytx.R;
import com.ytx.data.ActivityInfo;
import com.ytx.data.AddressInfo;
import com.ytx.listener.OnClickListener;

import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.Collection;

/**
 * Created by xiezuoyuan on 15/10/20.
 */
public class AddressAdapter extends KJAdapter<AddressInfo> {
    private Context context;
    private OnClickListener listener;

    public AddressAdapter(AbsListView view, Collection mDatas, int itemLayoutId, OnClickListener listener) {
        super(view, mDatas, itemLayoutId);
        context = view.getContext();
        this.listener = listener;
    }

    @Override
    public void convert(final AdapterHolder helper, final AddressInfo item, boolean isScrolling, final int position) {
        helper.setText(R.id.tv_name, item.name);
        RadioButton rb = helper.getView(R.id.rb_check);
        rb.setChecked(item.isChecked);
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });
        helper.setText(R.id.mobile, item.mobile);
        helper.setText(R.id.tv_address, item.address);
    }

    @Override
    public void refresh(Collection<AddressInfo> datas) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }
}
