package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
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

import database.Landing;



public class Land {

	/*
	 * Define the form: one large label, two small labels, two text boxes, two buttons, five panels
	 */
	// Label
	private JLabel jLabel = new JLabel("library management system");
	private JLabel jLabel2 = new JLabel("username: ");
	private JLabel jLabel3 = new JLabel("password: ");

	// font
	private Font font = new Font("Song Dynasty", Font.BOLD, 40);
	private Font font2 = new Font("Song Dynasty", Font.BOLD, 25);
	private Font font3 = new Font("Song Dynasty", Font.BOLD, 20);

	// text box
	private JTextField field = new JTextField(22);
	private JPasswordField field2 = new JPasswordField(22);

	// button
	private JButton button = new JButton("Login");
	private JButton button2 = new JButton("Register");

	// Panel
	private JPanel jPanel = new JPanel();
	private JPanel jPanel2 = new JPanel();
	private JPanel jPanel3 = new JPanel();
	private JPanel jPanel4 = new JPanel();
	private JPanel jPanel5 = new JPanel();
	// form
	private JFrame frame = new JFrame("Login");
	// size
	private Dimension dimension = new Dimension(30, 30);
	private Dimension dimension2 = new Dimension(100, 50);
	
	public String user;
	private String password;

	public Land() {
		
		frame.setTitle("Login");
		// Set size
		frame.setSize(550, 500);
		// center
		frame.setLocationRelativeTo(null);
		// layout is empty
		frame.setLayout(null);

		// Change window icon
		Toolkit t = Toolkit.getDefaultToolkit();
		Image image = t.getImage("img\\top.jpg");
		frame.setIconImage(image);

		// Add component
		addassembly();

		// Set transparency
		transparent();

		// Add event
		addEvent();

		// Change background image
		Icon i = new ImageIcon("img\\land1.jpg");
		JLabel Label = new JLabel(i);
		Label.setBounds(0, 0, 550, 500);
		frame.add(Label);

		// Cannot change the size of the form
		frame.setResizable(false);
		// window close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// window visible
		frame.setVisible(true);
	}

	private void addassembly() {
		// Add font
		jLabel.setFont(font);
		jLabel2.setFont(font2);
		jLabel3.setFont(font2);
		button.setFont(font3);
		button2.setFont(font3);
		field.setFont(font3);
		field2.setFont(font3);
		field.setPreferredSize(dimension);
		field2.setPreferredSize(dimension);
		button.setPreferredSize(dimension2);
		button2.setPreferredSize(dimension2);
//		button.setBackground(Color.pink);
//		button2.setBackground(Color.GRAY);

		jPanel.add(jLabel);
		jPanel2.add(jLabel2);
		jPanel2.add(field);
		jPanel3.add(jLabel3);
		jPanel3.add(field2);
		jPanel4.add(button);
		jPanel5.add(button2);

		jPanel.setBounds(0, 60, 550, 80);
		jPanel2.setBounds(0, 160, 550, 80);
		jPanel3.setBounds(0, 250, 550, 60);
		jPanel4.setBounds(100, 325, 150, 80);
		jPanel5.setBounds(275, 325, 150, 80);

		frame.add(jPanel);
		frame.add(jPanel2);
		frame.add(jPanel3);
		frame.add(jPanel4);
		frame.add(jPanel5);

	}

	private void transparent() {
		// Set transparency
		jLabel.setOpaque(false);
		jLabel2.setOpaque(false);
		jLabel3.setOpaque(false);
		field.setOpaque(false);
		field2.setOpaque(false);
		// button.setOpaque(false);
		// button2.setOpaque(false);
		jPanel.setOpaque(false);
		jPanel2.setOpaque(false);
		jPanel3.setOpaque(false);
		jPanel4.setOpaque(false);
		jPanel5.setOpaque(false);

	}

	private void addEvent() {

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				user = field.getText().trim();
				password = field2.getText().trim();
				if(Landing.test(user, password)) {
//					JOptionPane.showMessageDialog(null, "Landed successfully");
					frame.dispose();
					new MainInterface(user);
				}else {
					empty();
				}
					
			}
		});

		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//frame.setVisible(false);
				frame.dispose();
				new Register();
			}
		});
	}
	private void empty() {
		field.setText("");
		field2.setText("");
	}
}
