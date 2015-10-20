package com.ytx.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.ytx.R;

/**
 * Created by Augustus on 15/10/20.
 */
public class ShoppingEditPopupWindow extends PopupWindow {

    private View conentView;
    private LinearLayout popup_root;

    public ShoppingEditPopupWindow(final Activity context , View.OnClickListener listener) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popupwindow_shoppingedit, null);
        popup_root = (LinearLayout) conentView.findViewById(R.id.popup_root);
        this.setContentView(conentView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setTouchable(true);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        ColorDrawable dw = new ColorDrawable(0000000000);
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.AnimationPreview);
        popup_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingEditPopupWindow.this.dismiss();
            }
        });
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    ShoppingEditPopupWindow.this.dismiss();
                    return true;
                }
                return false;
            }

        });
    }

    interface ButtonClick {

    }
}
