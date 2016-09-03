package com.example.railway.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.railway.R;

public class ToastShow {
	private Context context;
	private String text;
	public ToastShow(Context context, String text) {
		super();
		this.context = context;
		this.text = text;
	}
	public ToastShow(Context context) {
		super();
		this.context = context;
	}


	public void show() {
		LayoutInflater inflater = LayoutInflater.from(context);
	    View v = inflater.inflate(R.layout.toast_btom, null);
	    TextView textView = (TextView) v.findViewById(R.id.toastbtom_text);
	    textView.setText(text);
//		final Dialog dialog = new Dialog((AntsApplication)context.getApplicationContext(), R.style.ToastDialog);
//		dialog.setContentView(v);
//		Window window = dialog.getWindow();
//        window.setGravity(Gravity.BOTTOM);  
//        window.setWindowAnimations(R.style.ToastDialogAnim);
//		dialog.show();
//		new Handler().postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				dialog.dismiss();
//			}
//		}, 1500);
	    Toast toast=new Toast(context);     //创建一个toast
	    toast.setDuration(Toast.LENGTH_SHORT);  
//	    toast.setGravity(Gravity.CENTER, Gravity.CENTER, Gravity.CENTER); 
	    toast.setView(v);     //設置其显示的view,
	    toast.show();  
	}
	public void show(String text) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.toast_btom, null);
		TextView textView = (TextView) v.findViewById(R.id.toastbtom_text);
		textView.setText(text);
//		final Dialog dialog = new Dialog((AntsApplication)context.getApplicationContext(), R.style.ToastDialog);
//		dialog.setContentView(v);
//		Window window = dialog.getWindow();
//        window.setGravity(Gravity.BOTTOM);  
//        window.setWindowAnimations(R.style.ToastDialogAnim);
//		dialog.show();
//		new Handler().postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				dialog.dismiss();
//			}
//		}, 1500);
		Toast toast=new Toast(context);     //创建一个toast
		toast.setDuration(Toast.LENGTH_SHORT);  
//	    toast.setGravity(Gravity.CENTER, Gravity.CENTER, Gravity.CENTER); 
		toast.setView(v);     //設置其显示的view,
		toast.show();  
	}
}
