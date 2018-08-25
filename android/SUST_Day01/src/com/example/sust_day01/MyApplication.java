package com.example.sust_day01;

import org.xutils.x;

import android.app.Application;

public class MyApplication extends Application{
	@Override
	public void onCreate() {
		super.onCreate();
		//对XUtls进行初始化
		x.Ext.init(this);
	}
}
