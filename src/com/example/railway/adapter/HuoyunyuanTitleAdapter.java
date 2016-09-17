package com.example.railway.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.example.railway.base.BaseCommenAdapter;

import java.util.List;

/**
 * Title:
 * Description:
 * Company:
 *
 * @author qianchao
 * @date 2016-9-722:17
 */
public class HuoyunyuanTitleAdapter extends BaseCommenAdapter<String,HuoyunyuanTitleHolder> {
    /**
     * @param context
     * @param lists
     */
    public HuoyunyuanTitleAdapter(Context context, List<String> lists) {
        super(context, lists);
    }

    @Override
    protected HuoyunyuanTitleHolder getHolder() {
        return new HuoyunyuanTitleHolder();
    }

    @Override
    protected void initViewByHolder(HuoyunyuanTitleHolder holder, View convertView) {

    }

    @Override
    protected void initData(HuoyunyuanTitleHolder holder, String data, int position) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
class HuoyunyuanTitleHolder{
    TextView title;
}