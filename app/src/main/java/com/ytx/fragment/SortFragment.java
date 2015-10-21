package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ytx.R;
import com.ytx.activity.HomeActivity;
import com.ytx.adapter.CartAdapter;
import com.ytx.data.Product;
import com.ytx.data.Shop;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import org.kymjs.kjframe.pulltorefresh.PullToRefreshListView;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.StringUtils;

import java.util.ArrayList;

/**
 * Created by Augustus on 15/10/18.
 */
public class SortFragment extends TitleBarFragment implements OnRefreshListener<ListView>{

    private HomeActivity activity;
    @BindView(id = R.id.list)
    private PullToRefreshListView pullToRefreshListView;
    private ArrayList<Shop> mData = new ArrayList<Shop>();
    private CartAdapter cartAdapter;
    @BindView(id = R.id.tv_operate,click = true)
    private TextView tv_operate;
    @BindView(id = R.id.tv_move,click = true)
    private TextView tv_move;
    @BindView(id = R.id.cbx_all,click = true)
    private CheckBox cbx_all;
    @BindView(id = R.id.ll_price)
    private LinearLayout ll_price;
    @BindView(id = R.id.tv_total_price)
    private TextView tv_total_price;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (HomeActivity) getActivity();
        return View.inflate(activity, R.layout.fragment_sort, null);
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        super.setActionBarRes(actionBarRes);
        setTitleBar(actionBarRes);
    }

    @Override
    public void onChange() {
        super.onChange();
        setTitleBar(null);
    }

    private void setTitleBar(ActionBarRes actionBarRes) {
        if(null != actionBarRes) {
            actionBarRes.title = getString(R.string.bottombar_content2);
            actionBarRes.right_txt = "Edit";
        } else {
            setTitle(getString(R.string.bottombar_content2));
            setRightText("Edit");
        }

    }

    @Override
    public void onRightTxtClick() {
        if (getRightText().getText().toString().equals("Edit")){
            setRightText("Save");
            ll_price.setVisibility(View.GONE);
            tv_move.setVisibility(View.VISIBLE);
            tv_operate.setText("删除");
        }else{
            setRightText("Edit");
            ll_price.setVisibility(View.VISIBLE);
            tv_move.setVisibility(View.GONE);
            tv_operate.setText("结算");
        }
        for (Shop shop : mData) {
            for (Product p : shop.pList){
                p.editable = getRightText().getText().toString().equals("Save");
            }
        }
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        for (int i = 0;i < 3;i++){
            Shop shop = new Shop();
            shop.name = "商家" + i;
            ArrayList<Product> list = new ArrayList<Product>();
            for (int t = 0;t < 2;t++){
                Product product = new Product();
                product.pName = shop.name + "的产品" + t;
                product.price = 1;
                list.add(product);
            }
            tv_total_price.setText("¥ 0.0");
            shop.pList.addAll(list);
            mData.add(shop);
        }
        ListView listView = pullToRefreshListView.getRefreshableView();
        cartAdapter = new CartAdapter(listView, mData, R.layout.item_cart_main, new AfterSelectedListener() {
            @Override
            public void todo() {
                int count = 0,total = 0;
                double totalPrice = 0;
                for (Shop shop : mData) {
                    for (Product p : shop.pList){
                        total++;
                        if (p.isChecked){
                            count++;
                            totalPrice += p.price * p.productNum;
                        }
                    }
                }
                if (count == total){
                    cbx_all.setChecked(true);
                }else{
                    cbx_all.setChecked(false);
                }
                tv_total_price.setText("¥ " + StringUtils.addComma("" + totalPrice));
                cartAdapter.notifyDataSetChanged();
            }
        });
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pullToRefreshListView.setOnRefreshListener(this);
        listView.setAdapter(cartAdapter);

    }

    @Override
    protected void widgetClick(View v) {
        switch (v.getId()){
            case R.id.cbx_all:
                for (Shop shop : mData) {
                    for (Product p : shop.pList){
                        p.isChecked = ((CheckBox) v).isChecked();
                    }
                }
                cartAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

    }


    public interface AfterSelectedListener {
        public void todo();
    }

}
