package com.example.sust_day01;

import org.xutils.x;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	@ViewInject(R.id.et_account)
	EditText et_account;
	
	@ViewInject(R.id.et_password)
	EditText et_password;
	
	@ViewInject(R.id.btn_login)
	Button btn_login;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 无标题设置
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		x.view().inject(this);
		btn_login.setOnClickListener(new OnClickListener() {
			//ANR Application Not Responding
			@Override
			public void onClick(View arg0) {
				String account=et_account.getText().toString();
				String password=et_password.getText().toString();
				if(account.equals("")||password.equals("")){
					Toast.makeText(MainActivity.this, "用户名和密码不能为空！", Toast.LENGTH_SHORT).show();
				}else{
					//UI线程 MainActivity所在线程 主线程
					//主要负责 界面的显示 和 用户行为的接收
					//子线程 异步任务 Service 
					postData(account,password);
				}
			}
		});
	}

	protected void postData(String account, String password) {
		RequestParams params=new RequestParams("http://192.168.0.137:8080/SUST/login");
		params.addBodyParameter("account", account);
		params.addBodyParameter("password", password);
		
		x.http().post(params, new CommonCallback<String>() {

			@Override
			public void onCancelled(CancelledException arg0) {
				
			}

			@Override//404
			public void onError(Throwable arg0, boolean arg1) {
				
			}

			@Override
			public void onFinished() {
				
			}

			@Override//200
			public void onSuccess(String result) {
				Toast.makeText(MainActivity.this, result, 0).show();
				startActivity(new Intent(MainActivity.this,IndexActivity.class));
				MainActivity.this.finish();
			}
		});
	}
}
