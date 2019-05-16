package cable_tem_det;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import com.sun.org.apache.bcel.internal.generic.DLOAD;

public class DeviceController implements ActionListener{
	private JList list;
	private JFrame frame = new JFrame("电力电缆温度监测系统");
	private Container c = frame.getContentPane();
	private JButton add = new JButton("添加");
	private JButton delete = new JButton("删除");
	private JButton back = new JButton("返回");
	private JButton update = new JButton("刷新");
	
	public void showDevice()
	{
		_initFrame();
		_actionLister();
	}
	
	private void _actionLister() {
		add.addActionListener(this);
		delete.addActionListener(this);
		back.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch (action) {
		case "添加":
			_actionAdd();
			break;
		case "删除":
			_actionDelete();
			break;
		case "返回":
			_actionBack();
			break;
		}
	}
	
	/**
	 * 监听删除事件
	 */
	private void _actionDelete()
	{
		int select = JOptionPane.showConfirmDialog(null, "确定要删除该设备吗?", "警告", JOptionPane.YES_NO_OPTION); 
		if (select == JOptionPane.YES_OPTION) {
			String did = list.getSelectedValue().toString();
			boolean result = _talkWithService(did);
			if (result) {
				Home home = new Home();
				home.showHome();
				frame.dispose();
			}
		}
	}
	
	/**
	 * 请求服务端
	 */
	private boolean _talkWithService(String did) {
		Map request = new HashMap<String, String>();
		request.put("uid", Enum.uid);
		request.put("did", did);
		String jsonRequest = Util.json_encode(request);
		System.out.println("请求数据:" + jsonRequest);
		String response = Util.sendJsonPost(jsonRequest, Enum.DEL_DEVICE);
		System.out.println("响应数据:" + response);
		String[] data = {"code", "reason"};
		Map responseMap = new HashMap<String, String>();
		responseMap = Util.json_decode(data, response);
		String code = (String) responseMap.get("code");
		if (code != null) {
			String reason = (String)responseMap.get("reason");
			System.out.println("请求失败!原因: " + reason);
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 监听添加按钮事件
	 */
	private void _actionAdd()
	{
		AddDevice addDevice = new AddDevice();
		addDevice.showAddDevice();
		frame.dispose();
	}
	
	/**
	 * 监听返回事件
	 */
	private void _actionBack()
	{
		Home home = new Home();
		home.showHome();
		frame.dispose();
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
		centerPanel.setLayout(null);
		centerPanel.setBackground(Color.white);
		//列表的设置
		list.setBounds(0, 30, 410, 205);
		list.setFont(new java.awt.Font("楷体", 0, 18));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setForeground(Color.darkGray);
		list.setSelectionBackground(Color.lightGray);
		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		list.setCellRenderer(renderer);
	    list.setBorder(BorderFactory.createEtchedBorder());
	    list.setSelectedIndex(0); //默认选择第一条数据
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(0, 15, 403, 205);
		//按钮的设置
		add.setBounds(40, 255, 60, 25);
		add.setBorderPainted(false); 
		add .setFont(new  java.awt.Font("楷体",  1,  13));
		add.setBackground(Color.lightGray);
		
		delete.setBounds(175, 255, 60, 25);
		delete.setBorderPainted(false); 
		delete .setFont(new  java.awt.Font("楷体",  1,  13));
		delete.setBackground(Color.lightGray);
		
		back.setBounds(310, 255, 60, 25);
		back.setBorderPainted(false); 
		back .setFont(new  java.awt.Font("楷体",  1,  13));
		back.setBackground(Color.lightGray);
		
	    centerPanel.add(scrollPane);
	    centerPanel.add(add);
	    centerPanel.add(delete);
	    centerPanel.add(back);
		c.add(centerPanel, "Center");
		
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
