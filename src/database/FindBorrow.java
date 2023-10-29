package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class FindBorrow {
	public FindBorrow() {
	}
	//display all borrowed book
	public static void allborrow(DefaultTableModel model) {

		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;
		ResultSet rs;
		String sqlStr = "select * from borrowrecords";
		try {
			preSql = con.prepareStatement(sqlStr);
			rs = preSql.executeQuery();
			while (rs.next()) {
				String user = rs.getString(2);
				int bookid = rs.getInt(3);
				String bookname = rs.getString(4);
				Date date = rs.getDate(5);
				Date date2 = rs.getDate(6);
				String status = rs.getString(7);
				model.addRow(new Vector<>(Arrays.asList(user,bookid,bookname,date,date2,status)));
			}
			con.close();
		} catch (SQLException e) {
		}
	}
	
	//find user who borrowed
	public static void userborrow(DefaultTableModel model,String user) {

		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;
		ResultSet rs;
		String sqlStr = "select * from borrowrecords where user = ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, user);
			rs = preSql.executeQuery();
			while (rs.next()) {
				int bookid = rs.getInt(3);
				String bookname = rs.getString(4);
				Date date = rs.getDate(5);
				Date date2 = rs.getDate(6);
				String status = rs.getString(7);
				model.addRow(new Vector<>(Arrays.asList(user,bookid,bookname,date,date2,status)));
			}
			con.close();
		} catch (SQLException e) {
		}
	}
	
	//find borrowed book ID
	public static void bookidborrow(DefaultTableModel model,int bookid) {

		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;
		ResultSet rs;
		String sqlStr = "select * from borrowrecords where bookid = ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setInt(1, bookid);
			rs = preSql.executeQuery();
			while (rs.next()) {
				String user = rs.getString(2);
				String bookname = rs.getString(4);
				Date date = rs.getDate(5);
				Date date2 = rs.getDate(6);
				String status = rs.getString(7);
				model.addRow(new Vector<>(Arrays.asList(user,bookid,bookname,date,date2,status)));
			}
			con.close();
		} catch (SQLException e) {
		}
	}
}