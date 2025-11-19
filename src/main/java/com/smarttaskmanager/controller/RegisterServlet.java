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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(name==null || name.trim().isEmpty() ||
		   email==null || email.trim().isEmpty() ||
		   password==null || password.trim().isEmpty()) {
			
			request.setAttribute("fields", "All fields are required!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		
		if(password.length()<6) {
			request.setAttribute("length", "Password must be at least 6 characters!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		
		UserDao udao = new UserDaoImpl();
		boolean isExist = udao.checkIfEmailAlreadyExists(email);
		
		if(isExist) {
			request.setAttribute("email", "Email already registered!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		
		User u = new User();
		u.setName(name);
		u.setEmail(email);
		u.setPassword(password);
		
		boolean isInsert = udao.insertDetails(u);
		
		if(isInsert) {
			HttpSession session = request.getSession();
			session.setAttribute("success", "Registration Successfull");
			response.sendRedirect("login.jsp");
		}
		else {
			request.setAttribute("error", "Something went wrong. Try again!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
	}

}
