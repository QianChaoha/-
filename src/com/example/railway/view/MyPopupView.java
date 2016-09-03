package com.example.railway.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.railway.uitl.ScreenUtil;

/**
 * Title:
 * Description:
 * Company:
 *
 * @author qianchao
 * @date 2016-8-2720:15
 */
public class MyPopupView extends PopupWindow {
    private Context mContext;

    public MyPopupView(Context mContext,View contentView){
        this.mContext = mContext;
        LinearLayout layout = new LinearLayout(mContext);
        layout.setBackgroundColor(Color.GRAY);
        contentView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtil.dip2px(mContext,200)));
        layout.addView(contentView,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtil.dip2px(mContext,200)));
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setFocusable(true);
        setContentView(layout);
    }
    public void showPopUp(View v) {
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        this.showAtLocation(v, Gravity.NO_GRAVITY, location[0], location[1] - this.getContentView().getMeasuredHeight());
    }
}
