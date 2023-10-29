package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.UpdateAdmin;

public class AdminSetPass extends JFrame{
	// panel
	private JPanel jPanel = new JPanel();
	// Label
	private JLabel jLabel = new JLabel("username: ");
	private JLabel jLabel2 = new JLabel("password: ");
	// text box
	private JTextField field = new JTextField(22);
	private JPasswordField field2 = new JPasswordField(22);
	// font
	private Font font2 = new Font("Song style", Font.BOLD, 22);
	private Font font3 = new Font("Song style", Font.BOLD, 18);
	// Button Modify
	private JButton button = new JButton("Sure");
	

	public AdminSetPass() {
		setSize(400, 450);
		setTitle("Set password");
		// Change background image
		Icon i = new ImageIcon("img\\ah.jpg");
		JLabel Label = new JLabel(i);
		Label.setBounds(0, 0, 400, 100);

		jLabel.setFont(font2);
		jLabel2.setFont(font2);
		field.setFont(font2);
		field2.setFont(font2);
		button.setFont(font3);

		jLabel.setBounds(50, 150, 100, 30);
		field.setBounds(150, 150, 185, 28);
		
		jLabel2.setBounds(50, 225, 100, 30);
		field2.setBounds(150, 225, 185, 28);
		button.setBounds(47, 300, 288, 35);
		

		//Add event
		addEvent();
		
		jPanel.add(jLabel);
		jPanel.add(field);
		jPanel.add(jLabel2);
		jPanel.add(field2);
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
	
	private void addEvent() {

		// Add OK button event
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String user = field.getText().trim();
				String password = field2.getText().trim();
				if(UpdateAdmin.sureuser(user)) {
					UpdateAdmin.updatepass(user, password);
					JOptionPane.showMessageDialog(null, "Operation completed");
				}else {
					JOptionPane.showMessageDialog(null, "Username does not exist", "warn", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}

}
