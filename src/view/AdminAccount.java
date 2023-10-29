package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import database.ConnectDatabase;

//Account information interface

public class AdminAccount extends JFrame {
	/*
	 * Background image table
	 */
	// panel
	private JPanel jPanel = new JPanel();
	// Layered panes
	private JLayeredPane jLayeredPane = new JLayeredPane();
	// Label
	private JLabel jLabel = new JLabel("Account information");
	// font
	private Font font = new Font("Song style", Font.BOLD, 40);
	// sheet
	public DefaultTableModel model = new DefaultTableModel();

	public AdminAccount() {
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
		model.addColumn("username", new Vector<Integer>());
		model.addColumn("student ID", new Vector<Integer>());
		model.addColumn("Name", new Vector<Integer>());
		model.addColumn("Is it an administrator?", new Vector<Integer>());
		JTable jTable = new JTable(model);
		JScrollPane pane = new JScrollPane(jTable);
		pane.setBounds(80, 160, 640, 350);
		jLayeredPane.add(pane);

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
		
		borrow(model);

		jPanel.setBounds(0, 0, 800, 200);
		jPanel.setOpaque(false);
		jPanel.setLayout(null);
		setContentPane(jLayeredPane);
		setTitle("Account information");
		// Cannot change the size of the form
		setResizable(false);
		add(jPanel);
		add(Label);
		setVisible(true);
	}

	private void borrow(DefaultTableModel model) {
		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;
		ResultSet rs;
		String sqlStr = "select * from usertable";
		try {
			preSql = con.prepareStatement(sqlStr);
			rs = preSql.executeQuery();
			while (rs.next()) {
				String user = rs.getString(1);
				String studentid = rs.getString(2);
				String name = rs.getString(3);
				int flag = rs.getInt(5);
				String admin;
				if (flag == 1) {
					admin = "yes";
				} else {
					admin = "no";
				}
				model.addRow(new Vector<>(Arrays.asList(user, studentid, name, admin)));
			}
			con.close();
		} catch (SQLException e) {
		}
	}
}
