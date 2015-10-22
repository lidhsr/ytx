package com.ytx.activity;

import com.ytx.R;
import com.ytx.app.FragmentType;
import com.ytx.fragment.CouponsFragment;
import com.ytx.fragment.TitleBarFragment;

/**
 * Created by Augustus on 15/10/17.
 */
public class SecondActivity extends TitleBarActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_second);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    @Override
    protected void onBackClick() {
        super.onBackClick();
        this.finish();
    }

    @Override
    protected void onMenuClick() {
        super.onMenuClick();
    }

    @Override
    protected void onRightTextClick() {
        super.onRightTextClick();
    }

}
