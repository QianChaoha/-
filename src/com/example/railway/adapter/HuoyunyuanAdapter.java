package com.example.railway.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.example.railway.bean.HuoYunYuan;

public class HuoyunyuanAdapter extends PagerAdapter {
	private List<View> huoYunYuans;
	private Context context;
	
	public HuoyunyuanAdapter(List<View> huoYunYuans, Context context) {
		super();
		this.huoYunYuans = huoYunYuans;
		this.context = context;
	}

	@Override
	public int getCount() {
		return huoYunYuans.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView(huoYunYuans.get(position));

	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = huoYunYuans.get(position);
		((ViewPager) container).addView(view);
		return view;
	}

}
