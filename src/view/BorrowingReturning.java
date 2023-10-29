package view;

import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import database.BorrowRecords;
import database.ConnectDatabase;
import database.FindBook;



public class BorrowingReturning {
	/*
	 * One label, book borrowing, one label, text box, button, layered window, large panel as bottom, label, button, added to panel
	 * 
	 */
	// Layered panes
	public JLayeredPane jLayeredPane = new JLayeredPane();
	// Label
	private JLabel jLabel = new JLabel("Book borrowing and returning");
	private JLabel jLabel2 = new JLabel("Please enter the ISBN: ");
	private JLabel jLabel3 = new JLabel("Book title: ");
	private JLabel jLabel4 = new JLabel();
	private JLabel jLabel5 = new JLabel("author: ");
	private JLabel jLabel6 = new JLabel();
	private JLabel jLabel7 = new JLabel("state: ");
	private JLabel jLabel8 = new JLabel();
	private JLabel jLabel9 = new JLabel("ISBN: ");
	private JLabel jLabel10 = new JLabel();

	// text box
	private JTextField field = new JTextField(20);
	// size
	// private Dimension dimension = new Dimension(220, 20);
	// button
	private JButton button = new JButton("Search");
	private JButton button2 = new JButton("Return the book");
	private JButton button3 = new JButton("Borrow");

	// panel
	private JPanel jPanel = new JPanel();
	// font
	private Font font = new Font("Song Dynasty", Font.BOLD, 60);
	private Font font2 = new Font("Song Dynasty", Font.BOLD, 30);
	private Font font3 = new Font("Song Dynasty", Font.BOLD, 26);

	// Receive username for this account
	private String user;

	// Receive book query forms and achieve real-time updates
	private DefaultTableModel model = new DefaultTableModel();
	int id;

