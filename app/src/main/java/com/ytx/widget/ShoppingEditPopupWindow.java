package com.ytx.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.ytx.R;
import com.ytx.activity.HomeActivity;

import java.util.ArrayList;

/**
 * Created by Augustus on 15/10/20.
 */
public class ShoppingEditPopupWindow extends PopupWindow {

    private Activity activity;
    private View contentView;
    private LinearLayout popup_root;
    private ImageView image;
    private TextView name;
    private TextView price;
    private TableLayout table_size;
    private TableLayout table_color;
    private Button btn_ok;

    private ArrayList<TextView> sizeTextList = new ArrayList<>();
    private ArrayList<TextView> colorTextList = new ArrayList<>();
    private int cols = 4;
    private int sizeValue;
    private int colorValue;

    public ShoppingEditPopupWindow(final Activity context , final ButtonClick listener, ArrayList<String> sizeList, ArrayList<String> colorList) {
        this.activity = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.popupwindow_shoppingedit, null);
        popup_root = (LinearLayout) contentView.findViewById(R.id.popup_root);
        image = (ImageView) contentView.findViewById(R.id.image);
        name = (TextView) contentView.findViewById(R.id.name);
        price = (TextView) contentView.findViewById(R.id.price);
        table_size = (TableLayout) contentView.findViewById(R.id.table_size);
        table_color = (TableLayout) contentView.findViewById(R.id.table_color);
        btn_ok = (Button) contentView.findViewById(R.id.btn_ok);
        this.setContentView(contentView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        this.update();
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.AnimationPreview);

        setSizeTable(sizeList);
        setColorTable(colorList);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != listener) {
                    listener.clikResult(sizeValue, colorValue);
                    ShoppingEditPopupWindow.this.dismiss();
                }
            }
        });
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getName() {
        return name;
    }

    public TextView getPrice() {
        return price;
    }

    public void setSizeTable(ArrayList<String> list) {
        sizeTextList.clear();
        ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT
        );
        int position = 0;
        int col;
        int size = list.size();
        int rowNum = size / cols;
        if(size%cols > 0) {
            rowNum += 1;
        }

        for(int i=0; i<rowNum; i++) {
            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            );

            TableRow row = new TableRow(activity);
            row.setOrientation(TableLayout.HORIZONTAL);
            row.setLayoutParams(params);

            col = cols;
            if(i == rowNum - 1) {
                col = size % cols == 0 ? cols : size % cols;
            }
            for(int j=0; j<col; j++) {
                View v = LayoutInflater.from(activity).inflate(R.layout.popup_text, null);
                TextView textView = (TextView) v.findViewById(R.id.txt);
                textView.setText(list.get(position));
                textView.setOnClickListener(new SizeTextClick(position));
                TableRow.LayoutParams p = new TableRow.LayoutParams(HomeActivity.screenW / 4 - 20, ViewPager.LayoutParams.WRAP_CONTENT);
                row.addView(v, p);
                ++position;
                sizeTextList.add(textView);
            }
            table_size.addView(row, param);
        }
    }

    public void setColorTable(ArrayList<String> list) {
        ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT
        );
        int position = 0;
        int col;
        int size = list.size();
        int rowNum = size / cols;
        if(size%cols > 0) {
            rowNum += 1;
        }

        for(int i=0; i<rowNum; i++) {
            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            );

            TableRow row = new TableRow(activity);
            row.setOrientation(TableLayout.HORIZONTAL);
            row.setLayoutParams(params);

            col = cols;
            if(i == rowNum - 1) {
                col = size % cols == 0 ? cols : size % cols;
            }
            for(int j=0; j<col; j++) {
                View v = LayoutInflater.from(activity).inflate(R.layout.popup_text, null);
                TextView textView = (TextView) v.findViewById(R.id.txt);
                textView.setText(list.get(position));
                textView.setOnClickListener(new ColorTextClick(position));
                TableRow.LayoutParams p = new TableRow.LayoutParams(HomeActivity.screenW / 4 - 20, ViewPager.LayoutParams.WRAP_CONTENT);
                row.addView(v, p);
                ++position;
                colorTextList.add(textView);
            }
            table_color.addView(row, param);
        }
    }

    public void show(View parent, int y) {
        this.showAtLocation(parent, Gravity.BOTTOM, 0, y);
    }

    class SizeTextClick implements View.OnClickListener {

        private int position;

        public SizeTextClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            sizeValue = position;
            int size = sizeTextList.size();
            for(int i=0; i<size; i++) {
                TextView tv = sizeTextList.get(i);
                if(i == position) {
                    tv.setTextColor(activity.getResources().getColor(R.color.color_yellow_light));
                    tv.setBackgroundResource(R.drawable.shape_size_h);
                } else {
                    tv.setTextColor(activity.getResources().getColor(android.R.color.black));
                    tv.setBackgroundResource(R.drawable.shape_size_n);
                }
            }
        }
    }

    class ColorTextClick implements View.OnClickListener {

        private int position;

        public ColorTextClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            colorValue = position;
            int size = colorTextList.size();
            for(int i=0; i<size; i++) {
                TextView tv = colorTextList.get(i);
                tv.setTextColor(activity.getResources().getColor(android.R.color.black));
                tv.setBackgroundResource(R.drawable.shape_size_n);
                if(i == position) {
                    tv.setTextColor(activity.getResources().getColor(R.color.color_yellow_light));
                    tv.setBackgroundResource(R.drawable.shape_size_h);
                }
            }
        }
    }

    public interface ButtonClick {
        void clikResult(int sizeValue, int colorValue);
    }
}
