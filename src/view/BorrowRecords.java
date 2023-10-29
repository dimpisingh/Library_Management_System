package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import database.ConnectDatabase;


public class BorrowRecords extends JFrame {
	/*
	 * Background image table
	 */
	// panel
	private JPanel jPanel = new JPanel();
	// Layered panes
	private JLayeredPane jLayeredPane = new JLayeredPane();
	//  Label
	private JLabel jLabel = new JLabel("Book borrowing record");
	// font
	private Font font = new Font("Song Dynasty", Font.BOLD, 40);
	// sheet
	public DefaultTableModel model = new DefaultTableModel();

	public BorrowRecords(String user) {
		// Change background image
		Icon i = new ImageIcon("img\\tabletop.jpg");
		JLabel Label = new JLabel(i);
		Label.setBounds(0, 0, 800, 100);
		setLayout(null);
		setSize(800, 600);
		setLocationRelativeTo(null);

		jLabel.setFont(font);
		jLabel.setBounds(315, 80, 400, 100);

		jPanel.add(jLabel);
		// sheet
		model.addColumn("ISBN", new Vector<Integer>());
		model.addColumn("book title", new Vector<Integer>());
		model.addColumn("Borrow date", new Vector<Integer>());
		model.addColumn("Book return date", new Vector<Integer>());
		model.addColumn("state", new Vector<Integer>());
		JTable jTable = new JTable(model);
		JScrollPane pane = new JScrollPane(jTable);
		pane.setBounds(80, 160, 640, 350);
		jLayeredPane.add(pane);

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

		borrow(model, user);

		jPanel.setBounds(0, 0, 800, 200);
		jPanel.setOpaque(false);
		jPanel.setLayout(null);
		setTitle("Book borrowing record");
		// Cannot change the size of the form
		setResizable(false);
		setContentPane(jLayeredPane);
		add(jPanel);
		add(Label);
		setVisible(true);
	}

	private void borrow(DefaultTableModel model, String user) {
		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;
		ResultSet rs;
		String sqlStr = "select * from borrowrecords where user = ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, user);
			rs = preSql.executeQuery();
			boolean flag = false;
			while (rs.next()) {
				flag = true;
				int bookid = rs.getInt(3);
				String bookname = rs.getString(4);
				Date date = rs.getDate(5);
				Date date2 = rs.getDate(6);
				String state = rs.getString(7);
				model.addRow(new Vector<>(Arrays.asList(bookid, bookname, date, date2, state)));
			}
			if (!flag) {
				JOptionPane.showMessageDialog(null, "You haven’t borrowed a book yet! ");
			}
			con.close();
		} catch (SQLException e) {
		}
	}
}
