package controller;

import domain.ChangeInfoService;

public class ChangeInfoController {
	public void showChangeInfo()
	{
		ChangeInfoService change = new ChangeInfoService();
		change.initFrame();
		change.actionListen();
	}
}
