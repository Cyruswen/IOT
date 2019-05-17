package controller;

import domain.CenterService;

public class CenterController {
	
	public void showCenter()
	{
		CenterService center = new CenterService();
		center.initFrame();
		center.actionListen();
	}
}
