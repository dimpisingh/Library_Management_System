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
import javax.swing.JPasswordField;

import database.ConnectDatabase;


public class ModifyPassword extends JFrame {
	/*
	 * Two labels Username One label One text box Old password One label One text box New password One label One text box Confirm password
	 */
	// Panel
	private JPanel jPanel = new JPanel();
	// Label
	private JLabel jLabel = new JLabel("username:");
	private JLabel jLabel2 = new JLabel();
	private JLabel jLabel3 = new JLabel("original password: ");
	private JLabel jLabel4 = new JLabel("New Password: ");
	private JLabel jLabel5 = new JLabel("Confirm Password: ");
	// text box
	private JPasswordField field = new JPasswordField(22);
	private JPasswordField field2 = new JPasswordField(22);
	private JPasswordField field3 = new JPasswordField(22);
	// font
	private Font font = new Font("Song Dynasty", Font.BOLD, 24);
	private Font font1 = new Font("Song Dynasty", Font.BOLD, 22);
	// Button Modify
	private JButton button = new JButton("Sure");
	private JFrame frame = new JFrame();

	public ModifyPassword(String user) {
		setSize(600, 450);
		// Change background image
		Icon i = new ImageIcon("img\\ah.jpg");
		JLabel Label = new JLabel(i);
		Label.setBounds(0, 0, 600, 150);

		setTitle("change Password");
		jLabel2.setText(user);
		jLabel.setFont(font);
		jLabel2.setFont(font);
		jLabel3.setFont(font);
		jLabel4.setFont(font);
		jLabel5.setFont(font);
		field.setFont(font1);
		field2.setFont(font1);
		field3.setFont(font1);
		button.setFont(font1);

		add();

		jLabel.setBounds(120, 170, 150, 30);
		jLabel2.setBounds(260, 170, 300, 30);
		jLabel3.setBounds(120, 210, 150, 30);
		field.setBounds(260, 215, 140, 25);
		jLabel4.setBounds(120, 250, 150, 30);
		field2.setBounds(260, 255, 140, 25);
		jLabel5.setBounds(120, 290, 150, 30);
		field3.setBounds(260, 295, 140, 25);
		button.setBounds(115, 340, 285, 30);

		jPanel.add(jLabel);
		jPanel.add(jLabel2);
		jPanel.add(jLabel3);
		jPanel.add(field);
		jPanel.add(jLabel4);
		jPanel.add(field2);
		jPanel.add(jLabel5);
		jPanel.add(field3);
		jPanel.add(button);
		jPanel.setLayout(null);
		jPanel.setBounds(0, 0, 600, 400);
		jPanel.setOpaque(false);
		add(jPanel);
		add(Label);
		// Cannot change the size of the form
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	

	// Compare old passwords
	private boolean comparison(String user, String password) {
		Connection con = ConnectDatabase.connectDB();

		PreparedStatement preSql;
		ResultSet rs;
		String sqlStr = "select * from usertable where user = ?";
		
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, user);
			rs = preSql.executeQuery();
			while (rs.next()) {
				String s = rs.getString(4);
				
				if (s.equals(password)) {
					return true;
				} else {
					return false;
				}
			}
			con.close();
			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	// Add event
	private void add() {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String user = jLabel2.getText().trim();
				String oldpass = field.getText().trim();
				String newpass = field2.getText().trim();
				String surepass = field3.getText().trim();
				if (comparison(user, oldpass)) {
					if(newpass.length()<6||newpass.length()>16) {
						JOptionPane.showMessageDialog(null, "Password requires 6-16 characters, Cannot contain spaces", "warn", JOptionPane.WARNING_MESSAGE);
						empty();
					}else {
						if (newpass.equals(surepass)) {
							database.ModifyPassword.modifypass(user, newpass);
							JOptionPane.showMessageDialog(null, "Successfully modified");
	
							JOptionPane.showMessageDialog(null, "Please log in again");
							dispose();
							frame.dispose();
							new Land();
						}else {
							JOptionPane.showMessageDialog(null, "Confirm passwords are not the same", "warn", JOptionPane.WARNING_MESSAGE);
							empty();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "The original password is incorrect", "warn", JOptionPane.WARNING_MESSAGE);
					empty();
				}

			}
		});
	}

	private void empty() {
		field.setText("");
		field2.setText("");
		field3.setText("");
	}
}
