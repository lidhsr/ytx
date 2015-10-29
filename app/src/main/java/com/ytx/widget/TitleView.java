package com.ytx.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ytx.R;

import org.kymjs.kjframe.tools.ToolString;

/**
 * Created by Augustus on 15/10/29.
 */
public class TitleView extends LinearLayout {

    private TextView titlebar_text_title;
    private ImageView titlebar_img_back;
    private ImageView titlebar_img_menu;
    private TextView titlebar_right_txt;

    public TitleView(Context context) {
        super(context);
        init(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_titlebar, null);
        titlebar_text_title = (TextView) view.findViewById(R.id.titlebar_text_title);
        titlebar_right_txt = (TextView) view.findViewById(R.id.titlebar_right_txt);
        titlebar_img_back = (ImageView) view.findViewById(R.id.titlebar_img_back);
        titlebar_img_menu = (ImageView) view.findViewById(R.id.titlebar_img_menu);
        this.addView(view);
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

    public void setLeftClick(View.OnClickListener listener) {
        titlebar_img_back.setOnClickListener(listener);
    }

    public void setRightTextClick(View.OnClickListener listener) {
        titlebar_right_txt.setOnClickListener(listener);
    }

    public void setRightImageClick(View.OnClickListener listener) {
        titlebar_img_menu.setOnClickListener(listener);
    }


}
