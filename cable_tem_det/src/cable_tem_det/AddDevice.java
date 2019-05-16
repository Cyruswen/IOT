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

public class AddDevice implements ActionListener{


	
	private JFrame frame = new JFrame("电力电缆温度监测系统");
	private Container c = frame.getContentPane();
	private JLabel mes = new JLabel();
	private JLabel didLable = new JLabel("设备号:");
	private JLabel verifyLable = new JLabel("验证码:");
	private JTextField did = new JTextField();
	private JPasswordField verifyCode = new JPasswordField();
	private JButton submit = new JButton("确定");
	private JButton back = new JButton("返回");
	
	/**
	 * 展示个人中心界面
	 */
	public void showAddDevice()
	{
		_initFrame();
		_actionListen();
	}
	
	/**
	 * 监听按钮事件
	 */
	private void _actionListen()
	{
		submit.addActionListener(this);
		back.addActionListener(this);
	}
	
	/**
	 * 重写监听事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch(action) {
		case "确定":
			_actionSubmit();
			break;
		case "返回":
			_actionBack();
			break;
		}
	}
	
	private void _actionSubmit()
	{
		String deviceId = did.getText();
		String verify = verifyCode.getText();
		if (deviceId.isEmpty() || verify.isEmpty()) {
			mes.setText("请填写信息!!");
		} else {
			String reason = _talkWithService(deviceId, verify);
			if (reason == null) {
				mes.setText("添加设备成功!");
			} else {
				mes.setText(reason);
			}
		}
	}
	
	private String _talkWithService(String did, String verifyCode) {
		Map request = new HashMap<String, String>();
		request.put("uid", Enum.uid);
		request.put("did", did);
		request.put("verifyCode", verifyCode);
		String jsonRequest = Util.json_encode(request);
		System.out.println("请求数据:" + jsonRequest);
		String response = Util.sendJsonPost(jsonRequest, Enum.ADD_DEVICE);
		System.out.println("响应数据:" + response);
		String[] data = {"code", "reason"};
		Map responseMap = new HashMap<String, String>();
		responseMap = Util.json_decode(data, response);
		String code = (String) responseMap.get("code");
		if (code != null) {
			String reason = (String)responseMap.get("reason");
			return reason;
		} else {
			return null;
		}
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
		
		didLable.setFont(new java.awt.Font("楷体", 1, 20));
		didLable.setBounds(75, 62, 100, 20);
		verifyLable.setFont(new java.awt.Font("楷体", 1, 20));
		verifyLable.setBounds(75, 100, 100, 20);
		did.setBounds(170,60,140,27);
		verifyCode.setBounds(170, 98, 140, 27);
		
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
		centerPanel.add(didLable);
		centerPanel.add(verifyLable);
		centerPanel.add(did);
		centerPanel.add(verifyCode);
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
