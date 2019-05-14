package cable_tem_det;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home {
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
		System.out.println("在这里我也能拿到uid: " + Enum.uid);
	}
	
	private void _initFrame()
	{
		frame.setSize(410,380);
		c.setLayout(new BorderLayout());
		//顶部表单
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		JLabel title = new JLabel("电力电缆温度监测系统");
		title.setFont(new java.awt.Font("微软雅黑", 1, 30));
		titlePanel.add(title);
		c.add(titlePanel,"North");
		//中部表单
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(null);
		//设备管理
		deviceManage.setBounds(125, 50, 130, 35);
		deviceManage.setBorderPainted(false); 
		deviceManage .setFont(new  java.awt.Font("微软雅黑",  1,  15));
		deviceManage.setBackground(Color.LIGHT_GRAY); 
		//数据查询
		dataQuery.setBounds(125, 90, 130, 35);
		dataQuery.setBorderPainted(false); 
		dataQuery .setFont(new  java.awt.Font("微软雅黑",  1,  15));
		dataQuery.setBackground(Color.LIGHT_GRAY); 
		//温度预警
		temperatureWarn.setBounds(125, 130, 130, 35);
		temperatureWarn.setBorderPainted(false); 
		temperatureWarn .setFont(new  java.awt.Font("微软雅黑",  1,  15));
		temperatureWarn.setBackground(Color.LIGHT_GRAY); 
		//个人中心
		personCenter.setBounds(125, 170, 130, 35);
		personCenter.setBorderPainted(false); 
		personCenter .setFont(new  java.awt.Font("微软雅黑",  1,  15));
		personCenter.setBackground(Color.LIGHT_GRAY); 
		
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
