package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Category {
	public Category() {
	}
	//add category
	public static boolean addcategory(String category) {
		Connection con = ConnectDatabase.connectDB();

		PreparedStatement preSql;

		String sqlStr = "insert into bookcategory values (?)";

		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, category);
			int ok = preSql.executeUpdate();
			con.close();
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Category already exists", "warn", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	//Set Cateogry
	public static boolean setcategory(String category,String category2) {
		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;

		String sqlStr = "update bookcategory  set Category = ? where Category = ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, category2);
			preSql.setString(2, category);
			int ok = preSql.executeUpdate();
			con.close();
			if(ok==0) {
				JOptionPane.showMessageDialog(null, "Category does not exist", "warn", JOptionPane.WARNING_MESSAGE);
				return false;
			}
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Category does not exist", "warn", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
}