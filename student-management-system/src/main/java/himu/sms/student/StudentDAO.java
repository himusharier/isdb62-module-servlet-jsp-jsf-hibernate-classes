package himu.sms.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import himu.sms.database.DBConnection;

public class StudentDAO {
	private static final String INSERT_STUDENT_SQL = "INSERT INTO students_jsp (name, email, mobile, address) VALUES (?, ?, ?, ?)";
	private static final String SELECT_ALL_STUDENTS_SQL = "SELECT * FROM students_jsp";
	private static final String SELECT_STUDENT_BY_ID_SQL = "SELECT * FROM students_jsp WHERE id = ?";
	private static final String UPDATE_STUDENT_SQL = "UPDATE students_jsp SET name = ?, email = ?, mobile = ?, address = ? WHERE id = ?";
	private static final String DELETE_STUDENT_SQL = "DELETE FROM students_jsp WHERE id = ?";

	public void addStudent(Student student) throws SQLException {
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {

			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getEmail());
			preparedStatement.setString(3, student.getMobile());
			preparedStatement.setString(4, student.getAddress());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("error while adding student!", e);
		}
	}

	public List<Student> getAllStudents() throws SQLException {
		List<Student> students = new ArrayList<>();

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS_SQL);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String mobile = resultSet.getString("mobile");
				String address = resultSet.getString("address");

				students.add(new Student(id, name, email, mobile, address));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("error while fetching students records.", e);
		}
		return students;
	}

	public void deleteStudent(int id) throws SQLException {
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_SQL)) {
			preparedStatement.setInt(1, id);
			preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("error while fetching students records.", e);
		}
	}

	public void getStudentById(int id) throws SQLException {
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID_SQL)) {
			preparedStatement.setInt(1, id);
			preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("error while fetching students records.", e);
		}
	}

}
