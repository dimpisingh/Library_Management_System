package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.FindBorrow;
import database.UpdateAdmin;


public class Permissions extends JFrame {
	
	// Panel
	private JPanel jPanel = new JPanel();
	// Label
	private JLabel jLabel = new JLabel("username: ");
	private JLabel jLabel2 = new JLabel("Options: ");
	// Text box
	private JTextField field = new JTextField(22);	
	// drop down box
	private JComboBox<String> box = new JComboBox<String>();
	// font
	private Font font2 = new Font("Song Dynasty", Font.BOLD, 22);
	private Font font3 = new Font("Song Dynasty", Font.BOLD, 18);
	// Button Modify
	private JButton button = new JButton("Sure");
	
	private String s;

	public Permissions() {
		setSize(400, 450);
		setTitle("Modify permissions");
		// Change background image
		Icon i = new ImageIcon("img\\ah.jpg");
		JLabel Label = new JLabel(i);
		Label.setBounds(0, 0, 400, 100);

		jLabel.setFont(font2);
		jLabel2.setFont(font2);
		field.setFont(font2);
		button.setFont(font3);

		jLabel.setBounds(50, 150, 100, 30);
		field.setBounds(150, 150, 185, 28);
		
		jLabel2.setBounds(50, 225, 100, 30);
		
		button.setBounds(47, 300, 288, 35);
		// drop down box
		box.addItem("delete users");
		box.addItem("Change to user");
		box.addItem("Add as administrator");
		box.setFont(font3);
		box.setBounds(150, 225, 185, 28);
		// box.setBackground(Color.cyan);
		box.setOpaque(false);

		//Add event
		addEvent();
		
		jPanel.add(jLabel);
		jPanel.add(field);
		jPanel.add(jLabel2);
		jPanel.add(box);
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

		// Get dropdown list value
		s = "delete users";
		box.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					s = (String) e.getItem();
				}
			}
		});

		// Add OK button event
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String user = field.getText().trim();
				if(UpdateAdmin.sureuser(user)) {
					if (s.equals("delete users")) {
						UpdateAdmin.deleteuser(user);
						JOptionPane.showMessageDialog(null, "Operation completed");
					} else if (s.equals("Change to user")) {
						UpdateAdmin.updateuser(user);
						JOptionPane.showMessageDialog(null, "Operation completed");
					}else if(s.equals("Add as administrator")) {
						UpdateAdmin.updateadmin(user);
						JOptionPane.showMessageDialog(null, "Operation completed");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Username does not exist", "warn", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}

}
