package com.example.railway.view;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.railway.R;
import com.example.railway.interfaces.DialogCallback;

/**
 * Description: Company: guanghua
 * 
 * @author qianchao
 */
public class CommenDialog {
	private MyDialog mDialog;
	private TextView tv_title, tv_content, tv_ok;
	private DialogCallback mDialogInterface;
	private boolean callBack = false;

	/**
	 * 
	 * @param context
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 * @param mDialogInterface
	 *            dialog点击事件 回调接口
	 */
	public CommenDialog(Context context, String title, String content, final DialogCallback mDialogInterface) {
		this.mDialogInterface = mDialogInterface;
		View view = View.inflate(context, R.layout.progress_dialog_layout, null);
		mDialog = new MyDialog(context, view, R.style.dialog);

		tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_content = (TextView) view.findViewById(R.id.tv_content);
		tv_ok = (TextView) view.findViewById(R.id.tv_ok);

		tv_title.setText(title);
		tv_content.setText(content);

		// 确定按钮点击事件的回调
		tv_ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mDialog.dismiss();
				if (mDialogInterface != null) {
					mDialogInterface.ok_click(v);
				}
			}
		});
		mDialog.show();
	}
	public CommenDialog(Context context, String title,View contentView, final DialogCallback mDialogInterface) {
		this.mDialogInterface = mDialogInterface;
		View view = View.inflate(context, R.layout.progress_dialog_layout, null);
		mDialog = new MyDialog(context, view, R.style.dialog);

		tv_title = (TextView) view.findViewById(R.id.tv_title);
		FrameLayout flContent = (FrameLayout) view.findViewById(R.id.flContent);
		flContent.removeAllViews();
		flContent.addView(contentView);
		tv_ok = (TextView) view.findViewById(R.id.tv_ok);
		tv_title.setText(title);

		// 确定按钮点击事件的回调
		tv_ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mDialog.dismiss();
				if (mDialogInterface != null) {
					mDialogInterface.ok_click(v);
				}
			}
		});
		mDialog.show();
	}

	public CommenDialog(Context context, final DialogCallback mDialogInterface) {
		this.mDialogInterface = mDialogInterface;
		View view = View.inflate(context, R.layout.progress_dialog_layout, null);
		mDialog = new MyDialog(context, view, R.style.dialog);

		tv_content = (TextView) view.findViewById(R.id.tv_content);
		tv_ok = (TextView) view.findViewById(R.id.tv_ok);

		// 确定按钮点击事件的回调
		tv_ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mDialog.dismiss();
				if (callBack) {
					if (mDialogInterface!=null) {
						mDialogInterface.ok_click(v);
					}
					callBack=false;
				}
			}
		});

	}

	public void showWithCallBack(String content) {
		this.callBack=true;
		tv_content.setText(content);
		mDialog.show();
	}
	public void show(String content) {
		this.callBack=false;
		tv_content.setText(content);
		mDialog.show();
	}
	public void show() {
		this.callBack=false;
		mDialog.show();
	}
}
