package com.smarttaskmanager.dao;

import java.util.List;

import com.smarttaskmanager.model.Task;

public interface TaskDao {
	
	public List<Task> showAllTasks(int id);
}
