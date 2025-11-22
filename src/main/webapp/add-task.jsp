<%
	User u = (User) session.getAttribute("user");

	if(u==null) {
		response.sendRedirect("login.jsp");
		return;
	}
%>

<%@page import="com.smarttaskmanager.model.User"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Task - Smart Task Manager</title>
    <link rel="stylesheet" href="css/add-task.css"/>
</head>
<body>
    <div class="add-task-container">
        <a href="tasks.jsp" class="back-btn">← Back to Home</a>
        
        <div class="form-header">
            <h1>Add New Task</h1>
            <p>Create a new task to stay organized</p>
        </div>

        <form action="taskServlet?action=add" method="post" autocomplete="off">
            <div class="form-group">
                <label for="title"><span class="icon">📝</span>Task Title</label>
                <input type="text" id="title" name="title" placeholder="Enter task title" required>
            </div>

            <div class="form-group">
                <label for="description"><span class="icon">📄</span>Description</label>
                <textarea id="description" name="description" placeholder="Enter task description" required></textarea>
            </div>

            <div class="form-group">
                <label for="priority"><span class="icon">⚡</span>Priority</label>
                <select id="priority" name="priority" required>
                    <option value="">Select Priority</option>
                    <option value="LOW">Low Priority</option>
                    <option value="MEDIUM">Medium Priority</option>
                    <option value="HIGH">High Priority</option>
                </select>
            </div>

            <div class="form-group">
                <label for="due_date"><span class="icon">📅</span>Due Date</label>
                <input type="date" id="due_date" name="due_date" required>
            </div>

            <div class="form-group">
                <label for="status"><span class="icon">⏱️</span>Status</label>
                <select id="status" name="status" required>
                    <option value="">Select Status</option>
                    <option value="PENDING">Pending</option>
                    <option value="IN_PROGRESS">In Progress</option>
                    <option value="COMPLETED">Completed</option>
                </select>
            </div>

            <button type="submit" class="add-btn">➕ Add Task</button>
        </form>
    </div>
    
    <%
    	String error = (String) request.getAttribute("error");
                		
        if(error!=null) {
        	%>
        		<h3><%= error %></h3>
        	<%
        	
        	request.removeAttribute("error");
        }
    %>
</body>
</html>