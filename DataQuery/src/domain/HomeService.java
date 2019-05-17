package domain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import controller.CenterController;
import controller.DataManagerController;
import view.HomeView;

public class HomeService extends HomeView implements ActionListener{
	
	public void actionListener()
	{
		dataManage.addActionListener(this);
		dataQuery.addActionListener(this);
		personCenter.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch(action) {
		case "数据管理":
			DataManagerController manager = new DataManagerController();
			manager.showDataManager();
			frame.dispose();
			break;
		case "数据查询":
			break;
		case "个人中心":
			CenterController center = new CenterController();
			center.showCenter();
			frame.dispose();
			break;
		}
	}
}
