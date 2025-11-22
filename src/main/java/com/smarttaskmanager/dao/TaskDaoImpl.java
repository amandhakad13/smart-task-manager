package com.smarttaskmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smarttaskmanager.model.Task;
import com.smarttaskmanager.util.DBConnection;

public class TaskDaoImpl implements TaskDao {

	@Override
	public List<Task> showAllTasks(int id) {
		
		List<Task> ls = new ArrayList<Task>();
		
		try {
			
			Connection con = DBConnection.getConnection();
			String query = "select * from tasks where user_id = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Task t = new Task();
				
				t.setId(rs.getInt(1));
				t.setUserId(rs.getInt(2));
				t.setTitle(rs.getString(3));
				t.setDescription(rs.getString(4));
				t.setPriority(rs.getString(5));
				t.setDueDate(rs.getDate(6));
				t.setStatus(rs.getString(7));
				
				ls.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public boolean addTask(Task obj, int id) {
		
		try {
			Connection con = DBConnection.getConnection();
			String query = "insert into tasks (user_id, title, description, priority, due_date, status) values (?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setString(2, obj.getTitle());
			pstmt.setString(3, obj.getDescription());
			pstmt.setString(4, obj.getPriority());
			pstmt.setDate(5, obj.getDueDate());
			pstmt.setString(6, obj.getStatus());
			
			return pstmt.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
