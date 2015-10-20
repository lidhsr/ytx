package com.ytx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ytx.R;
import com.ytx.activity.HomeActivity;
import com.ytx.adapter.CartAdapter;
import com.ytx.adapter.SwipeAdapter;
import com.ytx.data.Product;
import com.ytx.data.Shop;
import com.ytx.widget.SwipeListView;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.pulltorefresh.PullToRefreshListView;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Augustus on 15/10/18.
 */
public class SortFragment extends TitleBarFragment implements PullToRefreshBase.OnRefreshListener<ListView> {

    private HomeActivity activity;
    @BindView(id = R.id.list)
    private PullToRefreshListView pullToRefreshListView;
    private ArrayList<Shop> mData = new ArrayList<Shop>();
    private CartAdapter cartAdapter;

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
            actionBarRes.right_txt = "";
        } else {
            setTitle(getString(R.string.bottombar_content2));
            setRightText("");
        }

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
                list.add(product);
            }
            shop.pList.addAll(list);
            mData.add(shop);
        }
        ListView listView = pullToRefreshListView.getRefreshableView();
        cartAdapter = new CartAdapter(listView,mData,R.layout.item_cart_main);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pullToRefreshListView.setOnRefreshListener(this);
        listView.setAdapter(cartAdapter);

    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

    }

}
