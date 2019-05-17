package controller;

import domain.LoginService;

public class LoginController {
	
	//登录界面控制器
	public void showLogin() {
		LoginService loginService = new LoginService();
		loginService.initLoginFrame();
		loginService.actionLister();
	}
}
