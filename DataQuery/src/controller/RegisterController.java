package controller;

import domain.RegisterServie;

public class RegisterController {
	public void showRegister()
	{
		RegisterServie register = new RegisterServie();
		register.initFrame();
		register.actionLister();
	}
}
