package cable_tem_det;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChangeInfo implements ActionListener{
	private JFrame frame = new JFrame("电力电缆温度监测系统");
	private Container c = frame.getContentPane();
	private JLabel mes = new JLabel();
	private JButton changeEmail = new JButton("更换邮箱");
	private JButton changePhone = new JButton("更换手机");
	private JButton changePassword = new JButton("更换密码");
	private JButton back = new JButton("返回");
	
	/**
	 * 展示个人中心界面
	 */
	public void showChangeInfo()
	{
		_initFrame();
		_actionListen();
	}
	
	/**
	 * 监听按钮事件
	 */
	private void _actionListen()
	{
		changeEmail.addActionListener(this);
		changePhone.addActionListener(this);
		changePassword.addActionListener(this);
		back.addActionListener(this);
	}
	
	/**
	 * 重写监听事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch(action) {
		case "更换邮箱":
			_actionChangeEmail();
			break;
		case "更换手机":
			_actionChangePhone();
			break;
		case "更换密码":
			_actionChangePass();
			break;
		case "返回":
			_actionBack();
			break;
		}
	}
	
	private void _actionChangePass()
	{
		ChangePassword changepass = new ChangePassword();
		changepass.showChangePass();
		frame.dispose();
	}
	
	private void _actionChangePhone()
	{
		ChangePhone changePhone = new ChangePhone();
		changePhone.showChangePhone();
		frame.dispose();
	}
	
	private void _actionChangeEmail()
	{
		ChangeEmail changeEmail = new ChangeEmail();
		changeEmail.showChangeEmail();
		frame.dispose();
	}
	
	/**
	 * 处理返回事件
	 */
	private void _actionBack()
	{
		Center center = new Center();
		center.showCenter();
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
