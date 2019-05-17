package controller;

import domain.DataManagerService;

public class DataManagerController {
	public void showDataManager()
	{
		DataManagerService manager = new DataManagerService();
		manager.initFrame();
		manager.actionListener();
	}
}