	public BorrowingReturning() {
		// Change background image
		Icon i = new ImageIcon("img\\returning.jpg");
		JLabel Label = new JLabel(i);
		Label.setBounds(0, 0, 1200, 800);

		// Label
		jLabel.setFont(font);
		jLabel.setBounds(480, 35, 800, 100);
		jLabel.setForeground(Color.black);

		jLabel2.setFont(font2);
		jLabel2.setBounds(280, 164, 250, 30);
		jLabel2.setForeground(Color.black);

		// text box
		field.setFont(font2);
		field.setBackground(Color.black);
		field.setBounds(480, 164, 250, 30);
		field.setForeground(Color.black);
		field.setOpaque(false);

		// button
		button.setFont(font2);
		button.setBounds(780, 160, 100, 40);
		button.setForeground(Color.black);
		button.setBackground(Color.black);
		button.setOpaque(false);

		// panel
		jPanel.setBounds(270, 220, 620, 450);
		jPanel.setBackground(Color.white);
		jPanel.setLayout(null);


		// Labels and buttons on the panel
		jLabel3.setFont(font2);
		jLabel4.setFont(font2);
		jLabel5.setFont(font2);
		jLabel6.setFont(font2);
		jLabel7.setFont(font2);
		jLabel8.setFont(font2);
		jLabel9.setFont(font2);
		jLabel10.setFont(font2);

		button2.setFont(font3);
		button3.setFont(font3);

		jLabel3.setForeground(Color.black);
		jLabel4.setForeground(Color.black);
		jLabel5.setForeground(Color.black);
		jLabel6.setForeground(Color.black);
		jLabel7.setForeground(Color.black);
		jLabel8.setForeground(Color.black);
		jLabel9.setForeground(Color.black);
		jLabel10.setForeground(Color.black);

		button2.setForeground(Color.black);
		button3.setForeground(Color.black);

		jLabel9.setBounds(100, 20, 100, 50);
		jLabel10.setBounds(200, 20, 400, 50);
		jLabel3.setBounds(100, 90, 100, 50);
		jLabel4.setBounds(200, 90, 400, 50);
		jLabel5.setBounds(100, 160, 100, 50);
		jLabel6.setBounds(200, 160, 400, 50);
		jLabel7.setBounds(100, 230, 100, 50);
		jLabel8.setBounds(200, 230, 400, 50);

		button2.setBounds(80, 310, 90, 50);
		button3.setBounds(460, 310, 90, 50);
		button2.setBackground(Color.black);
		button2.setOpaque(false);
		button3.setBackground(Color.black);
		button3.setOpaque(false);

		// Add Event
		addEvent();

		jPanel.add(jLabel3);
		jPanel.add(jLabel4);
		jPanel.add(jLabel5);
		jPanel.add(jLabel6);
		jPanel.add(jLabel7);
		jPanel.add(jLabel8);
		jPanel.add(jLabel9);
		jPanel.add(jLabel10);
		jPanel.add(button2);
		jPanel.add(button3);

		jPanel.setOpaque(false);

		// Add layered window
		jLayeredPane.add(Label, new Integer(0), 0);
		jLayeredPane.add(jLabel, new Integer(100), 1);
		jLayeredPane.add(jLabel2, new Integer(100), 2);
		jLayeredPane.add(field, new Integer(100), 3);
		jLayeredPane.add(button, new Integer(100), 4);
		jLayeredPane.add(jPanel, new Integer(100), 5);
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	private void addEvent() {
		// Add search button event
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				try {
					id = Integer.parseInt(field.getText().trim());
					field.setText("");
					Connection con = ConnectDatabase.connectDB();
					PreparedStatement preSql;
					ResultSet rs;
					String sqlStr = "select * from booktable where bookid = ?";
					try {
						preSql = con.prepareStatement(sqlStr);
						preSql.setInt(1, id);
						rs = preSql.executeQuery();
						boolean flag = false;
						while (rs.next()) {
							flag = true;
							jLabel10.setText(rs.getString(1));
							jLabel4.setText(rs.getString(3));
							jLabel6.setText(rs.getString(4));
							jLabel8.setText(rs.getString(6));
						}
						if (!flag) {
							JOptionPane.showMessageDialog(null, "Book does not exist", "warn", JOptionPane.WARNING_MESSAGE);
							empty();
						}
						con.close();
					} catch (SQLException e1) {
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Incorrect input", "warn", JOptionPane.WARNING_MESSAGE);
					field.setText("");
				}

			}
		});

		// Add borrow button event
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if (jLabel8.getText().equals("In the library")) {
					id = Integer.parseInt(jLabel10.getText().trim());
					BorrowRecords.Borrow(user, id, jLabel4.getText().trim());
					JOptionPane.showMessageDialog(null, "Borrowing successful! ", "Congratulations", JOptionPane.WARNING_MESSAGE);
					empty();
					model.setRowCount(0);
					FindBook.allbook(model);
				} else if (jLabel8.getText().equals("loan")) {
					JOptionPane.showMessageDialog(null, "This book has been borrowed! ", "warn", JOptionPane.WARNING_MESSAGE);
					empty();
				} else {
					JOptionPane.showMessageDialog(null, "mistake! ", "warn", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		// Add return book button event
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (jLabel8.getText().equals("loan")) {
					if(BorrowRecords.comparison(user, id)) {
						id = Integer.parseInt(jLabel10.getText().trim());
						BorrowRecords.Return(user, id);
						JOptionPane.showMessageDialog(null, "Return the book successfully");
						empty();
						model.setRowCount(0);
						FindBook.allbook(model);
					}else {
						JOptionPane.showMessageDialog(null, "This book is not borrowed by you! ", "Congratulations", JOptionPane.WARNING_MESSAGE);
					}
				} else if (jLabel8.getText().equals("In the library")) {
					JOptionPane.showMessageDialog(null, "This book is in the library! ", "warn", JOptionPane.WARNING_MESSAGE);
					empty();
				} else {
					JOptionPane.showMessageDialog(null, "mistake! ", "warn", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
	
	public void empty() {
		jLabel4.setText("");
		jLabel6.setText("");
		jLabel8.setText("");
		jLabel10.setText("");
	}
	
}
