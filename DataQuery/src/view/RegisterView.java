package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterView {

	protected JFrame frame = new JFrame("数据查询系统");
	protected Container c = frame.getContentPane();
	protected JTextField userName = new JTextField();
	protected JPasswordField password = new JPasswordField();
	protected JTextField userEmail = new JTextField();
	protected JTextField userPhone = new JTextField();
	protected JLabel mes = new JLabel();
	protected JLabel nameLabel = new JLabel("用户名:");
	protected JLabel passLabel  = new JLabel("密  码:");
	protected JLabel emailLabel = new JLabel("邮  箱:");
	protected JLabel phoneLabel = new JLabel("手机号:");
	protected JButton register = new JButton("确认注册");
	protected JButton login = new JButton("返回登录");
	
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
		
		nameLabel.setFont(new java.awt.Font("楷体", 0, 18));
		nameLabel.setBounds(115, 40, 130, 35);
		passLabel.setFont(new java.awt.Font("楷体", 0, 18));
		passLabel.setBounds(115, 70, 130, 35);
		emailLabel.setFont(new java.awt.Font("楷体", 0, 18));
		emailLabel.setBounds(115, 100, 130, 35);
		phoneLabel.setFont(new java.awt.Font("楷体", 0, 18));
		phoneLabel.setBounds(115, 130, 130, 35);
		centerPanel.add(nameLabel);
		centerPanel.add(passLabel);
		centerPanel.add(emailLabel);
		centerPanel.add(phoneLabel);
		
		userName.setBounds(178,45,130,27);
		password.setBounds(178, 75, 130, 27);
		userEmail.setBounds(178, 105, 130, 27);
		userPhone.setBounds(178, 135, 130, 27);
		centerPanel.add(userName);
		centerPanel.add(password);
		centerPanel.add(userEmail);
		centerPanel.add(userPhone);
		
		register.setBounds(110, 170, 200, 30);
		register.setBorderPainted(false); 
		register .setFont(new  java.awt.Font("楷体",  1,  16));
		register.setBackground(Color.LIGHT_GRAY); 
		centerPanel.add(register);
		
		login.setBounds(110, 205, 200, 30);
		login.setBorderPainted(false); 
		login .setFont(new  java.awt.Font("楷体",  1,  16));
		login.setBackground(Color.LIGHT_GRAY); 
		centerPanel.add(login);
		
		c.add(centerPanel, "Center");
		
		JPanel footPanel = new JPanel();
		footPanel.setBackground(Color.white);
		mes.setForeground(Color.RED);
		mes.setFont(new java.awt.Font("楷体", 1, 20));
		footPanel.add(mes);
		c.add(footPanel, "South");
		
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}


}
