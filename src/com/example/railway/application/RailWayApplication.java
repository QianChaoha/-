package com.example.railway.application;

import com.example.railway.uitl.Config;
import com.pgyersdk.crash.PgyCrashManager;

import android.app.Application;
import com.yoo.push.PMan;

public class RailWayApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		PgyCrashManager.register(this);//蒲公英-上报crash异常
//		5秒后，将会发起广告请求（延时请求广告）。
//
//		方法里的boolean类型参数
//		isTiming值为true时，表示在调用该方法时起，定时 一小时请求一次（此模式下，网络和屏幕状态变更将会触发请求，这时将不受定时控制）；
//
//		isTiming值为false时，表示只在该方法被调用时请求广告（主动请求广告，无其他触发条件）。
		PMan.get(getApplicationContext(), Config.JU_ID, "").getMessage(getApplicationContext(), Config.JU_PUSH);
	}
}
