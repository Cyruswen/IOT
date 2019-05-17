package controller;

import domain.ChangePhoneService;

public class ChangePhoneController {
	
	public void showChangePhone()
	{
		ChangePhoneService changePhone = new ChangePhoneService();
		changePhone.initFrame();
		changePhone.actionListen();
	}
}
