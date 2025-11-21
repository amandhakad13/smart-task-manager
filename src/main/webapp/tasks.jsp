<%
	User u = (User) session.getAttribute("user");

	if(u==null) {
		response.sendRedirect("login.jsp");
		return;
	}
%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.smarttaskmanager.model.Task"%>
<%@page import="java.util.List"%>
<%@page import="com.smarttaskmanager.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Smart Task Manager</title>
    <link rel="stylesheet" href="css/tasks.css"/>
</head>
<body>
    <div class="header">
        <h1>🎯 Smart Task Manager</h1>
        <div class="header-right">
            <a href="tasks.jsp">Home</a>
            <span class="username">👤 <%= u.getName() %></span>
            <a href="logout">Logout</a>
        </div>
    </div>

    <div class="container">
        <div class="task-section">
            <h2 class="section-title">📋 My Tasks</h2>
            
            <% 
				List<Task> ls = (List<Task>) session.getAttribute("task");
				
				if(ls.isEmpty()) {
					%>
						<h3>No Task Found</h3>
					<%
				}
				else {
					for(Task t : ls) {
						%>
						<div class="task-card">
			                <div class="task-header">
			                    <div class="task-title"><%= t.getTitle() %></div>
			                    <span class="task-priority priority-high"><%= t.getPriority() %></span>
			                </div>
			                <div class="task-description"><%= t.getDescription() %></div>
			                <div class="task-meta">
			                    <div class="meta-item"><span class="meta-icon">📅</span> Due: <%= t.getDueDate() %></div>
			                    <div class="meta-item"><span class="meta-icon">⏱️</span> <span class="task-status status-progress"><%= t.getStatus() %></span></div>
			                </div>
			                <div class="task-actions">
			                    <button class="btn btn-edit" onclick="location.href='editTask.jsp?id=1'">✏️ Edit</button>
			                    <button class="btn btn-complete" onclick="location.href='completeTask?id=1'">✓ Mark Complete</button>
			                    <button class="btn btn-delete" onclick="if(confirm('Delete this task?')) location.href='deleteTask?id=1'">🗑️ Delete</button>
			                </div>
			            </div>
						<%
					}
				}
			
			%>
        </div>

        <button class="add-task-btn" onclick="location.href='addTask.jsp'">➕ Add New Task</button>
    </div>
</body>
</html>