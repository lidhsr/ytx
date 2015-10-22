package com.ytx.fragment;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ytx.R;
import com.ytx.activity.HomeActivity;
import com.ytx.adapter.CartAdapter;
import com.ytx.adapter.SwipeAdapter;
import com.ytx.data.ActivityInfo;
import com.ytx.data.Product;
import com.ytx.data.Shop;
import com.ytx.widget.ShoppingEditPopupWindow;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.pulltorefresh.PullToRefreshListView;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.StringUtils;

import java.util.ArrayList;

/**
 * Created by Augustus on 15/10/18.
 */
public class ShoppingFragment extends TitleBarFragment implements PullToRefreshBase.OnRefreshListener<ListView>,
        ShoppingEditPopupWindow.PopupClick, SwipeAdapter.PopupClickListener {

    private HomeActivity activity;
    @BindView(id = R.id.list)
    private PullToRefreshListView pullToRefreshListView;
    @BindView(id = R.id.shopping_root)
    private LinearLayout shopping_root;
    @BindView(id = R.id.empty_layout)
    private LinearLayout empty_layout;
    @BindView(id = R.id.btn_go, click = true)
    private Button btn_go;
    @BindView(id = R.id.recommend_01, click = true)
    private LinearLayout recommend_01;
    @BindView(id = R.id.recommend_02, click = true)
    private LinearLayout recommend_02;
    @BindView(id = R.id.recommend_03, click = true)
    private LinearLayout recommend_03;

    private ArrayList<Shop> mData = new ArrayList<Shop>();
    private CartAdapter cartAdapter;
    @BindView(id = R.id.tv_operate, click = true)
    private TextView tv_operate;
    @BindView(id = R.id.tv_move, click = true)
    private TextView tv_move;
    @BindView(id = R.id.cbx_all, click = true)
    private CheckBox cbx_all;
    @BindView(id = R.id.ll_price)
    private LinearLayout ll_price;
    @BindView(id = R.id.tv_total_price)
    private TextView tv_total_price;
    @BindView(id = R.id.rl_bottom)
    private RelativeLayout rl_bottom;

    private boolean isEdit = false;
    private ShoppingEditPopupWindow shoppingEditPopupWindow;

    android.os.Handler handler = new android.os.Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            ArrayList<String> size = new ArrayList<>();
            size.add("X");
            size.add("M");
            size.add("L");
            size.add("XL");
            size.add("XXL");

            ArrayList<String> color = new ArrayList<>();
            color.add("白色");
            color.add("黑色");
            color.add("红色");

            shoppingEditPopupWindow = new ShoppingEditPopupWindow(activity, ShoppingFragment.this, size, color);
            shoppingEditPopupWindow.getName().setText("标题");
            shoppingEditPopupWindow.getPrice().setText("¥1,800");
            shoppingEditPopupWindow.show(shopping_root, activity.height);
        }
    };

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        activity = (HomeActivity) getActivity();
        return View.inflate(activity, R.layout.fragment_shopping, null);
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

    @Override
    protected void initData() {
        super.initData();

    }

    private void changeBottom() {
        int size = mData.size();
        rl_bottom.setVisibility(size == 0 ? View.GONE : View.VISIBLE);
    }

    private void setTitleBar(ActionBarRes actionBarRes) {
        if (null != actionBarRes) {
            actionBarRes.title = getString(R.string.bottombar_content3);
            actionBarRes.right_txt = "编辑";
        } else {
            setTitle(getString(R.string.bottombar_content3));
            setRightText(isEdit ? "保存" : "编辑");
        }
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.btn_go:
                activity.changeFragment(activity.contentFragment1);
                break;
            case R.id.cbx_all:
                for (Shop shop : mData) {
                    for (Product p : shop.products) {
                        p.isChecked = ((CheckBox) v).isChecked();
                    }
                }
                cartAdapter.notifyDataSetChanged();
                break;
            case R.id.recommend_01:

                break;
            case R.id.recommend_02:

                break;
            case R.id.recommend_03:

                break;
        }
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        btn_go.setWidth(HomeActivity.screenW / 2);

        for (int i = 0; i < 3; i++) {
            Shop shop = new Shop();
            shop.name = "商家" + i;
            ArrayList<Product> list = new ArrayList<Product>();
            for (int t = 0; t < 2; t++) {
                Product product = new Product();
                product.pName = shop.name + "的产品" + t;
                product.price = 1;
                product.priceOrigin = 2;
                product.productNum = 1;
                product.color = "白色";
                product.size = "MS";
                list.add(product);
            }
            for (int t = 0;t < 2;t++){
                ActivityInfo info = new ActivityInfo();
                info.content = "活动商品已满足［满500元换购］活动" + t;
                shop.activityInfo.add(info);
            }
            shop.coupon = i;
            tv_total_price.setText("¥ 0.0");
            shop.products.addAll(list);
            mData.add(shop);
        }
        ListView listView = pullToRefreshListView.getRefreshableView();
        cartAdapter = new CartAdapter(listView, mData, R.layout.item_cart_main, new SortFragment.AfterSelectedListener() {

            @Override
            public void todo() {
                int count = 0, total = 0;
                double totalPrice = 0;
                for (Shop shop : mData) {
                    for (Product p : shop.products) {
                        total++;
                        if (p.isChecked) {
                            count++;
                            totalPrice += p.price * p.productNum;
                        }
                    }
                }
                if (count == total) {
                    cbx_all.setChecked(true);
                } else {
                    cbx_all.setChecked(false);
                }
                tv_total_price.setText("¥ " + StringUtils.addComma("" + totalPrice));
                cartAdapter.notifyDataSetChanged();
            }
        });
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pullToRefreshListView.setOnRefreshListener(this);
        listView.setAdapter(cartAdapter);
        pullToRefreshListView.setEmptyView(empty_layout);
        cartAdapter.setPopupClickListener(this);
        changeBottom();
    }

    @Override
    public void onRightTxtClick() {
        super.onRightTxtClick();
        isEdit = !isEdit;
        setRightText(isEdit ? "保存" : "编辑");

        if (isEdit) {
            ll_price.setVisibility(View.GONE);
            tv_move.setVisibility(View.VISIBLE);
            tv_operate.setText("删除");
        } else {
            ll_price.setVisibility(View.VISIBLE);
            tv_move.setVisibility(View.GONE);
            tv_operate.setText("结算");
        }
        for (Shop shop : mData) {
            for (Product p : shop.products) {
                p.editable = isEdit;
            }
        }
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

    }

    @Override
    public void clikResult(int sizeValue, int colorValue) {
        Log.e("msg", "size:" + sizeValue + ", color:" + colorValue);
    }

    @Override
    public void onClick() {
        handler.sendEmptyMessage(0);
    }
}
