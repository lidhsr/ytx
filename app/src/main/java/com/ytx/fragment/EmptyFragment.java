package com.ytx.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ytx.R;
import com.ytx.activity.SecondActivity;
import com.ytx.adapter.AddressAdapter;
import com.ytx.data.AddressInfo;
import com.ytx.listener.OnClickListener;
import com.ytx.widget.DialogTools;
import com.ytx.widget.TitleView;

import org.kymjs.kjframe.pulltorefresh.PullToRefreshBase;
import org.kymjs.kjframe.pulltorefresh.PullToRefreshListView;
import org.kymjs.kjframe.tools.ToastUtils;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Augustus on 15/10/18.
 */
public class EmptyFragment extends SupportFragment {

    @BindView(id = R.id.list)
    private PullToRefreshListView pullToRefreshListView;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return View.inflate(getActivity(), R.layout.list, null);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.DISABLED);
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
