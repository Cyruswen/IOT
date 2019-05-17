package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChangeInfoView {

	protected JFrame frame = new JFrame("数据查询系统");
	protected Container c = frame.getContentPane();
	protected JLabel mes = new JLabel();
	protected JButton changeEmail = new JButton("更换邮箱");
	protected JButton changePhone = new JButton("更换手机");
	protected JButton changePassword = new JButton("更换密码");
	protected JButton back = new JButton("返回");
	
	/**
	 * 展示界面
	 */
	public void initFrame()
	{
		frame.setSize(410,380);
		c.setLayout(new BorderLayout());
		//顶部表单
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.white);
		JLabel title = new JLabel("数据查询系统");
		title.setFont(new java.awt.Font("楷体", 1, 30));
		titlePanel.add(title);
		c.add(titlePanel, "North");
		//中部表单
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.white);
		centerPanel.setLayout(null);
		//更改邮箱
		changeEmail.setBounds(125, 92, 130, 35);
		changeEmail.setBorderPainted(false); 
		changeEmail .setFont(new  java.awt.Font("楷体",  1,  15));
		changeEmail.setBackground(Color.lightGray); 
		//更改手机
		changePhone.setBounds(125, 45, 130, 35);
		changePhone.setBorderPainted(false); 
		changePhone .setFont(new  java.awt.Font("楷体",  1,  15));
		changePhone.setBackground(Color.lightGray);
		//更改密码
		changePassword.setBounds(125, 139, 130, 35);
		changePassword.setBorderPainted(false); 
		changePassword .setFont(new  java.awt.Font("楷体",  1,  15));
		changePassword.setBackground(Color.lightGray);
		
		//返回
		back.setBounds(310, 255, 60, 25);
		back.setBorderPainted(false); 
		back .setFont(new  java.awt.Font("楷体",  1,  13));
		back.setBackground(Color.lightGray);
		
		centerPanel.add(changeEmail);
		centerPanel.add(changePhone);
		centerPanel.add(changePassword);
		centerPanel.add(back);
		c.add(centerPanel, "Center");
		
		//底部表单
		mes.setForeground(Color.RED);
		mes.setFont(new java.awt.Font("楷体", 1, 20));
		JPanel footPanel = new JPanel(new FlowLayout());
		footPanel.setBackground(Color.white);
		footPanel.add(mes);
		c.add(footPanel, "South");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}


}
