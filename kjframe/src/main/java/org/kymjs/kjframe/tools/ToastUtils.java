package org.kymjs.kjframe.tools;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toast工具, 解决Toast延迟弹出问题.
 * 
 * @date 2012-6-2
 */
public class ToastUtils {

	private static Handler handler = new Handler(Looper.getMainLooper());

	private static Toast toast = null;

	public static void showMessage(final Context context, final String msg) {
		showMessage(context, msg, Toast.LENGTH_SHORT);
	}

	public static void showMessage(final Context context, final String msg,
			final int len) {
		new Thread(new Runnable() {
			public void run() {
				handler.post(new Runnable() {

					@Override
					public void run() {
						synchronized (this) {
							if (toast != null) {
								// toast.cancel();
								toast.setText(msg);
								toast.setDuration(len);
								toast.setGravity(Gravity.BOTTOM, 0, 15);
							} else {
								toast = Toast.makeText(context, msg, len);
								toast.setGravity(Gravity.BOTTOM, 0, 15);
							}
							toast.show();
						}
					}
				});
			}
		}).start();
	}

	public static void showMessage(final Context context, final int msg) {
		showMessage(context, msg, Toast.LENGTH_SHORT);
	}

	public static void showMessage(final Context context, final int msg,
			final int len) {
		new Thread(new Runnable() {
			public void run() {
				handler.post(new Runnable() {
					@Override
					public void run() {
						synchronized (this) {
							if (toast != null) {
//								toast.cancel();
								toast.setText(msg);
								toast.setDuration(len);
								toast.setGravity(Gravity.BOTTOM, 0, 15);
							} else {
								toast = Toast.makeText(context, msg, len);
								toast.setGravity(Gravity.BOTTOM, 0, 15);
							}
							toast.show();
						}
					}
				});
			}
		}).start();
	}

}
