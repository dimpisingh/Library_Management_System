package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Admin {
	/*
	 * Label
	 *
	 * Four buttons: Modify account, modify password, borrow book records, exit the program
	 * 
	 */
	// Panel
	public JPanel jPanel2 = new JPanel();
	// Label
	private JLabel jLabel = new JLabel("Account management");
	// button
	private JButton button = new JButton("Modify information");
	private JButton button2 = new JButton("Change Password");
	private JButton button3 = new JButton("Book borrowing record");
	private JButton button4 = new JButton("sign out");
	private JButton button5 = new JButton("Account information");
	private JButton button6 = new JButton("Modify permissions");
	private JButton button7 = new JButton("Set password");
	// font
	private Font font = new Font("Song style", Font.BOLD, 60);
	private Font font1 = new Font("Song style", Font.BOLD, 25);
	private String user;
	private JFrame frame;

	public Admin() {
		// Change background image
		Icon i = new ImageIcon("img\\account.jpg");
		JLabel Label = new JLabel(i);
		Label.setBounds(0, 0, 1200, 800);
		// Panel layout is empty
		jPanel2.setLayout(null);
		// Label
		jLabel.setFont(font);
		jLabel.setBounds(460, 50, 800, 70);
		// button
		button.setFont(font1);
		button2.setFont(font1);
		button3.setFont(font1);
		button4.setFont(font1);
		button5.setFont(font1);
		button6.setFont(font1);
		button7.setFont(font1);
		button.setBounds(150, 250, 250, 50);
		button2.setBounds(150, 310, 250, 50);
		button3.setBounds(150, 370, 250, 50);
		button4.setBounds(150, 430, 250, 50);
		button5.setBounds(420, 250, 250, 50);
		button6.setBounds(420, 310, 250, 50);
		button7.setBounds(420, 370, 250, 50);

		button.setBackground(Color.cyan);
		button2.setBackground(Color.cyan);
		button3.setBackground(Color.cyan);
		button4.setBackground(Color.cyan);
		button5.setBackground(Color.cyan);
		button6.setBackground(Color.cyan);
		button7.setBackground(Color.cyan);

		//Add event
		add();
		
		button.setOpaque(false);
		button2.setOpaque(false);
		button3.setOpaque(false);
		button4.setOpaque(false);
		button5.setOpaque(false);
		button6.setOpaque(false);
		button7.setOpaque(false);

		jPanel2.add(button);
		jPanel2.add(button2);
		jPanel2.add(button3);
		jPanel2.add(button4);
		jPanel2.add(button5);
		jPanel2.add(button6);
		jPanel2.add(button7);
		jPanel2.add(jLabel);
		jPanel2.add(Label);
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	// Add event
	private void add() {
		// Modify information
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ModifyInformation(user);
			}
		});

		// change Password
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ModifyPassword password = new ModifyPassword(user);
				password.setFrame(frame);
			}
		});
		
		//Book borrowing record
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AdminBorrow();
			}
		});
		
		//sign out
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				new Land();
			}
		});
		
		//Account information
		button5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AdminAccount();
			}
		});
		
		//Modify permissions
		button6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Permissions();
			}
		});
		
		//Set password
		button7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AdminSetPass();
			}
		});
	}
}
