/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ytx.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ytx.R;

import org.kymjs.kjframe.KJActivity;

/**
 * 应用Activity基类
 *
 * @author kymjs (https://www.kymjs.com/)
 * @since 2015-3
 */
public abstract class TitleBarActivity extends KJActivity {

    public ImageView mImgBack;
    public TextView mTvTitle;
    public ImageView mImgMenu;
    public RelativeLayout mRlTitleBar;
    public TextView mRightText;

    protected final Handler mMainLoopHandler = new Handler(
            Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        try {
            mRlTitleBar = (RelativeLayout) findViewById(R.id.titlebar);
            mImgBack = (ImageView) findViewById(R.id.titlebar_img_back);
            mTvTitle = (TextView) findViewById(R.id.titlebar_text_title);
            mImgMenu = (ImageView) findViewById(R.id.titlebar_img_menu);
            mRightText = (TextView) findViewById(R.id.titlebar_right_txt);
            mImgBack.setOnClickListener(this);
            mImgMenu.setOnClickListener(this);
        } catch (NullPointerException e) {
            throw new NullPointerException(
                    "TitleBar Notfound from Activity layout");
        }
        super.onStart();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.titlebar_img_back:
                onBackClick();
                break;
            case R.id.titlebar_img_menu:
                onMenuClick();
                break;
            case R.id.titlebar_right_txt:
                onRightTextclick();
                break;
            default:
                break;
        }
    }

    protected void onBackClick() {
    }

    protected void onMenuClick() {
    }

    protected void onRightTextclick() {

    }

    public void onCurtainPull() {
    }

    public void onCurtainPush() {
    }

}
