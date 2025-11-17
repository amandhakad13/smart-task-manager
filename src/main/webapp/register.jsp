<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - Smart Task Manager</title>
    <link rel="stylesheet" href="css/register.css"/>
</head>
<body>
    <div class="register-container">
        <a href="index.jsp" class="back-btn">← Back to Home</a>
        
        <div class="register-header">
            <h1>Create Account</h1>
            <p>Join us and start organizing your tasks</p>
        </div>

        <form action="register" method="post" autocomplete="off">
            <div class="form-group">
                <label for="name"><span class="icon">👤</span>Full Name</label>
                <input type="text" id="name" name="name" placeholder="Enter your full name" required>
            </div>

            <div class="form-group">
                <label for="email"><span class="icon">📧</span>Email Address</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>

            <div class="form-group">
                <label for="password"><span class="icon">🔒</span>Password</label>
                <input type="password" id="password" name="password" placeholder="Create a strong password" required>
            </div>

            <button type="submit" class="register-btn">Register</button>
        </form>

        <div class="login-link">
            Already have an account? <a href="login.jsp">Login here</a>
        </div>
    </div>
    
    <%
    	String fields = (String) request.getAttribute("fields");
        String length = (String) request.getAttribute("length");
        String email = (String) request.getAttribute("email");
        String error = (String) request.getAttribute("error");
                		
        if(fields!=null) {
        	%>
        		<h3><%= fields %></h3>
        	<%
        	request.removeAttribute("fields");
        }
        
        if(length!=null) {
        	%>
    		<h3><%= length %></h3>
    		<%
    		request.removeAttribute("length");
        }
        
        if(email!=null) {
        	%>
    		<h3><%= email %></h3>
    		<%
    		request.removeAttribute("email");
        }
        
        if(error!=null) {
        	%>
    		<h3><%= error %></h3>
    		<%
    		request.removeAttribute("error");
        }
    %>
</body>
</html>