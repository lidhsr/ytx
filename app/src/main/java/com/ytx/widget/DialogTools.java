package com.ytx.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ytx.R;

import org.kymjs.kjframe.tools.ToolString;

public class DialogTools {

	public static void showDialog(Context context, String title,
			String message, OnClickListener okListener) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("确定", okListener);
		builder.setNegativeButton("取消", null);
		builder.setCancelable(false);
		builder.create().show();

	}

	public static void showDialog(Context context, String title,
			String message, OnClickListener okListener,
			OnClickListener cancelListener) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("确定", okListener);
		builder.setNegativeButton("取消", cancelListener);
		builder.setCancelable(false);
		builder.create().show();

	}

	public static void showSingleDialog(Context context, String title,
			String message, OnClickListener okListener) {
		AlertDialog.Builder builder = new Builder(context);
		if (!ToolString.isNoBlankAndNoNull(title)) {
			builder.setTitle(title);
		}
		builder.setMessage(message);
		builder.setPositiveButton("确定", okListener);
		builder.setCancelable(false);
		builder.create().show();

	}

	public static AlertDialog showCustomDialog(Context context, String title,
										String message,View.OnClickListener listener){
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		alertDialog.setCanceledOnTouchOutside(false);
		Window window = alertDialog.getWindow();
		window.setContentView(R.layout.dialog_main);
		WindowManager wm = ((Activity)context).getWindowManager();
		Display display = wm.getDefaultDisplay();
		WindowManager.LayoutParams  lp = window.getAttributes();
		lp.width = display.getWidth() - 200;
		lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
		window.setAttributes(lp);
		TextView tv_title = (TextView) window.findViewById(R.id.tv_title);
		tv_title.setText(title);
		TextView tv_message = (TextView) window.findViewById(R.id.tv_message);
		tv_message.setText(message);
		window.findViewById(R.id.tv_ok).setOnClickListener(listener);
		window.findViewById(R.id.tv_no).setOnClickListener(listener);
		return alertDialog;
	}

}
