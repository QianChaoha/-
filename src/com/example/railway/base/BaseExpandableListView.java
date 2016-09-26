package com.example.railway.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

import com.example.railway.R;

/**
 * Created by Administrator on 2016-9-25.
 */
public class BaseExpandableListView extends ExpandableListView {
    public BaseExpandableListView(Context context) {
        super(context);
    }

    public BaseExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private void init() {
//		setSelector  点击显示的颜色
//		setCacheColorHint  拖拽的颜色
//		setDivider  每个条目的间隔	的分割线
        this.setSelector(R.drawable.nothing);
        this.setCacheColorHint(R.drawable.nothing);
        this.setDivider(getContext().getResources().getDrawable(R.drawable.nothing));
    }
}
