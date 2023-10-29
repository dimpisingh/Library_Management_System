package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import database.FindBook;

public class BookSearch {
	
	// Layered panes
	public JLayeredPane jLayeredPane = new JLayeredPane();
	// Label
	private JLabel jLabel = new JLabel("Book search");
	private JLabel jLabel2 = new JLabel("Please select the query method: ");
	// text box
	private JTextField field = new JTextField(25);
	// size
	private Dimension dimension = new Dimension(220, 30);
	// drop down box
	private JComboBox<String> box = new JComboBox<String>();
	// button
	private JButton button = new JButton("search");
	// sheet
	public DefaultTableModel model = new DefaultTableModel();
	// font
	private Font font = new Font("Song Dynasty", Font.BOLD, 60);
	private Font font1 = new Font("Song Dynasty", Font.BOLD, 25);
	private Font font2 = new Font("Song Dynasty", Font.BOLD, 20);
	// Store dropdown options
	private String s;
	private String book;
	private int id;

	public BookSearch() {
		// Change background image
		Icon i = new ImageIcon("img\\booksearch.jpg");
		JLabel Label = new JLabel(i);
		Label.setBounds(0, 0, 1200, 800);
		// Label
		jLabel.setFont(font);
		jLabel.setBounds(485, 35, 800, 100);
		jLabel.setForeground(Color.cyan);

		jLabel2.setFont(font1);
		jLabel2.setBounds(180, 130, 250, 30);
		jLabel2.setForeground(Color.cyan);

		// drop down box
		box.setSize(dimension);
		box.addItem("Find by category");
		box.addItem("Search by book title");
		box.addItem("Search by author");
		box.addItem("Search by ISBN");
		box.setFont(font2);
		box.setBounds(180, 170, 200, 40);
		// box.setBackground(Color.cyan);
		box.setOpaque(false);

		// text box
		field.setFont(font2);
		field.setSize(dimension);
		field.setBackground(Color.cyan);
		field.setBounds(480, 173, 250, 35);
		field.setForeground(Color.cyan);
		field.setOpaque(false);

		// button
		button.setFont(font1);
		button.setBounds(850, 170, 100, 40);
		button.setForeground(Color.cyan);
		button.setBackground(Color.cyan);
		button.setOpaque(false);

		// sheet
		model.addColumn("ISBN", new Vector<Integer>());
		model.addColumn("category", new Vector<Integer>());
		model.addColumn("book title", new Vector<Integer>());
		model.addColumn("author", new Vector<Integer>());
		model.addColumn("the publisher", new Vector<Integer>());
		model.addColumn("state", new Vector<Integer>());
		JTable jTable = new JTable(model);

		JScrollPane pane = new JScrollPane(jTable);
		pane.setBounds(180, 250, 800, 400);

//		for(int k = 0; k < 30; k++) {
//			model.addRow(new Vector<Integer>());
//		}
		FindBook.allbook(model);

		JTableHeader head = jTable.getTableHeader();
		// Set header size
		head.setPreferredSize(new Dimension(head.getWidth(), 30));
		// Set header font size
		head.setFont(new Font("Song Dynasty", Font.BOLD, 20));
		// head.setForeground(Color.cyan);
		head.setBackground(Color.cyan);
		// Set the row width of the table
		jTable.setRowHeight(30);
		// Set font size in table rows
		jTable.setFont(new Font("Song Dynasty", Font.ROMAN_BASELINE, 17));
		/* Center the content in the table */
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jTable.setDefaultRenderer(Object.class, renderer);
		
		//Add event
		addEvent();
		
		// Add layered window
		jLayeredPane.add(Label, new Integer(0), 0);
		jLayeredPane.add(jLabel, new Integer(100), 1);
		jLayeredPane.add(jLabel2, new Integer(100), 2);
		jLayeredPane.add(box, new Integer(100), 3);
		jLayeredPane.add(field, new Integer(100), 4);
		jLayeredPane.add(button, new Integer(100), 5);
		jLayeredPane.add(pane, new Integer(100), 6);
	}

	private void addEvent() {
		
		//Get dropdown list value
		s = "Find by category";
		box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED) {
					s=(String)e.getItem();
				}
			}
		});
		
		//Add search button event
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				model.setRowCount(0);
				if(s.equals("Find by category")) {
					book=field.getText().trim();
					FindBook.findcategory(model, book);
				}else if(s.equals("Search by book title")) {
					book=field.getText().trim();
					FindBook.findbookname(model, book);
				}else if(s.equals("Search by author")) {
					book=field.getText().trim();
					FindBook.findauthor(model, book);
				}else if(s.equals("Search by ISBN")) {
					try {
						id= Integer.parseInt(field.getText().trim());
						FindBook.findbookid(model, id);
					}catch(Exception e1) {
					}
				}
				field.setText("");
			}
		});
	}
}
