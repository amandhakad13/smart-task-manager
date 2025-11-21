<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Smart Task Manager</title>
    <link rel="stylesheet" href="css/login.css"/>
</head>
<body>
    <div class="login-container">
        <a href="index.jsp" class="back-btn">← Back to Home</a>
        
        <div class="login-header">
            <h1>Welcome Back</h1>
            <p>Login to access your tasks</p>
        </div>

        <form action="login" method="post" autocomplete="off">
            <div class="form-group">
                <label for="email"><span class="icon">📧</span>Email Address</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>

            <div class="form-group">
                <label for="password"><span class="icon">🔒</span>Password</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
            </div>

            <div class="forgot-password">
                <a href="#">Forgot Password?</a>
            </div>

            <button type="submit" class="login-btn">Login</button>
        </form>

        <div class="register-link">
            Don't have an account? <a href="register.jsp">Register here</a>
        </div>
    </div>
    
    <%
    	String success = (String) session.getAttribute("success");
        String error = (String) request.getAttribute("error");
        String fields = (String) request.getAttribute("fields");
                		
        if(success!=null) {
        	%>
        		<h3><%= success %></h3>
        	<%
        	
        	session.removeAttribute("success");
        }
        
        if(error!=null) {
        	%>
        		<h3><%= error %></h3>
        	<%
        	
        	request.removeAttribute("error");
        }
        
        if(fields!=null) {
        	%>
        		<h3><%= fields %></h3>
        	<%
        	
        	request.removeAttribute("fields");
        }
    
    %>
</body>
</html>