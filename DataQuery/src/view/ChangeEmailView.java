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
import javax.swing.JTextField;

public class ChangeEmailView {
	protected JFrame frame = new JFrame("数据查询系统");
	protected Container c = frame.getContentPane();
	protected JLabel mes = new JLabel();
	protected JLabel emailLable = new JLabel("新邮箱号:");
	protected JTextField email = new JTextField();
	protected JButton submit = new JButton("确定");
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
		//标签
		emailLable.setFont(new java.awt.Font("楷体", 1, 20));
		emailLable.setBounds(75, 62, 100, 20);
		//文本框
		email.setBounds(170,60,140,27);
		//确定
		submit.setBounds(40, 255, 60, 25);
		submit.setBorderPainted(false); 
		submit .setFont(new  java.awt.Font("楷体",  1,  13));
		submit.setBackground(Color.lightGray);
		
		//返回
		back.setBounds(310, 255, 60, 25);
		back.setBorderPainted(false); 
		back .setFont(new  java.awt.Font("楷体",  1,  13));
		back.setBackground(Color.lightGray);
		
		centerPanel.add(submit);
		centerPanel.add(back);
		centerPanel.add(emailLable);
		centerPanel.add(email);
		c.add(centerPanel, "Center");
		
		//底部表单
		mes.setForeground(Color.RED);
		mes.setFont(new java.awt.Font("楷体", 1, 15));
		JPanel footPanel = new JPanel(new FlowLayout());
		footPanel.setBackground(Color.white);
		footPanel.add(mes);
		c.add(footPanel, "South");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}


}
