package com.smarttaskmanager.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
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
		
		TaskDao tdao = new TaskDaoImpl();
		
		List<Task> ls = tdao.showAllTasks(u.getId());
		
		if(ls.isEmpty()) {
			
		}
		else {
			request.setAttribute("task", ls);
			request.getRequestDispatcher("tasks.jsp").forward(request, response);
		}
	}

}
