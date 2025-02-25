package com.himu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

	// Method to create a new employee
	public void create(Employee employee) throws Exception {
		String SQL = "INSERT INTO employee_jsf_crud (name, phone, email, salary, address) VALUES (?, ?, ?, ?, ?)";
		try (Connection connection = DatabaseConfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getPhone());
			preparedStatement.setString(3, employee.getEmail());
			preparedStatement.setInt(4, employee.getSalary());
			preparedStatement.setString(5, employee.getAddress());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error while adding employee!", e);
		}
	}

	// Method to fetch all employees
	public List<Employee> readAll() throws Exception {
		List<Employee> employees = new ArrayList<>();
		String SQL = "SELECT * FROM employee_jsf_crud ORDER BY id";
		try (Connection connection = DatabaseConfig.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL)) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String phone = resultSet.getString("phone");
				String email = resultSet.getString("email");
				int salary = resultSet.getInt("salary");
				String address = resultSet.getString("address");

				employees.add(new Employee(id, name, phone, email, salary, address));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error while fetching records.", e);
		}
		return employees;
	}

	// Method to update a employee
	public void update(Employee employee) throws Exception {
		String SQL = "UPDATE employee_jsf_crud SET name=?, phone=?, email=?, salary=? address=? WHERE id=?";
		try (Connection connection = DatabaseConfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getPhone());
			preparedStatement.setString(3, employee.getEmail());
			preparedStatement.setInt(4, employee.getSalary());
			preparedStatement.setString(5, employee.getAddress());
			preparedStatement.setInt(6, employee.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error while updating employee!", e);
		}
	}

	// Method to delete a employee
	public void delete(int id) throws Exception {
		String SQL = "DELETE FROM employee_jsf_crud WHERE id=?";
		try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error while deleting employee!", e);
		}
	}
}