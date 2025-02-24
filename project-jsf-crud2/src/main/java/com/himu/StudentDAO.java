package com.himu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

	public void create(Student student) throws Exception {
		String SQL = "INSERT INTO student_jsf_crud (name, email, course) VALUES (?, ?, ?)";
		try (Connection connection = DatabaseConfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getEmail());
			preparedStatement.setString(3, student.getCourse());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("error while adding student!", e);
		}
	}

	public List<Student> readAll() throws Exception {
		List<Student> students = new ArrayList<>();
		String SQL = "SELECT * FROM student_jsf_crud ORDER BY id";
		try (Connection connection = DatabaseConfig.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL)) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String course = resultSet.getString("course");

				students.add(new Student(id, name, email, course));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("error while fetching records.", e);
		}
		return students;
	}

	public void update(Student student) throws Exception {
		String SQL = "UPDATE student_jsf_crud SET name=?, email=?, course=? WHERE id=?";
		try (Connection connection = DatabaseConfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getEmail());
			preparedStatement.setString(3, student.getCourse());
			preparedStatement.setInt(5, student.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("error while updating!", e);
		}
	}

	public void delete(int id) throws Exception {
		String SQL = "DELETE FROM student_jsf_crud WHERE id=?";
		try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}

}
