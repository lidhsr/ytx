package com.ytx.activity;

import android.util.DisplayMetrics;
import android.util.Log;
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
import com.ytx.fragment.TitleBarFragment;

import org.kymjs.kjframe.tools.ToastUtils;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.KJActivityStack;

/**
 * Created by Augustus on 15/10/17.
 */
public class HomeActivity extends TitleBarActivity {

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

    private TitleBarFragment contentFragment1;
    private TitleBarFragment contentFragment2;
    private TitleBarFragment contentFragment3;
    private TitleBarFragment contentFragment4;
    private TitleBarFragment contentFragment5;
    private TitleBarFragment currentFragment;

    private boolean isOnKeyBacking;
    public int height;

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

    @Override
    protected void onBackClick() {
        super.onBackClick();
        currentFragment.onBackClick();
    }

    @Override
    protected void onMenuClick() {
        super.onMenuClick();
        currentFragment.onMenuClick();
    }

    @Override
    protected void onRightTextClick() {
        super.onRightTextClick();
        currentFragment.onRightTxtClick();
    }

    public void changeFragment(TitleBarFragment targetFragment) {
        currentFragment = targetFragment;
        super.changeFragment(R.id.main_content, targetFragment);
    }

    @Override
    public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isOnKeyBacking) {
                mMainLoopHandler.removeCallbacks(onBackTimeRunnable);
                isOnKeyBacking = false;
                // UIHelper.toHome(aty);
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
