package domain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import common.Util;
import controller.LoginController;
import Enum.Enum;
import view.RegisterView;

public class RegisterServie extends RegisterView implements ActionListener{
	/**
	 * 监听按钮
	 */
	public void actionLister()
	{
		register.addActionListener(this);
		login.addActionListener(this);
	}
	
	/**
	 * 重写事件处理函数
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch(action) {
		case "确认注册":
			_submitAction();
			break;
		case "返回登录":
			_loginAction();
			break;
		}
	}
	
	/**
	 * 处理确认注册事件
	 */
	private void _submitAction()
	{
		String name = userName.getText();
		String passwd = password.getText();
		String email = userEmail.getText();
		String phone = userPhone.getText();
		System.out.println("获得了userName: " + name);
		if (name.isEmpty() || passwd.isEmpty() || email.isEmpty() || phone.isEmpty()) {
			mes.setText("信息不完整, 请确认后填写!");
		} else {
			String reason = _talkWithService(name, passwd, email, phone);
			if (reason == null) {
				LoginController login = new LoginController();
				login.showLogin();
				frame.dispose();
			} else {
				mes.setText(reason);
			}
		}
	}
	
	/**
	 * 处理登录事件
	 */
	private void _loginAction()
	{
		LoginController login = new LoginController();
		login.showLogin();
		frame.dispose();
	}

	private String _talkWithService(String userName, String password, String userEmail, String userPhone)
	{
		Map request = new HashMap<String, String>();
		request.put("userName", userName);
		request.put("password", password);
		request.put("userEmail", userEmail);
		request.put("userPhone", userPhone);
		String jsonRequest = Util.json_encode(request);
		String jsonResponse = Util.sendJsonPost(jsonRequest,Enum.REGISTER_URL);
		Map response = new HashMap<String, String>();
		String[] data = {"code", "reason"};
		response = Util.json_decode(data, jsonResponse);
		String code = (String) response.get("code");
		if (code != null) {
			String reason = (String)response.get("reason");
			return reason;
		} else {
			return null;
		}
	}
}
