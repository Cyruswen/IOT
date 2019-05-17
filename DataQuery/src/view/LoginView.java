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

public class LoginView {
	protected JFrame frame = new JFrame("数据查询系统");
	protected Container c = frame.getContentPane();
	protected JLabel mes = new JLabel();
	protected JTextField userName = new JTextField();
	protected JPasswordField password = new JPasswordField();
	protected JButton login = new JButton("登录");
	protected JButton register = new JButton("注册");
	
	
	/**
	 * 初始化登录注册界面
	 */
	public void initLoginFrame() {
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
		JPanel fieldPanel = new JPanel();
		fieldPanel.setBackground(Color.white);
		fieldPanel.setLayout(null);
		JLabel l1 = new JLabel("账户");
		l1.setFont(new java.awt.Font("楷体", 1, 20));
		l1.setBounds(110, 62, 50, 20);
		JLabel l2 = new JLabel("密码");
		l2.setFont(new java.awt.Font("楷体", 1, 20));;
		l2.setBounds(110, 104, 50, 20);
		fieldPanel.add(l1);
		fieldPanel.add(l2);
		userName.setBounds(170,60,120,27);
		password.setBounds(170,100,120,27);
		login.setBounds(110, 160, 80, 30);
		login.setBackground(Color.LIGHT_GRAY);
		register.setBounds(220, 160, 80, 30);
		register.setBackground(Color.LIGHT_GRAY);
		fieldPanel.add(userName);
		fieldPanel.add(password);
		fieldPanel.add(login);
		fieldPanel.add(register);
		c.add(fieldPanel,"Center");
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
