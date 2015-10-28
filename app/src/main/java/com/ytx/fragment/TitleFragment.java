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
package com.ytx.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytx.R;

import org.kymjs.kjframe.tools.ToolString;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.KJFragment;

/**
 * 
 * 具有ActionBar的Activity的基类
 * 
 * @author kymjs (http://www.kymjs.com/)
 * 
 */
public class TitleFragment extends KJFragment {

    @BindView(id = R.id.titlebar_text_title)
    private TextView titlebar_text_title;
    @BindView(id = R.id.titlebar_img_back, click = true)
    private ImageView titlebar_img_back;
    @BindView(id = R.id.titlebar_img_menu, click = true)
    private ImageView titlebar_img_menu;
    @BindView(id = R.id.titlebar_right_txt, click = true)
    private TextView titlebar_right_txt;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.main_titlebar, container, false);
    }

    public void setTitleBar(Drawable leftDrawable, String title) {
        setTitleBar(leftDrawable, title, null, null);
    }

    public void setTitleBar(Drawable leftDrawable, String title, String rightText) {
        setTitleBar(leftDrawable, title, null, rightText);
    }

    public void setTitleBar(Drawable leftDrawable, String title, Drawable rightDrawable) {
        setTitleBar(leftDrawable, title, rightDrawable, null);
    }

    public void setTitleBar(Drawable leftDrawable, String title, Drawable rightDrawable, String rightText) {
        if(null != leftDrawable) {
            setLeftImage(leftDrawable);
        }

        if(!ToolString.isNoBlankAndNoNull(title)) {
            setTitleText(title);
        }

        if(null != rightDrawable) {
            setRightImage(leftDrawable);
        }

        if(!ToolString.isNoBlankAndNoNull(rightText)) {
            setRightText(rightText);
        }
    }

    public void setTitleText(String title) {
        titlebar_text_title.setText(title);
    }

    public void setLeftImage(Drawable drawable) {
        titlebar_img_back.setImageDrawable(drawable);
    }

    public void setRightImage(Drawable drawable) {
        titlebar_img_menu.setImageDrawable(drawable);
    }

    public void setRightText(String text) {
        titlebar_right_txt.setText(text);
    }
}
