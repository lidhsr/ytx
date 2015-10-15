package org.kymjs.kjframe.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 解决SrollView中嵌套Listview数据显示不全问题
 * @author 曾繁添
 * @version 1.0
 *
 */
public class SListView extends ListView {
	public SListView(Context context) {
		super(context);
	}

	public SListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);

		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
