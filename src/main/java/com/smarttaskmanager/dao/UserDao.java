package com.smarttaskmanager.dao;

import com.smarttaskmanager.model.User;

public interface UserDao {
	
	public boolean insertDetails(User obj);
	public boolean checkIfEmailAlreadyExists(String email);
	public User checkLoginDetails(String email, String password);
}
