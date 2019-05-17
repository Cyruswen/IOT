package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class DataManagerView {
	protected JFrame frame = new JFrame("数据查询系统");
	protected Container c = frame.getContentPane();
	protected JLabel mes = new JLabel();
	
	protected JLabel MLabel = new JLabel("M :");
	protected JLabel CLabel = new JLabel("C :");
	protected JLabel ALabel = new JLabel("A :");
	protected JLabel NLabel = new JLabel("N :");
	protected JLabel VxLabel = new JLabel("vx:");
	protected JLabel VyLabel = new JLabel("vy:");
	protected JLabel AxLabel = new JLabel("ax:");
	protected JLabel AyLabel = new JLabel("ay:");
	protected JLabel VzLabel = new JLabel("vz:");
	protected JLabel TrscwsLabel = new JLabel("TRSCWS:");
	protected JLabel EcscwsLabel = new JLabel("ECSCWS:");
	protected JLabel TcscwsLabel = new JLabel("TCSCWS:");
	protected JLabel CrLabel = new JLabel("Cr:");
	protected JLabel CsLabel = new JLabel("Cs:");
	protected JButton add = new JButton("添加");
	protected JButton select = new JButton("查询");
	protected JButton back = new JButton("返回"); 
	
	protected JTextField MText = new JTextField();
	protected JTextField CText = new JTextField();
	protected JTextField AText = new JTextField();
	protected JTextField NText = new JTextField();
	protected JTextField VxText = new JTextField();
	protected JTextField VyText= new JTextField();
	protected JTextField AxText = new JTextField();
	protected JTextField AyText = new JTextField();
	protected JTextField VzText = new JTextField();
	protected JTextField TrscwsText = new JTextField();
	protected JTextField EcscwsText= new JTextField();
	protected JTextField TcscwsText= new JTextField();
	protected JTextField CrText = new JTextField();
	protected JTextField CsText = new JTextField();
	
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
		centerPanel.setLayout(null);
		centerPanel.setBackground(Color.white);
		
		//配置各标签
		MLabel	.setFont(new java.awt.Font("楷体", 0, 15));
		MLabel.setBounds(40, 20, 50, 10);
		MText.setBounds(70, 13, 110, 27);
		CLabel	.setFont(new java.awt.Font("楷体", 0, 15));
		CLabel.setBounds(40, 50, 50, 10);
		CText.setBounds(70, 43, 110, 27);
		ALabel	.setFont(new java.awt.Font("楷体", 0, 15));
		ALabel.setBounds(40, 80, 50, 10);
		AText.setBounds(70, 73, 110, 27);
		NText	.setFont(new java.awt.Font("楷体", 0, 15));
		NLabel.setBounds(40, 110, 50, 10);
		NText.setBounds(70, 103, 110, 27);
		VxLabel.setFont(new java.awt.Font("楷体", 0, 15));
		VxLabel.setBounds(40, 140, 50, 10);
		VxText.setBounds(70, 133, 110, 27);
		VyLabel.setFont(new java.awt.Font("楷体", 0, 15));
		VyLabel.setBounds(40, 170, 50, 10);
		VyText.setBounds(70, 163, 110, 27);
		VzLabel.setFont(new java.awt.Font("楷体", 0, 15));
		VzLabel.setBounds(40, 200, 50, 10);
		VzText.setBounds(70, 193, 110, 27);
		AxLabel.setFont(new java.awt.Font("楷体", 0, 15));
		AxLabel.setBounds(210, 20, 50, 10);
		AxText.setBounds(240, 13, 110, 27);
		AyLabel.setFont(new java.awt.Font("楷体", 0, 15));
		AyLabel.setBounds(210, 50, 50, 10);
		AyText.setBounds(240, 43, 110, 27);
		CrLabel.setFont(new java.awt.Font("楷体", 0, 15));
		CrLabel.setBounds(210, 80, 50, 10);
		CrText.setBounds(240, 103, 110, 27);
		CsLabel.setFont(new java.awt.Font("楷体", 0, 15));
		CsLabel.setBounds(210, 110, 50, 10);
		CsText.setBounds(240, 73, 110, 27);	
		TrscwsLabel.setFont(new java.awt.Font("楷体", 0, 12));
		TrscwsLabel.setBounds(195, 140, 50, 10);
		TrscwsText.setBounds(240, 133, 110, 27);
		EcscwsLabel.setFont(new java.awt.Font("楷体", 0, 12));
		EcscwsLabel.setBounds(195, 170, 50, 10);
		EcscwsText.setBounds(240, 163, 110, 27);
		TcscwsLabel.setFont(new java.awt.Font("楷体", 0, 12));
		TcscwsLabel.setBounds(195, 200, 50, 10);
		TcscwsText.setBounds(240, 193, 110, 27);
		
		
		//按钮配置
		add.setBounds(40, 255, 60, 25);
		add.setBorderPainted(false); 
		add .setFont(new  java.awt.Font("楷体",  1,  13));
		add.setBackground(Color.lightGray);
		
		select.setBounds(175, 255, 60, 25);
		select.setBorderPainted(false); 
		select .setFont(new  java.awt.Font("楷体",  1,  13));
		select.setBackground(Color.lightGray);
		
		back.setBounds(310, 255, 60, 25);
		back.setBorderPainted(false); 
		back .setFont(new  java.awt.Font("楷体",  1,  13));
		back.setBackground(Color.lightGray);
		
		centerPanel.add(MLabel);
		centerPanel.add(MText);
		centerPanel.add(CLabel);
		centerPanel.add(CText);
		centerPanel.add(ALabel);
		centerPanel.add(AText);
		centerPanel.add(NLabel);
		centerPanel.add(NText);
		centerPanel.add(VxLabel);
		centerPanel.add(VxText);
		centerPanel.add(VyLabel);
		centerPanel.add(VyText);
		centerPanel.add(VzLabel);
		centerPanel.add(VzText);
		centerPanel.add(AxLabel);
		centerPanel.add(AxText);
		centerPanel.add(AyLabel);
		centerPanel.add(AyText);
		centerPanel.add(CrLabel);
		centerPanel.add(CrText);
		centerPanel.add(CsLabel);
		centerPanel.add(CsText);
		centerPanel.add(TrscwsLabel);
		centerPanel.add(TrscwsText);
		centerPanel.add(EcscwsLabel);
		centerPanel.add(EcscwsText);
		centerPanel.add(TcscwsLabel);
		centerPanel.add(TcscwsText);
		
		
	    centerPanel.add(add);
	    centerPanel.add(select);
	    centerPanel.add(back);
		c.add(centerPanel, "Center");
		
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
