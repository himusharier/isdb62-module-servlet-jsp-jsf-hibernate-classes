<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Student</title>
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
	.form > form > .yellow-btn:hover {
		text-decoration: underline;
	}
		
</style>
</head>
<body>
<div class="container">
	<h2>Add Student</h2>
	<div class="form">
		<c:if test="${not empty successMessage}">
			<div style="color: green; margin-bottom: 10px;">${successMessage}</div>
		</c:if>
		<c:if test="${not empty errorMessage}">
			<div style="color: red; margin-bottom: 10px;">${errorMessage}</div>
		</c:if>
		<form action="../student/StudentServlet" method="post">
			<input type="hidden" name="action" value="add">
			
	        <label for="name">Name:</label>
	        <input type="text" id="name" name="name" required><br><br>
	
	        <label for="email">Email:</label>
	        <input type="email" id="email" name="email" required><br><br>
	
	        <label for="mobile">Mobile:</label>
	        <input type="tel" id="mobile" name="mobile" required><br><br>
	
	        <label for="address">Address:</label>
	        <input type="text" id="address" name="address" required><br><br>
	
	        <input class="blue-btn" type="submit" value="Submit">
	        <br><br>
			<a class="yellow-btn" href="/student-management-system">⟵ Back to Home</a>
    	</form>
	</div>
</div>
</body>
</html>