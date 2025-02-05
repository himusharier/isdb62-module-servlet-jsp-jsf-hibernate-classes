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
					<a href="#">Update</a>
					<form>
					</form>
				</td>
			</tr>
		<%
		}
		} else {
		%>
			<tr>
				<td colspan="5">No students available</td>
			</tr>
		<%
		}
		%>
	  	</tbody>
	</table>
</div>

</body>
</html>