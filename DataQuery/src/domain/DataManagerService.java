package domain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.HomeController;
import view.DataManagerView;

public class DataManagerService extends DataManagerView implements ActionListener{

	public void actionListener()
	{
		add.addActionListener(this);
		select.addActionListener(this);
		back.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch(action){
		case "添加":
			JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "查询":
			System.out.println("查询");
			break;
		case "返回":
			HomeController home = new HomeController();
			home.showHome();
			frame.dispose();
			break;
		}
		
	}
	
}
