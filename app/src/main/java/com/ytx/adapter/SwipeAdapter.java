
package com.ytx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.ytx.R;
import com.ytx.data.Product;

import java.util.ArrayList;
import java.util.HashMap;


public class SwipeAdapter extends BaseAdapter {
    /**
     * 上下文对象
     */
    private Context mContext = null;

    /**
     * 
     */
    private int mRightWidth = 0;

    /**
     * 单击事件监听器
     */
    private IOnItemRightClickListener mListener = null;

    public interface IOnItemRightClickListener {
        void onRightClick(View v, int position);
    }

    private ArrayList<Product> list = null;

    /**
     * @param ctx
     */
    public SwipeAdapter(Context ctx, int rightWidth, IOnItemRightClickListener l,ArrayList<Product> list) {
        mContext = ctx;
        mRightWidth = rightWidth;
        mListener = l;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final int thisPosition = position;
        Product product = list.get(position);
        ViewHolder item;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_cart_swipe, parent, false);
            item = new ViewHolder();
            item.item_left = (View)convertView.findViewById(R.id.item_left);
            item.item_right = (View)convertView.findViewById(R.id.item_right);
            item.item_left_txt = (TextView)convertView.findViewById(R.id.item_left_txt);
            item.item_right_txt = (TextView)convertView.findViewById(R.id.item_right_txt);
            convertView.setTag(item);
        } else {// 有直接获得ViewHolder
            item = (ViewHolder)convertView.getTag();
        }
        LinearLayout.LayoutParams lp1 = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        item.item_left.setLayoutParams(lp1);
        LinearLayout.LayoutParams lp2 = new LayoutParams(mRightWidth, LayoutParams.MATCH_PARENT);
        item.item_right.setLayoutParams(lp2);
        item.item_left_txt.setText(product.pName);
        item.item_right_txt.setText("删除");
        item.item_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onRightClick(v, thisPosition);
                }
            }
        });

        return convertView;
    }

    private class ViewHolder {
        View item_left;

        View item_right;

        TextView item_left_txt;

        TextView item_right_txt;
    }
}

