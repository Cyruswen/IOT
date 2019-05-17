package controller;

import domain.ChangeEmailService;

public class ChangeEmailController {
	public void showChangeEmail()
	{
		ChangeEmailService change = new ChangeEmailService();
		change.initFrame();
		change.actionListen();
	}
}
