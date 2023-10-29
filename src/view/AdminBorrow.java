package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import database.ConnectDatabase;
import database.FindBook;
import database.FindBorrow;

/**
 * Administrator book borrowing record page
 * 
 */
public class AdminBorrow extends JFrame {
	/*
	 * Background image table
	 * 
	 * drop-down box text box button
	 */
	// panel
	private JPanel jPanel = new JPanel();
	// Layered panes
	private JLayeredPane jLayeredPane = new JLayeredPane();
	// Label
	private JLabel jLabel = new JLabel("Book borrowing record");
	private JLabel jLabel2 = new JLabel("Please select the query method: ");
	// text box
	private JTextField field = new JTextField(20);
	// drop down box
	private JComboBox<String> box = new JComboBox<String>();
	// button
	private JButton button = new JButton("search");
	// font
	private Font font = new Font("Song style", Font.BOLD, 40);
	private Font font2 = new Font("Song style", Font.BOLD, 20);
	private Font font3 = new Font("Song style", Font.BOLD, 18);
	private Font font4 = new Font("Song style", Font.BOLD, 17);
	// sheet
	public DefaultTableModel model = new DefaultTableModel();
	// Store dropdown options
	private String s;
	private int num;

	public AdminBorrow() {
		// Change background image
		Icon i = new ImageIcon("img\\admintop.jpg");
		JLabel Label = new JLabel(i);
		Label.setBounds(0, 0, 850, 100);
		setLayout(null);
		setSize(850, 650);
		setLocationRelativeTo(null);

		jLabel.setFont(font);
		jLabel.setBounds(345, 80, 400, 100);

		jLabel2.setFont(font4);
		jLabel2.setBounds(95, 140, 250, 30);

		// drop down box
		box.addItem("Search by username");
		box.addItem("Search by ISBN");
		box.setFont(font3);
		box.setBounds(95, 175, 160, 30);
		// box.setBackground(Color.cyan);
		box.setOpaque(false);

		// text box
		field.setFont(font2);
		field.setBackground(Color.cyan);
		field.setBounds(325, 175, 200, 30);
		field.setOpaque(false);

		// button
		button.setFont(font2);
		button.setBounds(590, 173, 80, 35);
		button.setBackground(Color.cyan);
		button.setOpaque(false);

		jPanel.add(jLabel);
		jPanel.add(jLabel2);
		jPanel.add(box);
		jPanel.add(field);
		jPanel.add(button);
		// sheet
		model.addColumn("username", new Vector<Integer>());
		model.addColumn("ISBN", new Vector<Integer>());
		model.addColumn("book title", new Vector<Integer>());
		model.addColumn("Borrow date", new Vector<Integer>());
		model.addColumn("Book return date", new Vector<Integer>());
		model.addColumn("̬state", new Vector<Integer>());
		JTable jTable = new JTable(model);
		JScrollPane pane = new JScrollPane(jTable);
		pane.setBounds(80, 220, 690, 340);
		jLayeredPane.add(pane);

		// All borrowing records
		FindBorrow.allborrow(model);
		
		setTitle("Book borrowing record");
		// Cannot change the size of the form
		setResizable(false);
		// Add event
		addEvent();
		JTableHeader head = jTable.getTableHeader();
		// Set header size
		head.setPreferredSize(new Dimension(head.getWidth(), 30));
		// Set header font size
		head.setFont(new Font("Song style", Font.BOLD, 20));
		// head.setForeground(Color.cyan);
		head.setBackground(Color.cyan);
		// Set the row width of the table
		jTable.setRowHeight(30);
		// Set font size in table rows
		jTable.setFont(new Font("Song style", Font.ROMAN_BASELINE, 17));
		/* Center the content in the table */
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jTable.setDefaultRenderer(Object.class, renderer);

		// jPanel.setBackground(Color.blue);
		jPanel.setBounds(0, 0, 850, 250);
		jPanel.setOpaque(false);
		jPanel.setLayout(null);
		setContentPane(jLayeredPane);
		add(jPanel);
		add(Label);
		setVisible(true);
	}

	private void addEvent() {

		// Get dropdown list value
		s = "Search by username";
		box.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					s = (String) e.getItem();
				}
			}
		});

		// Add search button event
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				model.setRowCount(0);
				if (s.equals("Search by username")) {
					try {
						String user = field.getText().trim();
						FindBorrow.userborrow(model, user);
					} catch (Exception e1) {
					}
				} else if (s.equals("Search by ISBN")) {
					try {
						num = Integer.parseInt(field.getText().trim());
						FindBorrow.bookidborrow(model, num);
					} catch (Exception e1) {
					}
				}
				field.setText("");
			}
		});
	}
}
