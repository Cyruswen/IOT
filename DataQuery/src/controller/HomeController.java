package controller;

import domain.HomeService;

public class HomeController {
	
	public void showHome()
	{
		HomeService home = new HomeService();
		home.initFrame();
		home.actionListener();
	}
}
