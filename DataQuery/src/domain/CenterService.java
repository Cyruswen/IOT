package domain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.ChangeInfoController;
import controller.HomeController;
import controller.LoginController;
import view.CenterView;

public class CenterService extends CenterView implements ActionListener {
	/**
	 * 监听按钮事件
	 */
	public void actionListen()
	{
		accountSafe.addActionListener(this);
		exit.addActionListener(this);
		back.addActionListener(this);
	}
	
	/**
	 * 重写监听事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch(action) {
		case "账号安全":
			_actionAccountSafe();
			break;
		case "意见反馈":
			break;
		case "软件升级":
			break;
		case "隐私政策":
			break;
		case "系统介绍":
			break;
		case "退出登录":
			_actionExit();
			break;
		case "返回":
			_actionBack();
			break;
		}
	}
	
	/**
	 * 处理账户安全事件
	 */
	private void _actionAccountSafe()
	{
		ChangeInfoController change = new ChangeInfoController();
		change.showChangeInfo();
		frame.dispose();
	}
	
	/**
	 * 处理退出登录事件
	 */
	private void _actionExit()
	{
		LoginController login = new LoginController();
		login.showLogin();
		frame.dispose();
	}
	
	/**
	 * 处理返回事件
	 */
	private void _actionBack()
	{
		HomeController home = new HomeController();
		home.showHome();
		frame.dispose();
	}
}
