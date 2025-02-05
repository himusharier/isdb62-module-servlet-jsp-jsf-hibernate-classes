<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="himu.sms.student.StudentDAO, himu.sms.student.Student, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of Students</title>
<style>
	body {
		font-family: arial;
		font-size: 14px;
	}
	.container {
		width: 700px;
		margin: 0 auto;
		margin-top: 100px;
	}
	.container > h2 {
		font-size: 22px;
		font-weight: 400;
		margin-bottom: 30px;
		border-bottom: 2px solid black;
	}
    table {
	      width: 100%;
	      margin: 5px auto;
	      border-collapse: collapse;
    }
    th {
	      background-color: #eee;
	      text-align: left;
	      padding: 5px;
    }
    td {
	      text-align: left;
	      padding: 5px;
	      border: 1px solid #ddd;
    }
    table, th, td {
      	border: 1px solid #ddd;
    }
     .container > table > tbody > tr > td > .options {
    	display: flex;
    }
    .update-btn {
    	background-color: #FBCA2C;
		color: black;
		text-decoration: none;
		padding: 5px;
		border-radius: 4px;
		border: none;
		cursor: pointer;
		font-size: 12px;
		margin-right: 5px;
    }
    .update-btn:hover {
    	text-decoration: underline;
    }
    .container > table > tbody > tr > td > div > form > button {
    	background-color: #BB2D3B;
		color: white;
		text-decoration: none;
		padding: 5px;
		border-radius: 4px;
		border: none;
		cursor: pointer;
		font-size: 12px;
    }
    .container > table > tbody > tr > td > div > form > button:hover {
    	text-decoration: underline;
    }
	.yellow-btn {
		background-color: #FBCA2C;
		color: black;
		text-decoration: none;
		padding: 5px 20px;
		border-radius: 4px;
		border: none;
		cursor: pointer;
		margin-top: 10px;
	}
	.yellow-btn:hover {
		text-decoration: underline;
	}
  </style>
</head>
<body>
<div class="container">
	<h2>List of Students</h2>
	<table border="1">
		<thead>
		    <tr>
		      <th>ID</th>
		      <th>Name</th>
		      <th>Email</th>
		      <th>Mobile</th>
		      <th>Address</th>
		      <th>Options</th>
		    </tr>
	  	</thead>
	  	<tbody>
		<%
		StudentDAO studentDAO = new StudentDAO();
		List<Student> studentList = studentDAO.getAllStudents();
		if (studentList != null) {
		  	for (Student student : studentList) {
		%>
			<tr>
				<td><%= student.getId() %></td>
				<td><%= student.getName() %></td>
				<td><%= student.getEmail() %></td>
				<td><%= student.getMobile() %></td>
				<td><%= student.getAddress() %></td>
				<td>
					<div class="options">
						<a class="update-btn" href="update-student.jsp?id=<%= student.getId() %>">Update</a>
						<form action="StudentServlet" method="post">
							<input type="hidden" name="id" value="<%= student.getId() %>">
							<input type="hidden" name="action" value="delete">
							<button class="delete-btn" type="submit" onClick="return confirm('Are you sure to delete this student?');">Delete</button>
						</form>
					</div>
				</td>
			</tr>
		<%
			}
		} else {
		%>
			<tr>
				<td colspan="6">No students available</td>
			</tr>
		<%
		}
		%>
	  	</tbody>
	</table>
    <br><br>
	<a class="yellow-btn" href="/student-management-system">⟵ Back to Home</a>
</div>

</body>
</html>