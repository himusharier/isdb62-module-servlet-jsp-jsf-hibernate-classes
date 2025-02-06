<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="himu.sms.student.StudentDAO, himu.sms.student.Student" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Student</title>
<style>
	body {
		font-family: arial;
		font-size: 14px;
	}
	.container {
		width: 250px;
		margin: 0 auto;
		margin-top: 100px;
	}
	.container > h2 {
		font-size: 22px;
		font-weight: 400;
		margin-bottom: 30px;
		border-bottom: 2px solid black;
	}
	.form {
		
	}
	.form > form > label {
		display: inline-block;
		width: 60px;
		vertical-align: top;
	}
	.form > form > .blue-btn {
		background-color: #1A5ED7;
		color: white;
		text-decoration: none;
		padding: 5px 20px;
		border-radius: 4px;
		border: none;
		cursor: pointer;
	}
	.form > form > .yellow-btn {
		background-color: #FBCA2C;
		color: black;
		text-decoration: none;
		padding: 5px 20px;
		border-radius: 4px;
		border: none;
		cursor: pointer;
		margin-top: 10px;
	}
	.form > form > .green-btn {
		background-color: #1E7347;
		color: white;
		text-decoration: none;
		padding: 5px 20px;
		border-radius: 4px;
		border: none;
		cursor: pointer;
		margin-top: 10px;
	}
	.form > form > .green-btn:hover {
		text-decoration: underline;
	}
	.form > form > .yellow-btn:hover {
		text-decoration: underline;
	}
	.form > form > textarea {
		width: 170px;
	}
}
		
</style>
</head>
<body>
<div class="container">
	<h2>Update Student</h2>
	<div class="form">
		<c:if test="${not empty successMessage}">
			<div style="color: green; margin-bottom: 10px;">${successMessage}</div>
		</c:if>
		<c:if test="${not empty errorMessage}">
			<div style="color: red; margin-bottom: 10px;">${errorMessage}</div>
		</c:if>
		<%
            int id = Integer.parseInt(request.getParameter("id"));
            StudentDAO studentDAO = new StudentDAO();
            Student student = studentDAO.getStudentById(id);
        %>
		<form action="StudentServlet" method="post">
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="id" value="<%= student.getId() %>">
			
	        <label for="name">Name:</label>
	        <input type="text" id="name" name="name" value="<%= student.getName() %>" required><br><br>
	
	        <label for="email">Email:</label>
	        <input type="email" id="email" name="email" value="<%= student.getEmail() %>" required><br><br>
	
	        <label for="mobile">Mobile:</label>
	        <input type="tel" id="mobile" name="mobile" value="<%= student.getMobile() %>" required><br><br>
	
	        <label for="address">Address:</label>
	        <!-- <input type="text" id="address" name="address" required><br><br> -->
	        <textarea rows="2" id="address" name="address"><%= student.getAddress() %></textarea>
	
			<br><br>
	        <input class="blue-btn" type="submit" value="Update">
	        <br><br><br>
			<a class="green-btn" href="list-students.jsp">⟵ Back to List</a>
	        <br><br>
			<a class="yellow-btn" href="/student-management-system">⟵ Back to Home</a>
    	</form>
	</div>
</div>
</body>
</html>