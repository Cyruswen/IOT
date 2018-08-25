package com.example.sust_day01;

import org.xutils.x;
import org.xutils.common.Callback;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;

public class IndexActivity extends Activity{
	@ViewInject(R.id.sc)
	Switch sc;
	@ViewInject(R.id.btn_add)
	Button btn_add;
	@ViewInject(R.id.btn_sub)
	Button btn_sub;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		x.view().inject(this);
		sc.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
				String pin="";
				if(isChecked){//IO0ÁÁ
					pin="ON1";
				}else{//IO0Ãð
					pin="OFF1";
				}
				postData(pin);
			}
		});
		btn_add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				postData("ADD");
			}
		});
		btn_sub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				postData("DEL");
			}
		});
	}
	protected void postData(String pin) {
		RequestParams params = new RequestParams("http://192.168.0.172");
		params.addParameter("pin",pin);
		x.http().request(HttpMethod.GET, params, new Callback.CommonCallback<String>() {
		    @Override
		    public void onSuccess(String result) {
		    	Log.d("TAG", "onSuccess");
		    }
		    @Override
		    public void onError(Throwable ex, boolean isOnCallback) {
		    	Log.d("TAG", "onError");
		    }
		    @Override
		    public void onCancelled(CancelledException cex) {
		    	Log.d("TAG", "onCancelled");
		    }
		    @Override
		    public void onFinished() {
		    	Log.d("TAG", "onCancelled");
		    }
		});
	}
	

}
