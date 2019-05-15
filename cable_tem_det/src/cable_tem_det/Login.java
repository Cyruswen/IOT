package cable_tem_det;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login implements ActionListener{
	private JFrame frame = new JFrame("电力电缆温度监测系统");
	private Container c = frame.getContentPane();
	private JLabel mes = new JLabel();
	private JTextField userName = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JButton login = new JButton("登录");
	private JButton register = new JButton("注册");
	
	/**
	 * 操作登录界面
	 */
	public void showLogin() {
		_initFrame();
		_actionLister();
	}
	
	/**
	 * 监听按钮事件
	 */
	private void _actionLister()
	{
		login.addActionListener(this);
		register.addActionListener(this);
	}
	
	/**
	 * 重写事件监听函数
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch(action) {
		case "登录":
			_loginAction();
			break;
		case "注册":
			_registerAction();
			break;
		}
	}
	
	/**
	 * 处理登录事件
	 */
	private void _loginAction()
	{
		if (userName.getText().equals("")) {
			mes.setText("用户名不能为空");
		} else if (password.getText().equals("")) {
			mes.setText("密码不能为空");
		} else {
			mes.setText("");
			String strUserName = userName.getText();
			String strPassword = password.getText();
			String reason = _talkWithService(strUserName, strPassword);
			if (reason == null) {
				Home home = new Home();
				home.showHome();
				frame.dispose();
			} else {
				mes.setText(reason);
			}
		}
	}
	
	/**
	 * 处理注册事件
	 */
	private void _registerAction()
	{
		Register register = new Register();
		register.showRegister();
		frame.dispose();
	}
	
	/**
	* 处理和服务端通信的数据
	 */
	private String _talkWithService(String userName, String password)
	{
		Map request = new HashMap<String, String>();
		request.put("account", userName);
		request.put("password", password);
		String jsonRequest = Util.json_encode(request);
		System.out.println("请求数据:" + jsonRequest);
		String response = Util.sendJsonPost(jsonRequest, Enum.LOGIN_URL);
		System.out.println("响应数据:" + response);
		String[] data = {"code", "reason"};
		Map responseMap = new HashMap<String, String>();
		responseMap = Util.json_decode(data, response);
		Enum.uid = (String) responseMap.get("uid");
		String code = (String) responseMap.get("code");
		if (code != null) {
			String reason = (String)responseMap.get("reason");
			return reason;
		} else {
			return null;
		}
	}
	
	/**
	 * 初始化登录注册界面
	 */
	private void _initFrame() {
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