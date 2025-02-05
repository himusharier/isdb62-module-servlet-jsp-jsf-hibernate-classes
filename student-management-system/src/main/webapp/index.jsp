<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Management System</title>
<style>
	body {
		font-family: arial;
		font-size: 14px;
	}
	.container {
		text-align: center;
		margin-top: 100px;
	}
	.container > h1 {
		font-size: 32px;
		font-weight: 400;
	}
	.buttons {
		margin-top: 50px;
	}
	.buttons > a {
		text-decoration: none;
		padding: 10px 20px;
		border-radius: 7px;
	}
	.buttons > a:hover {
		text-decoration: underline;
	}
	.buttons > .blue-btn {
		background-color: #1A5ED7;
		color: white;
	}
	.buttons > .green-btn {
		background-color: #1E7347;
		color: white;
	}
	.buttons > .yellow-btn {
		background-color: #FBCA2C;
		color: black;
	}
</style>
</head>
<body>
<div class="container">
	<h1>Welcome to the Student Management System</h1>
	<div class="buttons">
		<a class="blue-btn" href="pages/add-student.jsp">Add Student</a>
		<a class="green-btn" href="pages/list-students.jsp">List of Students</a>
		<!-- <a class="yellow-btn" href="pages/update-student.jsp">Update Student</a> -->
	</div>
</div>
</body>
</html>