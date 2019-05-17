package domain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import view.ChangeEmailView;
import common.Util;
import controller.ChangeInfoController;
import Enum.Enum;

public class ChangeEmailService extends ChangeEmailView implements ActionListener{
	
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
		String userEmail = email.getText();
		if (userEmail.isEmpty()) {
			mes.setText("请输入邮箱号!!");
		} else {
			String reason = _talkWithService(userEmail);
			if (reason == null) {
				mes.setText("更改成功");
			} else {
				mes.setText(reason);
			}
		}
	}
	
	private String _talkWithService(String email) {
		Map request = new HashMap<String, String>();
		request.put("uid", Enum.uid);
		request.put("userEmail", email);
		String jsonRequest = Util.json_encode(request);
		System.out.println("请求数据:" + jsonRequest);
		String response = Util.sendJsonPost(jsonRequest, Enum.CHANGE_EMAIL);
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
