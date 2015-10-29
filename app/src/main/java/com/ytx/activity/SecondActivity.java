package com.ytx.activity;

import android.app.FragmentManager;
import android.view.KeyEvent;

import com.ytx.R;
import com.ytx.app.FragmentType;
import com.ytx.fragment.CouponsFragment;
import com.ytx.fragment.OrderConfirmFragment;
import com.ytx.fragment.PayFragment;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.SupportFragment;

/**
 * Created by Augustus on 15/10/17.
 */
public class SecondActivity extends KJActivity {

    private int fragmentTyep;
    private SupportFragment fragment;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_second);
    }

    @Override
    public void initData() {
        super.initData();
        fragmentTyep = getIntent().getExtras().getInt(FragmentType.FRAGMENT_TYPE);
        switch (fragmentTyep) {
            case FragmentType.COUPONS_FRAGMENT:
                fragment = new CouponsFragment();
                break;
            case FragmentType.PAY_FRAGMENT:
                fragment = new PayFragment();
                break;
            case FragmentType.ORDER_CONFIRM_FRAGMENT:
                fragment = new OrderConfirmFragment();
                break;
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
        changeFragment(fragment);
    }

    public void changeFragment(SupportFragment targetFragment) {
        super.changeFragment(R.id.second_content, targetFragment);
    }

    public void changeFragment(SupportFragment targetFragment, boolean isBack) {
        super.changeFragment(R.id.second_content, targetFragment, isBack);
    }

    @Override
    public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            FragmentManager fragmentManager = getFragmentManager();
            int count = fragmentManager.getBackStackEntryCount();
            if(count > 0) {
                fragmentManager.popBackStack();
            } else {
                this.finish();
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
