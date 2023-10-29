package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 * Library management interface
 *  
 */
public class BookAdmin {
	/*
	 * Label
	 *
	 * Five buttons: Add book information, Modify book information, Delete book information, Add book category, Modify book category
	 * 
	 */
	// panel
	public JPanel jPanel2 = new JPanel();
	// Label
	private JLabel jLabel = new JLabel("library management");
	// button
	private JButton button = new JButton("Add book information");
	private JButton button2 = new JButton("Modify book information");
	private JButton button3 = new JButton("Delete book information");
	private JButton button4 = new JButton("Add book category");
	private JButton button5 = new JButton("Edit book category");
	// font
	private Font font = new Font("Song style", Font.BOLD, 60);
	private Font font1 = new Font("Song style", Font.BOLD, 25);
	// Table : A table used to update the book search interface
	public DefaultTableModel model = new DefaultTableModel();

	public BookAdmin() {
		// Change background image
		Icon i = new ImageIcon("img\\bookadmin.jpg");
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
		button.setBounds(150, 190, 250, 50);
		button2.setBounds(150, 250, 250, 50);
		button3.setBounds(150, 310, 250, 50);
		button4.setBounds(150, 370, 250, 50);
		button5.setBounds(150, 430, 250, 50);

		button.setBackground(Color.cyan);
		button2.setBackground(Color.cyan);
		button3.setBackground(Color.cyan);
		button4.setBackground(Color.cyan);
		button5.setBackground(Color.cyan);

		button.setOpaque(false);
		button2.setOpaque(false);
		button3.setOpaque(false);
		button4.setOpaque(false);
		button5.setOpaque(false);
		
		//Add event
		add();
		
		jPanel2.add(button);
		jPanel2.add(button2);
		jPanel2.add(button3);
		jPanel2.add(button4);
		jPanel2.add(button5);
		jPanel2.add(jLabel);
		jPanel2.add(Label);
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	
	// Add event
	private void add() {
		// Add book information
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddBook addBook = new AddBook();
				addBook.setModel(model);
			}
		});

		// Modify book information
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ModifyBook modifyBook = new ModifyBook();
				modifyBook.setModel(model);
			}
		});

		// Delete book information
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DeleteBook deleteBook = new DeleteBook();
				deleteBook.setModel(model);
			}
		});

		// Add book category
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AddCategory();
			}
		});

		// Edit book category
		button5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ModifyCategory();
			}
		});

	}
}
