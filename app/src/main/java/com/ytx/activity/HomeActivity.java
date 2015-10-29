package com.ytx.activity;

import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ytx.R;
import com.ytx.fragment.MainFragment;
import com.ytx.fragment.MyFragment;
import com.ytx.fragment.SearchFragment;
import com.ytx.fragment.ShoppingFragment;
import com.ytx.fragment.SortFragment;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.tools.ToastUtils;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.KJActivityStack;
import org.kymjs.kjframe.ui.KJFragment;
import org.kymjs.kjframe.ui.SupportFragment;

/**
 * Created by Augustus on 15/10/17.
 */
public class HomeActivity extends KJActivity {

    @BindView(id = R.id.bottombar_group)
    private RadioGroup bottombar_group;
    @BindView(id = R.id.bottombar_content1, click = true)
    private RadioButton mRbtnContent1;
    @BindView(id = R.id.bottombar_content2, click = true)
    private RadioButton mRbtnContent2;
    @BindView(id = R.id.bottombar_content3, click = true)
    private RadioButton mRbtnContent3;
    @BindView(id = R.id.bottombar_content4, click = true)
    private RadioButton mRbtnContent4;
    @BindView(id = R.id.bottombar_content5, click = true)
    private RadioButton mRbtnContent5;


    public SupportFragment contentFragment1;
    private SupportFragment contentFragment2;
    private SupportFragment contentFragment3;
    private SupportFragment contentFragment4;
    private SupportFragment contentFragment5;
    private SupportFragment currentFragment;

    private boolean isOnKeyBacking;
    public static int height;

    public static int screenW;
    public static int screenH;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_home);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenW = dm.widthPixels;// 获取屏幕分辨率宽度
        screenH = dm.heightPixels;
    }

    @Override
    public void initData() {
        super.initData();
        contentFragment1 = new MainFragment();
        contentFragment2 = new SortFragment();
        contentFragment3 = new ShoppingFragment();
        contentFragment4 = new SearchFragment();
        contentFragment5 = new MyFragment();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        changeFragment(contentFragment1);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        height = bottombar_group.getHeight();
        switch(v.getId()) {
            case R.id.bottombar_content1:
                changeFragment(contentFragment1);
                break;
            case R.id.bottombar_content2:
                changeFragment(contentFragment2);
                break;
            case R.id.bottombar_content3:
                changeFragment(contentFragment3);
                break;
            case R.id.bottombar_content4:
                changeFragment(contentFragment4);
                break;
            case R.id.bottombar_content5:
                changeFragment(contentFragment5);
                break;
        }
    }

    public void changeFragment(SupportFragment targetFragment) {
        if(null != currentFragment && currentFragment.equals(targetFragment)) {
            return;
        }
        currentFragment = targetFragment;
        changeBottomChecked(targetFragment);
        super.changeFragment(R.id.main_content, targetFragment);
    }

    private void changeBottomChecked(SupportFragment targetFragment) {
        if(targetFragment instanceof MainFragment) {
            mRbtnContent1.setChecked(true);
        } else if(targetFragment instanceof SortFragment) {
            mRbtnContent2.setChecked(true);
        } else if(targetFragment instanceof ShoppingFragment) {
            mRbtnContent3.setChecked(true);
        } else if(targetFragment instanceof SearchFragment) {
            mRbtnContent4.setChecked(true);
        } else if(targetFragment instanceof MyFragment) {
            mRbtnContent5.setChecked(true);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isOnKeyBacking) {
                mMainLoopHandler.removeCallbacks(onBackTimeRunnable);
                isOnKeyBacking = false;
                KJActivityStack.create().AppExit(aty);
            } else {
                isOnKeyBacking = true;
                ToastUtils.showMessage(this, "再点击一次退出");
                mMainLoopHandler.postDelayed(onBackTimeRunnable, 2000);
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    private final Runnable onBackTimeRunnable = new Runnable() {
        @Override
        public void run() {
            isOnKeyBacking = false;
        }
    };
}
