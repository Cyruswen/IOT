package cable_tem_det;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Center implements ActionListener{
	private JFrame frame = new JFrame("电力电缆温度监测系统");
	private Container c = frame.getContentPane();
	private JLabel mes = new JLabel();
	private JButton accountSafe = new JButton("账号安全");
	private JButton feedback = new JButton("意见反馈");
	private JButton update = new JButton("软件升级");
	private JButton privace = new JButton("隐私政策");
	private JButton readme = new JButton("系统介绍");
	private JButton exit = new JButton("退出登录");
	private JButton back = new JButton("返回");
	
	/**
	 * 展示个人中心界面
	 */
	public void showCenter()
	{
		_initFrame();
		_actionListen();
	}
	
	/**
	 * 监听按钮事件
	 */
	private void _actionListen()
	{
		accountSafe.addActionListener(this);
		exit.addActionListener(this);
		back.addActionListener(this);
	}
	
	/**
	 * 重写监听事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch(action) {
		case "账号安全":
			_actionAccountSafe();
			break;
		case "意见反馈":
			break;
		case "软件升级":
			break;
		case "隐私政策":
			break;
		case "系统介绍":
			break;
		case "退出登录":
			_actionExit();
			break;
		case "返回":
			_actionBack();
			break;
		}
	}
	
	/**
	 * 处理账户安全事件
	 */
	private void _actionAccountSafe()
	{
		ChangeInfo change = new ChangeInfo();
		change.showChangeInfo();
		frame.dispose();
	}
	
	/**
	 * 处理退出登录事件
	 */
	private void _actionExit()
	{
		Login login = new Login();
		login.showLogin();
		frame.dispose();
	}
	
	/**
	 * 处理返回事件
	 */
	private void _actionBack()
	{
		Home home = new Home();
		home.showHome();
		frame.dispose();
	}
	
	/**
	 * 展示界面
	 */
	private void _initFrame()
	{
		frame.setSize(410,380);
		c.setLayout(new BorderLayout());
		//顶部表单
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.white);
		JLabel title = new JLabel("电力电缆温度监测系统");
		title.setFont(new java.awt.Font("楷体", 1, 30));
		titlePanel.add(title);
		c.add(titlePanel, "North");
		//中部表单
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.white);
		centerPanel.setLayout(null);
		//账号安全
		accountSafe.setBounds(125, 25, 130, 35);
		accountSafe.setBorderPainted(false); 
		accountSafe .setFont(new  java.awt.Font("楷体",  1,  15));
		accountSafe.setBackground(Color.lightGray); 
		//意见反馈
		feedback.setBounds(125, 62, 130, 35);
		feedback.setBorderPainted(false); 
		feedback .setFont(new  java.awt.Font("楷体",  1,  15));
		feedback.setBackground(Color.lightGray);
		//软件升级
		update.setBounds(125, 99, 130, 35);
		update.setBorderPainted(false); 
		update .setFont(new  java.awt.Font("楷体",  1,  15));
		update.setBackground(Color.lightGray);
		//隐私政策
		privace.setBounds(125, 136, 130, 35);
		privace.setBorderPainted(false); 
		privace .setFont(new  java.awt.Font("楷体",  1,  15));
		privace.setBackground(Color.lightGray);
		//系统介绍
		readme.setBounds(125, 173, 130, 35);
		readme.setBorderPainted(false); 
		readme .setFont(new  java.awt.Font("楷体",  1,  15));
		readme.setBackground(Color.lightGray);
		//退出登录
		exit.setBounds(125, 210, 130, 35);
		exit.setBorderPainted(false); 
		exit .setFont(new  java.awt.Font("楷体",  1,  15));
		exit.setBackground(Color.lightGray);
		//返回
		back.setBounds(310, 255, 60, 25);
		back.setBorderPainted(false); 
		back .setFont(new  java.awt.Font("楷体",  1,  13));
		back.setBackground(Color.lightGray);
		
		centerPanel.add(accountSafe);
		centerPanel.add(feedback);
		centerPanel.add(update);
		centerPanel.add(privace);
		centerPanel.add(readme);
		centerPanel.add(exit);
		centerPanel.add(back);
		
		c.add(centerPanel, "Center");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}


}
