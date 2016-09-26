package com.example.railway.adapter;

import android.content.Context;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.railway.R;
import com.example.railway.base.BaseCommenAdapter;
import com.example.railway.base.BaseCommenExpandableListAdapter;
import com.example.railway.base.BaseListView;
import com.example.railway.bean.HyyTitle1;
import com.example.railway.bean.HyyTitle2;

import java.util.List;

/**
 * Title:
 * Description:
 * Company:
 *
 * @author qianchao
 * @date 2016-9-722:17
 */
public class HuoyunyuanTitleAdapter extends BaseCommenExpandableListAdapter<HyyTitle1, HuoyunyuanTitleHolder, HyyTitle2, HuoyunyuanContentHolder> {
    /**
     * @param context
     * @param listGroup
     */
    public HuoyunyuanTitleAdapter(Context context, List<HyyTitle1> listGroup) {
        super(context, listGroup);
    }

    @Override
    protected List<HyyTitle2> getChildren(HyyTitle1 hyyTitle1, int groupPosition) {
        return null;
    }

    @Override
    protected void initGroupData(HuoyunyuanTitleHolder holder, HyyTitle1 data, int groupPosition, boolean isExpanded) {
        holder.title.setText(data.mtitle1);
        if (isExpanded){
            holder.imageView.setImageResource(R.drawable.gray_arrow_bottom);
        }else {
            holder.imageView.setImageResource(R.drawable.gray_arrow_right);
        }
    }

    @Override
    protected void initChildData(HuoyunyuanContentHolder holder, HyyTitle1 groupData, HyyTitle2 childData, int groupPosition, int childPosition, boolean isLastChild) {
        holder.title.setText(childData.title2);
    }

    @Override
    protected HuoyunyuanTitleHolder getGroupHolder() {
        return new HuoyunyuanTitleHolder();
    }

    @Override
    protected HuoyunyuanContentHolder getChildHolder() {
        return new HuoyunyuanContentHolder();
    }

    @Override
    protected void initViewByGroupHolder(HuoyunyuanTitleHolder holder, View convertView) {
        holder.title = findGroupTvId(R.id.tv);
        holder.imageView= (ImageView) convertView.findViewById(R.id.iv);
    }

    @Override
    protected void initViewByChildHolder(HuoyunyuanContentHolder holder, View convertView) {
        holder.listView = (BaseListView) convertView.findViewById(R.id.listView);
        holder.title = findChildTvId(R.id.tv);
    }

    @Override
    protected int getGroupLayoutId() {
        return R.layout.hyy_group_item_layout;
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.hyy_child_item_layout;
    }
    /**
     * @param context
     * @param lists
     */
//    public HuoyunyuanTitleAdapter(Context context, List<HyyTitle1> lists) {
//        super(context, lists);
//    }
//
//    @Override
//    protected HuoyunyuanTitleHolder getHolder() {
//        return new HuoyunyuanTitleHolder();
//    }
//
//    @Override
//    protected void initViewByHolder(HuoyunyuanTitleHolder holder, View convertView) {
//        holder.title = (TextView) convertView.findViewById(R.id.tv);
//    }
//
//    @Override
//    protected void initData(HuoyunyuanTitleHolder holder, HyyTitle1 data, int position) {
//        holder.title.setText(data.mtitle1);
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.item_tv_layout;
//    }
}

class HuoyunyuanTitleHolder {
    TextView title;
    ImageView imageView;
}

class HuoyunyuanContentHolder {
    BaseListView listView;
    TextView title;
}