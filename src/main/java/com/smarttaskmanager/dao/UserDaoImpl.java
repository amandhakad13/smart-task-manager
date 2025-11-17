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
}
