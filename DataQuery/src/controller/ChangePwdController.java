package controller;

import domain.ChangePasswordService;

public class ChangePwdController {
	public void showChangePwd()
	{
		ChangePasswordService password = new ChangePasswordService();
		password.initFrame();
		password.actionListen();
	}
}
