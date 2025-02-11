package himu.ump.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import himu.ump.bean.User;

public class UserDAO {
	private String jdbcURL = "jdbc:postgresql://localhost:5432/himu_database";
	private String jdbcUsername = "postgres";
	private String jdbcPassword = "isdb62";
	private String jdbcDriver = "org.postgresql.Driver";

	private static final String INSERT_USERS_SQL = "INSERT INTO jsp_ump_project_users (name, email, country) VALUES (?, ?, ?)";
	private static final String SELECT_USER_BY_ID = "SELECT * FROM jsp_ump_project_users WHERE id = ?";
	private static final String SELECT_ALL_USERS = "SELECT * FROM jsp_ump_project_users ORDER BY id ASC";
	private static final String DELETE_USERS_SQL = "DELETE FROM jsp_ump_project_users WHERE id = ?";
	private static final String UPDATE_USERS_SQL = "UPDATE jsp_ump_project_users SET name = ?, email = ?, country = ? WHERE id = ?";

	public UserDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	// insert user:
	public void insertUser(User user) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// select user by id:
	public User selectUser(int id) {
		User user = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String country = resultSet.getString("country");
				user = new User(id, name, email, country);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	// select all users:
	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String country = resultSet.getString("country");
				users.add(new User(id, name, email, country));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	// update user:
	public boolean updateUser(User user) {
		boolean rowUpdated = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			preparedStatement.setInt(4, user.getId());

			rowUpdated = preparedStatement.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}

	// delete user:
	public boolean deleteUser(int id) {
		boolean rowDeleted = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL)) {
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}

}
