package cable_tem_det;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home implements ActionListener {
	private JFrame frame = new JFrame("电力电缆温度监测系统");
	private Container c = frame.getContentPane();
	private JLabel mes = new JLabel();
	private JButton deviceManage = new JButton("设备管理");
	private JButton dataQuery = new JButton("数据查询");
	private JButton temperatureWarn = new JButton("温度预警");
	private JButton personCenter = new JButton("个人中心");
	
	public void showHome()
	{
		_initFrame();
		_actionListener();
	}
	
	private void _actionListener()
	{
		deviceManage.addActionListener(this);
		dataQuery.addActionListener(this);
		temperatureWarn.addActionListener(this);
		personCenter.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch(action) {
		case "设备管理":
			break;
		case "数据查询":
			break;
		case "温度预警":
			break;
		case "个人中心":
			Center center = new Center();
			center.showCenter();
			frame.dispose();
			break;
		}
		
	}
	
	private void _initFrame()
	{
		frame.setSize(410,380);
		c.setLayout(new BorderLayout());
		//顶部表单
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.white);
		titlePanel.setLayout(new FlowLayout());
		JLabel title = new JLabel("电力电缆温度监测系统");
		title.setFont(new java.awt.Font("楷体", 1, 30));
		titlePanel.add(title);
		c.add(titlePanel,"North");
		//中部表单
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.white);
		centerPanel.setLayout(null);
		//设备管理
		deviceManage.setBounds(125, 50, 130, 35);
		deviceManage.setBorderPainted(false); 
		deviceManage .setFont(new  java.awt.Font("楷体",  1,  15));
		deviceManage.setBackground(Color.lightGray); 
		//数据查询
		dataQuery.setBounds(125, 90, 130, 35);
		dataQuery.setBorderPainted(false); 
		dataQuery .setFont(new  java.awt.Font("楷体",  1,  15));
		dataQuery.setBackground(Color.lightGray); 
		//温度预警
		temperatureWarn.setBounds(125, 130, 130, 35);
		temperatureWarn.setBorderPainted(false); 
		temperatureWarn .setFont(new  java.awt.Font("楷体",  1,  15));
		temperatureWarn.setBackground(Color.lightGray); 
		//个人中心
		personCenter.setBounds(125, 170, 130, 35);
		personCenter.setBorderPainted(false); 
		personCenter .setFont(new  java.awt.Font("楷体",  1,  15));
		personCenter.setBackground(Color.lightGray); 
		
		centerPanel.add(deviceManage);
		centerPanel.add(dataQuery);
		centerPanel.add(temperatureWarn);
		centerPanel.add(personCenter);
		
		c.add(centerPanel, "Center");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
