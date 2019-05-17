package domain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import Enum.Enum;
import common.Util;
import controller.HomeController;
import controller.RegisterController;
import view.LoginView;

public class LoginService extends LoginView implements ActionListener {
	
	/**
	 * 监听登录按钮事件
	 */
	public void actionLister()
	{
		login.addActionListener(this);
		register.addActionListener(this);
	}
	
	/**
	 * 重写事件监听函数
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch(action) {
		case "登录":
			_loginAction();
			break;
		case "注册":
			_registerAction();
			break;
		}
	}
	
	/**
	 * 处理登录事件
	 */
	private void _loginAction()
	{
		if (userName.getText().equals("")) {
			mes.setText("用户名不能为空");
		} else if (password.getText().equals("")) {
			mes.setText("密码不能为空");
		} else {
			mes.setText("");
			String strUserName = userName.getText();
			String strPassword = password.getText();
			String reason = _talkWithService(strUserName, strPassword);
			if (reason == null) {
				HomeController home = new HomeController();
				home.showHome();
				System.out.println("登录成功");
				frame.dispose();
			} else {
				mes.setText(reason);
			}
		}
	}
	
	/**
	 * 处理注册事件
	 */
	private void _registerAction()
	{
		RegisterController register = new RegisterController();
		register.showRegister();
		System.out.println("进入注册界面");
		frame.dispose();
	}
	
	/**
	* 处理和服务端通信的数据
	 */
	private String _talkWithService(String userName, String password)
	{
		Map request = new HashMap<String, String>();
		request.put("account", userName);
		request.put("password", password);
		String jsonRequest = Util.json_encode(request);
		System.out.println("请求数据:" + jsonRequest);
		String response = Util.sendJsonPost(jsonRequest, Enum.LOGIN_URL);
		System.out.println("响应数据:" + response);
		String[] data = {"code", "reason"};
		Map responseMap = new HashMap<String, String>();
		responseMap = Util.json_decode(data, response);
		Enum.uid = (String) responseMap.get("uid");
		String code = (String) responseMap.get("code");
		if (code != null) {
			String reason = (String)responseMap.get("reason");
			return reason;
		} else {
			return null;
		}
	}
	
}
