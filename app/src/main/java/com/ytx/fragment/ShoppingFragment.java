package com.ytx.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ytx.R;
import com.ytx.activity.HomeActivity;
import com.ytx.widget.ShoppingEditPopupWindow;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.pulltorefresh.PullToRefreshListView;
import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Augustus on 15/10/18.
 */
public class ShoppingFragment extends TitleBarFragment implements PullToRefreshBase.OnRefreshListener<ListView> {

    private HomeActivity activity;
    @BindView(id = R.id.list)
    private PullToRefreshListView pullToRefreshListView;
    @BindView(id = R.id.shopping_root)
    private RelativeLayout shopping_root;
    private boolean isEdit = false;
    private ShoppingEditPopupWindow shoppingEditPopupWindow;

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
        shoppingEditPopupWindow = new ShoppingEditPopupWindow(activity, null);
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
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pullToRefreshListView.setOnRefreshListener(this);

    }

    @Override
    public void onRightTxtClick() {
        super.onRightTxtClick();
        isEdit = !isEdit;
        setRightText(isEdit ? "保存" : "编辑");
        shoppingEditPopupWindow.showAtLocation(shopping_root, Gravity.CENTER, 0, 0);

    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

    }


}
