package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeView {
	protected JFrame frame = new JFrame("数据查询系统");
	protected Container c = frame.getContentPane();
	protected JLabel mes = new JLabel();
	protected JButton dataManage = new JButton("数据管理");
	protected JButton dataQuery = new JButton("数据查询");
	protected JButton personCenter = new JButton("个人中心");
	
	public void initFrame()
	{
		frame.setSize(410,380);
		c.setLayout(new BorderLayout());
		//顶部表单
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.white);
		titlePanel.setLayout(new FlowLayout());
		JLabel title = new JLabel("数据查询系统");
		title.setFont(new java.awt.Font("楷体", 1, 30));
		titlePanel.add(title);
		c.add(titlePanel,"North");
		//中部表单
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.white);
		centerPanel.setLayout(null);
		//设备管理
		dataManage.setBounds(125, 50, 130, 35);
		dataManage.setBorderPainted(false); 
		dataManage .setFont(new  java.awt.Font("楷体",  1,  15));
		dataManage.setBackground(Color.lightGray); 
		//数据查询
		dataQuery.setBounds(125, 90, 130, 35);
		dataQuery.setBorderPainted(false); 
		dataQuery .setFont(new  java.awt.Font("楷体",  1,  15));
		dataQuery.setBackground(Color.lightGray); 
		//个人中心
		personCenter.setBounds(125, 130, 130, 35);
		personCenter.setBorderPainted(false); 
		personCenter .setFont(new  java.awt.Font("楷体",  1,  15));
		personCenter.setBackground(Color.lightGray); 
		
		centerPanel.add(dataManage);
		centerPanel.add(dataQuery);
		centerPanel.add(personCenter);
		
		c.add(centerPanel, "Center");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
