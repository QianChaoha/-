package com.example.railway.application;

import com.pgyersdk.crash.PgyCrashManager;

import android.app.Application;

public class RailWayApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		PgyCrashManager.register(this);//蒲公英-上报crash异常
	}
}
