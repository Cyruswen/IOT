package cable_tem_det;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class main {

	public static void main(String[] args) {
		
		try {
			String style = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
			UIManager.setLookAndFeel(style);
        } catch (Throwable e) {
            e.printStackTrace();
        }
		
		Login login = new Login();
		login.showLogin();
//	    Home home = new Home();
//		home.showHome();
//		Register register = new Register();
//		register.showRegister();
//		Center center = new Center();
//		center.showCenter();
//		ChangeInfo change = new ChangeInfo();
//		change.showChangeInfo();
//		ChangePhone change = new ChangePhone();
//		change.showChangePhone();
//		ChangePassword changepass = new ChangePassword();
//		changepass.showChangePass();
	}

}
