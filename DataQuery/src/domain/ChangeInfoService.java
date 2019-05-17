package domain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.CenterController;
import controller.ChangeEmailController;
import controller.ChangePhoneController;
import controller.ChangePwdController;
import view.ChangeInfoView;


public class ChangeInfoService extends ChangeInfoView implements ActionListener {
	/**
	 * 监听按钮事件
	 */
	public void actionListen()
	{
		changeEmail.addActionListener(this);
		changePhone.addActionListener(this);
		changePassword.addActionListener(this);
		back.addActionListener(this);
	}
	
	/**
	 * 重写监听事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch(action) {
		case "更换邮箱":
			_actionChangeEmail();
			break;
		case "更换手机":
			_actionChangePhone();
			break;
		case "更换密码":
			_actionChangePass();
			break;
		case "返回":
			_actionBack();
			break;
		}
	}
	
	private void _actionChangePass()
	{
		ChangePwdController password = new ChangePwdController();
		password.showChangePwd();
		frame.dispose();
	}
	
	private void _actionChangePhone()
	{
		ChangePhoneController changePhone = new ChangePhoneController();
		changePhone.showChangePhone();
		frame.dispose();
	}
	
	private void _actionChangeEmail()
	{
		ChangeEmailController changeEmail = new ChangeEmailController();
		changeEmail.showChangeEmail();
		frame.dispose();
	}
	
	/**
	 * 处理返回事件
	 */
	private void _actionBack()
	{
		CenterController center = new CenterController();
		center.showCenter();
		frame.dispose();
	}
}
