package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class UpdateAdmin {
	//check registered user
	public static boolean sureuser(String user) {
		Connection con = ConnectDatabase.connectDB();

		PreparedStatement preSql;

		ResultSet rs;

		String sqlStr = "select * from usertable where user = ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, user);
			rs = preSql.executeQuery();
			boolean flag = false;
			while(rs.next()) {
				flag = true;
				return true;
			}
			if(!flag) {
				return false;
			}
			con.close();
			return false;
		} catch (SQLException e) {
			return false;
		}
	}
	
	//delete user
	public static void deleteuser(String user) {
		Connection con = ConnectDatabase.connectDB();

		PreparedStatement preSql;


		String sqlStr = "delete from usertable where user = ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, user);
			int ok = preSql.executeUpdate();
			con.close();
		} catch (SQLException e) {
		}
	}
	
	//update user
	public static void updateuser(String user) {
		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;

		String sqlStr = "update usertable  set admin = ? where user = ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setInt(1, 0);
			preSql.setString(2, user);
			int ok = preSql.executeUpdate();
			con.close();
		} catch (SQLException e) {
		}
	}
	
	//change admin
	public static void updateadmin(String user) {
		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;

		String sqlStr = "update usertable  set admin = ? where user = ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setInt(1, 1);
			preSql.setString(2, user);
			int ok = preSql.executeUpdate();
			con.close();
		} catch (SQLException e) {
		}
	}
	
	//update password
	public static void updatepass(String user,String password) {
		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;

		String sqlStr = "update usertable  set password = ? where user = ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, password);
			preSql.setString(2, user);
			int ok = preSql.executeUpdate();
			con.close();
		} catch (SQLException e) {
		}
	}
}