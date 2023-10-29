package view;

import java.awt.Color;
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
import javax.swing.table.DefaultTableModel;

import database.Book;
import database.FindBook;

public class ModifyBook extends JFrame{
	// Panel
	private JPanel jPanel = new JPanel();
	// Label
	private JLabel jLabel = new JLabel("Modify book information");
	private JLabel jLabel2 = new JLabel("Category: ");
	private JLabel jLabel3 = new JLabel("Book title: ");
	private JLabel jLabel4 = new JLabel("author: ");
	private JLabel jLabel5 = new JLabel("the publisher: ");
	private JLabel jLabel6 = new JLabel("state: ");
	private JLabel jLabel7 = new JLabel("ISBN: ");
	// text box
	private JTextField field = new JTextField(20);
	private JTextField field2 = new JTextField(20);
	private JTextField field3 = new JTextField(20);
	private JTextField field4 = new JTextField(20);
	// drop down box
	private JComboBox<String> box = new JComboBox<String>();
	private JComboBox<String> box1 = new JComboBox<String>();
	// button
	private JButton button = new JButton("Sure");
	// font
	private Font font = new Font("Song Dynasty", Font.BOLD, 40);
	private Font font2 = new Font("Song Dynasty", Font.BOLD, 25);
	private Font font3 = new Font("Song Dynasty", Font.BOLD, 20);
	
	// Table A table used to update the book search interface
	public DefaultTableModel model = new DefaultTableModel();
		
	private String s;
	private String s1;

	public ModifyBook() {
		// Change background image
		Icon i = new ImageIcon("img\\admintop.jpg");
		JLabel Label = new JLabel(i);
		Label.setBounds(0, 0, 580, 100);
		setLayout(null);
		setSize(580, 650);
		setLocationRelativeTo(null);
		
		setTitle("Modify book information");

		jLabel.setFont(font);
		jLabel.setBounds(155, 80, 400, 100);

		jLabel7.setFont(font2);
		jLabel7.setBounds(125, 200, 250, 30);
		
		jLabel2.setFont(font2);
		jLabel2.setBounds(125, 250, 250, 30);

		jLabel3.setFont(font2);
		jLabel3.setBounds(125, 300, 250, 30);
		
		jLabel4.setFont(font2);
		jLabel4.setBounds(125, 350, 250, 30);
		
		jLabel5.setFont(font2);
		jLabel5.setBounds(125, 400, 250, 30);
		
		jLabel6.setFont(font2);
		jLabel6.setBounds(125, 450, 250, 30);
		// drop down box
		box.addItem("Please select a category");
		Book.findcategory(box);
		box.setFont(font3);
		box.setBounds(245, 250, 200, 30);
		// box.setBackground(Color.cyan);
		box.setOpaque(false);
		
		// drop down box
		box1.addItem("In the library");
		box1.addItem("loan");
		//Book.findcategory(box);
		box1.setFont(font3);
		box1.setBounds(245, 450, 200, 30);
		// box.setBackground(Color.cyan);
		box1.setOpaque(false);

		// text box
		field4.setFont(font3);
		field4.setBackground(Color.cyan);
		field4.setBounds(245, 200, 200, 30);
		field4.setOpaque(false);
		
		field.setFont(font3);
		field.setBackground(Color.cyan);
		field.setBounds(245, 300, 200, 30);
		field.setOpaque(false);
		
		field2.setFont(font3);
		field2.setBackground(Color.cyan);
		field2.setBounds(245, 350, 200, 30);
		field2.setOpaque(false);
		
		field3.setFont(font3);
		field3.setBackground(Color.cyan);
		field3.setBounds(245, 400, 200, 30);
		field3.setOpaque(false);

		// button
		button.setFont(font2);
		button.setBounds(120, 510, 325, 35);
		button.setBackground(Color.cyan);
		button.setOpaque(false);

		jPanel.add(jLabel);
		jPanel.add(jLabel2);
		jPanel.add(jLabel3);
		jPanel.add(jLabel4);
		jPanel.add(jLabel5);
		jPanel.add(jLabel6);
		jPanel.add(jLabel7);
		jPanel.add(box);
		jPanel.add(box1);
		jPanel.add(field);
		jPanel.add(field2);
		jPanel.add(field3);
		jPanel.add(field4);
		jPanel.add(button);
		
		add();
		
		// jPanel.setBackground(Color.blue);
		jPanel.setBounds(0, 0, 850, 650);
		jPanel.setOpaque(false);
		jPanel.setLayout(null);
		// Cannot change the size of the form
		setResizable(false);
		add(jPanel);
		add(Label);
		setVisible(true);
	}
	
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	
	private void add() {
		//Get dropdown list value
		s = "Please select a category";
		box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED) {
					s=(String)e.getItem();
				}
			}
		});
		
		//Get dropdown list value
		s1 = "In the library";
		box1.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED) {
					s1=(String)e.getItem();
				}
			}
		});
				
		//Add search button event
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String bookname = field.getText().trim();
					String author = field2.getText().trim();
					String press = field3.getText().trim();
					int bookid = Integer.parseInt(field4.getText().trim());
					Book.modifybook(bookid, s, bookname, author, press, s1);
					model.setRowCount(0);
					FindBook.allbook(model);
					JOptionPane.showMessageDialog(null, "Successful operation");
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Incorrect input", "warn", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
	}

}
