package cable_tem_det;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.html.HTML;

public class Register implements ActionListener{
	private JFrame frame = new JFrame("电力电缆温度监测系统");
	private Container c = frame.getContentPane();
	private JTextField userName = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JTextField userEmail = new JTextField();
	private JTextField userPhone = new JTextField();
	private JLabel mes = new JLabel();
	private JLabel nameLabel = new JLabel("用户名:");
	private JLabel passLabel  = new JLabel("密  码:");
	private JLabel emailLabel = new JLabel("邮  箱:");
	private JLabel phoneLabel = new JLabel("手机号:");
	private JButton register = new JButton("确认注册");
	private JButton login = new JButton("返回登录");
	
	public void showRegister()
	{
		_initFrame();
		_actionLister();
	}
	
	/**
	 * 监听按钮
	 */
	private void _actionLister()
	{
		register.addActionListener(this);
		login.addActionListener(this);
	}
	
	/**
	 * 重写事件处理函数
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch(action) {
		case "确认注册":
			_submitAction();
			break;
		case "返回登录":
			_loginAction();
			break;
		}
		
	}
	
	/**
	 * 处理确认注册事件
	 */
	private void _submitAction()
	{
		String name = userName.getText();
		String passwd = password.getText();
		String email = userEmail.getText();
		String phone = userPhone.getText();
		System.out.println("获得了userName: " + name);
		if (name.isEmpty() || passwd.isEmpty() || email.isEmpty() || phone.isEmpty()) {
			mes.setText("信息不完整, 请确认后填写!");
		} else {
			String reason = _talkWithService(name, passwd, email, phone);
			if (reason == null) {
				Login login = new Login();
				login.showLogin();
				frame.dispose();
			} else {
				mes.setText(reason);
			}
		}
	}
	
	/**
	 * 处理登录事件
	 */
	private void _loginAction()
	{
		Login login = new Login();
		login.showLogin();
		frame.dispose();
	}

	private String _talkWithService(String userName, String password, String userEmail, String userPhone)
	{
		Map request = new HashMap<String, String>();
		request.put("userName", userName);
		request.put("password", password);
		request.put("userEmail", userEmail);
		request.put("userPhone", userPhone);
		String jsonRequest = Util.json_encode(request);
		String jsonResponse = Util.sendJsonPost(jsonRequest,Enum.REGISTER_URL);
		Map response = new HashMap<String, String>();
		String[] data = {"code", "reason"};
		response = Util.json_decode(data, jsonResponse);
		String code = (String) response.get("code");
		if (code != null) {
			String reason = (String)response.get("reason");
			return reason;
		} else {
			return null;
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
