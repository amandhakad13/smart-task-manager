package com.smarttaskmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smarttaskmanager.model.User;
import com.smarttaskmanager.util.DBConnection;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean insertDetails(User obj) {
		
		try {
			Connection con = DBConnection.getConnection();
			String query = "insert into users (name, email, password) values (?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, obj.getName());
			pstmt.setString(2, obj.getEmail());
			pstmt.setString(3, obj.getPassword());
			return pstmt.executeUpdate()>0;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkIfEmailAlreadyExists(String email) {
		
		try {
			Connection con = DBConnection.getConnection();
			String query = "select email from users where email = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User checkLoginDetails(String email, String password) {
		
		User u = null;
		try {
			Connection con = DBConnection.getConnection();
			String query = "select * from users where email = ? and password = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				u = new User();
				
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setRole(rs.getString(5));;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
}
