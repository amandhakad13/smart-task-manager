package com.smarttaskmanager.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.smarttaskmanager.dao.UserDao;
import com.smarttaskmanager.dao.UserDaoImpl;
import com.smarttaskmanager.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserDao udao = new UserDaoImpl();
		
		User u = udao.checkLoginDetails(email, password);
		
		if(u==null) {
			request.setAttribute("error", "Invalid Email/Password");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("user", u);
			
			if(u.getRole()=="ADMIN"||u.getRole().equals("ADMIN")) {
				response.sendRedirect("admin-dashboard.jsp");
			}
			else {
				response.sendRedirect("taskServlet");
			}
		}
		
	}

}
