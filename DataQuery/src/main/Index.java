package main;

import javax.swing.UIManager;

import controller.DataManagerController;
import controller.HomeController;
import controller.LoginController;

public class Index {

	public static void main(String[] args) {
		try {
			String style = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
			UIManager.setLookAndFeel(style);
        } catch (Throwable e) {
            e.printStackTrace();
        }
		
		LoginController loginController = new LoginController();
		loginController.showLogin();
//		HomeController home = new HomeController();
//		home.showHome();
//		DataManagerController manager = new DataManagerController();
//		manager.showDataManager();
	}

}
