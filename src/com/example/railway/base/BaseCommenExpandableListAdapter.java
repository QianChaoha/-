package com.example.railway.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * 
 * @author cqian
 * @param <T>listGroup中的实体类
 * @param <H>getGroupView中使用到的ViewHolder
 * @param <E>listChild中的实体类
 * @param <F>getChildView中使用到的ViewHolder
 */

public abstract class BaseCommenExpandableListAdapter<T, H, E, F> extends BaseExpandableListAdapter {
	protected List<T> listGroup;
	protected List<E> listChild;
	protected Context context;
	protected View groupConvertView;
	protected View childConvertView;


	/**
	 * @param backResult
	 * @param context
	 */
	public BaseCommenExpandableListAdapter(Context context, List<T> listGroup) {
		this.listGroup = listGroup;
		this.context = context;
	}

	@Override
	public int getGroupCount() {
		return (listGroup != null) ? listGroup.size() : 0;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		T t = listGroup.get(groupPosition);
		listChild = getChildren(t, groupPosition);
		return (listChild != null) ? listChild.size() : 0;
	}


	@Override
	public Object getGroup(int groupPosition) {
		return listGroup.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		T t = listGroup.get(groupPosition);
		return getChildren(t, groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

		H h = null;
		if (convertView == null) {
			h = getGroupHolder();
			convertView = View.inflate(context, getGroupLayoutId(), null);
			setGroupConvertView(convertView);
			initViewByGroupHolder(h, convertView);
			convertView.setTag(h);
		} else {
			h = (H) convertView.getTag();
		}
		T data = listGroup.get(groupPosition);
		initGroupData(h, data, groupPosition, isExpanded);
		return convertView;
	}


	@Override
	public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		F f = null;
		if (convertView == null) {
			f = getChildHolder();
			convertView = View.inflate(context, getChildLayoutId(), null);
			setChildConvertView(convertView);
			initViewByChildHolder(f, convertView);
			convertView.setTag(f);
		} else {
			f = (F) convertView.getTag();
		}
		T groupData = listGroup.get(groupPosition);
		E childData=(E) getChildren(groupData, groupPosition).get(childPosition);
		initChildData(f, groupData,childData, groupPosition,childPosition, isLastChild);
		return convertView;
	}


	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

	protected View findGroupViewById(int id) {
		return getGroupConvertView().findViewById(id);
	}

	protected TextView findGroupTvId(int id) {
		return (TextView) getGroupConvertView().findViewById(id);
	}

	protected ImageView findGroupIvId(int id) {
		return (ImageView) getGroupConvertView().findViewById(id);
	}

	protected RelativeLayout findGroupRlId(int id) {
		return (RelativeLayout) getGroupConvertView().findViewById(id);
	}

	protected LinearLayout findGroupLlId(int id) {
		return (LinearLayout) getGroupConvertView().findViewById(id);
	}
	protected View findChildViewById(int id) {
		return getChildConvertView().findViewById(id);
	}
	
	protected TextView findChildTvId(int id) {
		return (TextView) getChildConvertView().findViewById(id);
	}
	
	protected ImageView findChildIvId(int id) {
		return (ImageView) getChildConvertView().findViewById(id);
	}
	
	protected RelativeLayout findChildRlId(int id) {
		return (RelativeLayout) getChildConvertView().findViewById(id);
	}
	
	protected LinearLayout findChildLlId(int id) {
		return (LinearLayout) getChildConvertView().findViewById(id);
	}
	public View getChildConvertView() {
		return childConvertView;
	}

	public void setChildConvertView(View childConvertView) {
		this.childConvertView = childConvertView;
	}

	public View getGroupConvertView() {
		return groupConvertView;
	}

	public void setGroupConvertView(View groupConvertView) {
		this.groupConvertView = groupConvertView;
	}
	/**
	 * 根据指定groupList的实体类获取childList
	 * @param t
	 * @param groupPosition
	 * @return
	 */
	protected abstract List<E> getChildren(T t, int groupPosition);
	/**
	 * 用childList中的实体类完成对childViewHolder成员的初始化操作
	 * @param h
	 * @param data
	 * @param groupPosition
	 * @param isExpanded
	 */
	protected abstract void initGroupData(H holder, T data, int groupPosition, boolean isExpanded);
	/**
	 * 用groupList中的实体类完成对groupViewHolder成员的初始化操作
	 * @param e listChild中的holder
	 * @param data listGroup中的实体类
	 * @param childData 
	 * @param groupPosition
	 * @param childPosition
	 * @param isLastChild
	 */
	protected abstract void initChildData(F holder, T groupData, E childData, int groupPosition, int childPosition, boolean isLastChild);

	/**
	 * 获取一个GroupHolder
	 * 
	 * @return
	 */
	protected abstract H getGroupHolder();
	/**
	 * 获取一个ChildHolder
	 * @return
	 */
	protected abstract F getChildHolder();

	/**
	 * 对GroupHolder里的成员进行findViewById()操作
	 * @param holder
	 * @param convertView
	 */
	protected abstract void initViewByGroupHolder(H holder, View convertView);
	/**
	 * 对ChildHolder里的成员进行findViewById()操作
	 * @param holder
	 * @param convertView
	 */
	protected abstract void initViewByChildHolder(F holder, View convertView);


	/**
	 * 实例化groupConvertView时传入的layoutId
	 * 
	 * @return
	 */
	protected abstract int getGroupLayoutId();
	/**
	 * 实例化childConvertView时传入的layoutId
	 * @return
	 */
	protected abstract int getChildLayoutId();

}
