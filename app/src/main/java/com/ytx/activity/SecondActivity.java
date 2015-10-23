package com.ytx.activity;

import android.app.FragmentManager;
import android.util.Log;

import com.ytx.R;
import com.ytx.app.FragmentType;
import com.ytx.fragment.CouponsFragment;
import com.ytx.fragment.PayFragment;
import com.ytx.fragment.TitleBarFragment;

/**
 * Created by Augustus on 15/10/17.
 */
public class SecondActivity extends TitleBarActivity {

    private int fragmentTyep;
    private TitleBarFragment currentFragment;
    private TitleBarFragment fragment;

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
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
        changeFragment(fragment);
    }

    public void changeFragment(TitleBarFragment targetFragment) {
        currentFragment = targetFragment;
        super.changeFragment(R.id.second_content, targetFragment);
    }

    public void replaceFragment(TitleBarFragment targetFragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.second_content, targetFragment)
                .addToBackStack(null).commit();
    }

    @Override
    protected void onBackClick() {
        super.onBackClick();
//        FragmentManager fragmentManager = getFragmentManager();
//        int count = fragmentManager.getBackStackEntryCount();
//        Log.e("msg", "count:" + count);
//        if(count > 1) {
//            fragmentManager.popBackStack();
//            return;
//        }
        currentFragment.onBackClick();
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
