package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.ConnectDatabase;


public class ModifyInformation extends JFrame{
	/*
	 * Two tags	 username
	 * One label One textbox Student number
	 * One label One text box Name
	 * */
	//panel
	private JPanel jPanel = new JPanel();
	//Label
	private JLabel jLabel = new JLabel("�û�����");
	private JLabel jLabel2 = new JLabel();
	private JLabel jLabel3 = new JLabel("ѧ  �ţ�");
	private JLabel jLabel4 = new JLabel("��  ����");
	//text box
	private JTextField field = new JTextField(22);
	private JTextField field2 = new JTextField(22);
	//font
	private Font font = new Font("Song Dynasty", Font.BOLD, 24);
	private Font font1 = new Font("Song Dynasty", Font.BOLD, 22);
	//Button Modify
	private JButton button = new JButton("Sure");
	public ModifyInformation(String user) {
		setSize(600, 450);
		// Change background image
		Icon i = new ImageIcon("img\\ah.jpg");
		JLabel Label = new JLabel(i);
		Label.setBounds(0, 0, 600, 150);
		
		jLabel2.setText(user);
		show(user);
		jLabel.setFont(font);
		jLabel2.setFont(font);
		jLabel3.setFont(font);
		jLabel4.setFont(font);
		field.setFont(font1);
		field2.setFont(font1);
		button.setFont(font1);
		
		
		add();
		
		jLabel.setBounds(150, 180, 100, 30);
		jLabel2.setBounds(260, 180, 300, 30);
		jLabel3.setBounds(150, 230, 100, 30);
		field.setBounds(260, 230, 150, 30);
		jLabel4.setBounds(150, 280, 100, 30);
		field2.setBounds(260, 280, 150, 30);
		button.setBounds(145, 340, 268, 30);
		
		jPanel.add(jLabel);
		jPanel.add(jLabel2);
		jPanel.add(jLabel3);
		jPanel.add(field);
		jPanel.add(jLabel4);
		jPanel.add(field2);
		jPanel.add(button);
		jPanel.setLayout(null);
		jPanel.setBounds(0, 0, 600, 400);
		jPanel.setOpaque(false);
		setTitle("Modify information");
		add(jPanel);
		add(Label);
		// Cannot change the size of the form
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
	}
	
	// Get information display
	private void show(String user) {
		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;
		ResultSet rs;
		String sqlStr = "select * from usertable where user = ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, user);
			rs = preSql.executeQuery();
			while (rs.next()) {
				field.setText(rs.getString(2));
				field2.setText(rs.getString(3));
			}
			con.close();
		} catch (SQLException e) {
		}
	}
	
	// Modify information
	private void modify(String user,String studentid,String name) {
		Connection con = ConnectDatabase.connectDB();

		PreparedStatement preSql;

		String sqlStr = "update usertable set studentid=? where user = ?";

		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, studentid);
			preSql.setString(2, user);
			int ok = preSql.executeUpdate();
			
			sqlStr = "update usertable set name=? where user = ?";
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, name);
			preSql.setString(2, user);
			ok = preSql.executeUpdate();
			con.close();
		} catch (SQLException e) {
		}
	}
	
	// Add event
	private void add() {
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String user = jLabel2.getText().trim();
				String studentid = field.getText().trim();
				String name = field2.getText().trim();
				modify(user,studentid,name);
				JOptionPane.showMessageDialog(null, "Successfully modified");
				dispose();
			}
		});
	}
}
