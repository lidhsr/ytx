package com.ytx.activity;

import com.ytx.R;
import com.ytx.app.FragmentType;
import com.ytx.fragment.CouponsFragment;
import com.ytx.fragment.TitleBarFragment;

/**
 * Created by Augustus on 15/10/17.
 */
public class SecondActivity extends TitleBarActivity {

    private int fragmentTyep;
    private TitleBarFragment currentFragment;
    private CouponsFragment couponsFragment;

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
                couponsFragment = new CouponsFragment();
                break;
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
        switch (fragmentTyep) {
            case FragmentType.COUPONS_FRAGMENT:
                changeFragment(couponsFragment);
                break;
        }
    }

    public void changeFragment(TitleBarFragment targetFragment) {
        currentFragment = targetFragment;
        super.changeFragment(R.id.second_content, targetFragment);
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
