package com.smarttaskmanager.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.smarttaskmanager.dao.TaskDao;
import com.smarttaskmanager.dao.TaskDaoImpl;
import com.smarttaskmanager.model.Task;
import com.smarttaskmanager.model.User;

/**
 * Servlet implementation class TaskServlet
 */
@WebServlet("/taskServlet")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession hs = request.getSession();
		User u = (User) hs.getAttribute("user");
		
		if(hs == null || u == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		TaskDao tdao = new TaskDaoImpl();
		
		List<Task> ls = tdao.showAllTasks(u.getId());
		
		hs.setAttribute("task", ls);
		response.sendRedirect("tasks.jsp");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession hs = request.getSession();
		User u = (User) hs.getAttribute("user");
		
		if(hs == null || u == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		String action = request.getParameter("action");
		
		if(action.equals("add")) {
			
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String priority = request.getParameter("priority");
			String dueDate = request.getParameter("due_date");
			Date date = Date.valueOf(dueDate);
			String status = request.getParameter("status");
			
			Task t = new Task();
			t.setTitle(title);
			t.setDescription(description);
			t.setPriority(priority);
			t.setDueDate(date);
			t.setStatus(status);
			
			TaskDao tdao = new TaskDaoImpl();
			boolean isInsert = tdao.addTask(t, u.getId());
			
			if(isInsert) {
				response.sendRedirect("tasks.jsp");
				return;
			}
			else {
				request.setAttribute("error", "Something Went Wrong. Try Again!");
				request.getRequestDispatcher("add-task.jsp").forward(request, response);
				return;
			}
		}
		
	}

}
