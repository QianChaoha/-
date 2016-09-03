package com.example.railway.uitl;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.railway.R;

public class TopBarUtil {
	private Activity activity;
	private ImageView rightImageView;

	public TopBarUtil(Activity activity) {
		this.activity = activity;
	}

	/**
	 * 设置标题栏右侧ImageView的id和点击事件
	 * 
	 * @param id
	 * @param listener
	 * @return
	 */
	public TopBarUtil setRightImageView(int id, OnClickListener listener) {
		rightImageView = (ImageView) activity.findViewById(R.id.img_right);
		rightImageView.setVisibility(View.VISIBLE);
		if (id != -1) {
			rightImageView.setImageResource(id);
		}
		rightImageView.setOnClickListener(listener);
		return this;
	}

	public ImageView getRightImageView() {
		return rightImageView;
	}

	/**
	 * 设置标题栏显示的内容
	 * 
	 * @param title
	 * @return
	 */
	public TopBarUtil setTitle(String title) {
		TextView textView = (TextView) activity.findViewById(R.id.txt_title);
		textView.setText(title);
		return this;
	}

	/**
	 * 设置标题栏左侧的点击事件
	 * 
	 * @param listener
	 *            为null则默认finish()当前页面
	 * @return
	 */
	public TopBarUtil setLeftImageView(OnClickListener listener) {
		ImageView imageView = (ImageView) activity.findViewById(R.id.img_back);
		if (listener == null) {
			imageView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					activity.finish();
				}
			});
		} else {
			imageView.setOnClickListener(listener);
		}
		return this;
	}

	/**
	 * 设置标题栏左侧ImageView的id
	 * 
	 * @param id
	 * @return
	 */
	public TopBarUtil setLeftImageViewId(int id) {
		ImageView imageView = (ImageView) activity.findViewById(R.id.img_back);
		imageView.setImageResource(id);
		return this;
	};
	public TopBarUtil setLeftImageViewVisiable(int visibility) {
		ImageView imageView = (ImageView) activity.findViewById(R.id.img_back);
		imageView.setVisibility(visibility);
		return this;
	};
}
