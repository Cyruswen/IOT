package domain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import common.Util;
import controller.ChangeInfoController;
import controller.ChangePwdController;
import Enum.Enum;
import view.ChangePasswordView;

public class ChangePasswordService extends ChangePasswordView implements ActionListener{
	
	/**
	 * 监听按钮事件
	 */
	public void actionListen()
	{
		submit.addActionListener(this);
		back.addActionListener(this);
	}
	
	/**
	 * 重写监听事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch(action) {
		case "确定":
			_actionSubmit();
			break;
		case "返回":
			_actionBack();
			break;
		}
	}
	
	private void _actionSubmit()
	{
		String password = pass.getText();
		String password2 = pass2.getText();
		if (password.isEmpty() || password2.isEmpty()) {
			mes.setText("请输入密码!");
		} else if(!password.equals(password2)) {
			mes.setText("两次输入密码不一致!");
		} else {
			System.out.println("获得uid: " + Enum.uid);
			String reason = _talkWithService(password);
			if (reason == null) {
				mes.setText("更改成功");
			} else {
				mes.setText(reason);
			}
		}
	}
	
	private String _talkWithService(String password) {
		Map request = new HashMap<String, String>();
		request.put("uid", Enum.uid);
		request.put("password", password);
		String jsonRequest = Util.json_encode(request);
		System.out.println("请求数据:" + jsonRequest);
		String response = Util.sendJsonPost(jsonRequest, Enum.CHANGE_PASS);
		System.out.println("响应数据:" + response);
		String[] data = {"code", "reason"};
		Map responseMap = new HashMap<String, String>();
		responseMap = Util.json_decode(data, response);
		String code = (String) responseMap.get("code");
		if (code != null) {
			String reason = (String)responseMap.get("reason");
			return reason;
		} else {
			return null;
		}
	}
	
	/**
	 * 处理返回事件
	 */
	private void _actionBack()
	{
		ChangeInfoController change = new ChangeInfoController();
		change.showChangeInfo();
		frame.dispose();
	}
	
}
