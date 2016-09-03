package com.example.railway.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.example.railway.R;
import com.example.railway.base.BaseCommenAdapter;
import com.example.railway.bean.HuoYunYuan;

import java.util.List;

/**
 * Title:
 * Description:
 * Company:
 *
 * @author qianchao
 * @date 2016-8-289:55
 */
public class SeeAllAdapter extends BaseCommenAdapter<String, SeeAllHolder> {
    /**
     * @param context
     * @param lists
     */
    public SeeAllAdapter(Context context, List<String> lists) {
        super(context, lists);
    }

    @Override
    protected SeeAllHolder getHolder() {
        return new SeeAllHolder();
    }

    @Override
    protected void initViewByHolder(SeeAllHolder holder, View convertView) {
        holder.textView = (TextView) convertView.findViewById(R.id.textView);
    }

    @Override
    protected void initData(SeeAllHolder holder, String data, int position) {
        holder.textView.setText(data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.all_item;
    }
}

class SeeAllHolder {
    TextView textView;
}