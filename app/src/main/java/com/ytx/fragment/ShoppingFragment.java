package com.ytx.fragment;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ytx.R;
import com.ytx.activity.HomeActivity;
import com.ytx.widget.ShoppingEditPopupWindow;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.pulltorefresh.PullToRefreshListView;
import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;

/**
 * Created by Augustus on 15/10/18.
 */
public class ShoppingFragment extends TitleBarFragment implements PullToRefreshBase.OnRefreshListener<ListView>, ShoppingEditPopupWindow.ButtonClick {

    private HomeActivity activity;
    @BindView(id = R.id.list)
    private PullToRefreshListView pullToRefreshListView;
    @BindView(id = R.id.shopping_root)
    private RelativeLayout shopping_root;
    @BindView(id = R.id.empty_layout)
    private LinearLayout empty_layout;
    @BindView(id = R.id.btn_go, click = true)
    private Button btn_go;

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

    private void setTitleBar(ActionBarRes actionBarRes) {
        if(null != actionBarRes) {
            actionBarRes.title = getString(R.string.bottombar_content3);
            actionBarRes.right_txt = "编辑";
        } else {
            setTitle(getString(R.string.bottombar_content3));
            setRightText("编辑");
        }
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch(v.getId()) {
            case R.id.btn_go:
                activity.changeFragment(activity.contentFragment1);
                break;
        }
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        btn_go.setWidth(HomeActivity.screenW / 2);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pullToRefreshListView.setOnRefreshListener(this);
        pullToRefreshListView.setEmptyView(empty_layout);
    }

    @Override
    public void onRightTxtClick() {
        super.onRightTxtClick();
        isEdit = !isEdit;
        setRightText(isEdit ? "保存" : "编辑");
        handler.sendEmptyMessage(0);
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

    }


    @Override
    public void clikResult(int sizeValue, int colorValue) {
        Log.e("msg", "size:" + sizeValue + ", color:" + colorValue);
    }
}
