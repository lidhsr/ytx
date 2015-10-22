
package com.ytx.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.ytx.R;
import com.ytx.data.Product;
import com.ytx.fragment.SortFragment;

import org.kymjs.kjframe.utils.StringUtils;

import java.util.ArrayList;


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
     * 右单击事件监听器
     */
    private IOnItemRightClickListener mListenerRight = null;

    public interface IOnItemRightClickListener {
        void onRightClick(View v, int position);
    }

    /**
     * 左单击事件监听器
     */
    private IOnItemLeftClickListener mListenerLeft = null;

    private SortFragment.AfterSelectedListener listener;

    public interface IOnItemLeftClickListener {
        void onLeftClick(View v, int position);
    }

    private ArrayList<Product> list = null;

    /**
     * @param ctx
     */
    public SwipeAdapter(Context ctx, int rightWidth, IOnItemRightClickListener mListenerRight,
                        IOnItemLeftClickListener mListenerLeft,ArrayList<Product> list,
                        SortFragment.AfterSelectedListener listener) {
        mContext = ctx;
        mRightWidth = rightWidth;
        this.mListenerRight = mListenerRight;
        this.mListenerLeft = mListenerLeft;
        this.list = list;
        this.listener = listener;
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
        final Product product = list.get(position);
        ViewHolder item;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_cart_swipe, parent, false);
            item = new ViewHolder();
            item.item_left = (View)convertView.findViewById(R.id.item_left);
            item.item_right = (View)convertView.findViewById(R.id.item_right);
            item.item_left_txt = (TextView)convertView.findViewById(R.id.item_left_txt);
            item.item_right_txt = (TextView)convertView.findViewById(R.id.item_right_txt);
            item.iv_product = (ImageView) convertView.findViewById(R.id.iv_product);
            item.cbx_item = (CheckBox) convertView.findViewById(R.id.cbx_item);
            item.ll_left = (LinearLayout) convertView.findViewById(R.id.ll_left);
            item.ll_left_edit = (LinearLayout) convertView.findViewById(R.id.ll_left_edit);
            item.ll_product_info = (LinearLayout) convertView.findViewById(R.id.ll_product_info);
            item.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            item.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
            item.tv_color = (TextView) convertView.findViewById(R.id.tv_color);
            item.tv_size = (TextView) convertView.findViewById(R.id.tv_size);
            item.tv_minus = (TextView) convertView.findViewById(R.id.tv_minus);
            item.tv_num_edit = (TextView) convertView.findViewById(R.id.tv_num_edit);
            item.tv_plus = (TextView) convertView.findViewById(R.id.tv_plus);
            item.tv_product_intro = (TextView) convertView.findViewById(R.id.tv_product_intro);
            item.tv_price_edit = (TextView) convertView.findViewById(R.id.tv_price_edit);
            item.tv_price_origin = (TextView) convertView.findViewById(R.id.tv_price_origin);
            convertView.setTag(item);
        } else {// 有直接获得ViewHolder
            item = (ViewHolder)convertView.getTag();
        }
        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.tv_minus:
                        if (product.productNum > 1)
                            product.productNum--;
                        listener.todo();
                        break;
                    case R.id.tv_plus:
                        product.productNum++;
                        listener.todo();
                        break;
                    case R.id.ll_product_info:
                        if(null != popupClickListener) {
                            popupClickListener.onClick();
                        }
                        break;
                    case R.id.iv_product:
                        if (mListenerLeft != null) {
                            mListenerLeft.onLeftClick(v, thisPosition);
                        }
                        break;
                    case R.id.item_right:
                        if (mListenerRight != null) {
                            mListenerRight.onRightClick(v, thisPosition);
                        }
                        break;
                }
            }
        };
        LinearLayout.LayoutParams lp1 = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        item.item_left.setLayoutParams(lp1);
        LinearLayout.LayoutParams lp2 = new LayoutParams(mRightWidth, LayoutParams.MATCH_PARENT);
        item.item_right.setLayoutParams(lp2);
        item.item_left_txt.setText(product.pName);
        item.item_right_txt.setText("删除");
        item.tv_num_edit.setText("" + product.productNum);
        item.tv_num.setText("x"+product.productNum);
        item.tv_price.setText("¥ " + StringUtils.addComma(""+product.price));
        item.tv_price_edit.setText("¥ " + StringUtils.addComma("" + product.price * product.productNum));
        item.item_right.setOnClickListener(onClickListener);
        item.iv_product.setOnClickListener(onClickListener);
        item.cbx_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                product.isChecked = isChecked;
                listener.todo();
            }
        });
        item.tv_color.setText("颜色:"+product.color);
        item.tv_size.setText("尺码:"+product.size);
        if (product.priceOrigin > 0){
            //item.tv_price_origin.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
            item.tv_price_origin.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
            item.tv_price_origin.setText("¥ " + StringUtils.addComma(""+product.priceOrigin));
        }
        item.cbx_item.setChecked(product.isChecked);
        if (product.editable){
            item.ll_left.setVisibility(View.GONE);
            item.ll_left_edit.setVisibility(View.VISIBLE);
        }else{
            item.ll_left.setVisibility(View.VISIBLE);
            item.ll_left_edit.setVisibility(View.GONE);
        }
        item.ll_product_info.setOnClickListener(onClickListener);
        item.tv_minus.setOnClickListener(onClickListener);
        item.tv_plus.setOnClickListener(onClickListener);
        return convertView;
    }


    private class ViewHolder {
        View item_left;
        View item_right;
        TextView item_left_txt;
        TextView item_right_txt;
        ImageView iv_product;
        CheckBox cbx_item;
        LinearLayout ll_left_edit,ll_left,ll_product_info;
        TextView tv_price,tv_num,tv_color,tv_size,tv_price_origin;
        TextView tv_minus,tv_num_edit,tv_plus,tv_product_intro,tv_price_edit;
    }

    private PopupClickListener popupClickListener;

    public void setPopupClickListener(PopupClickListener listener) {
        this.popupClickListener = listener;
    }

    public interface PopupClickListener {
        void onClick();
    }
}

