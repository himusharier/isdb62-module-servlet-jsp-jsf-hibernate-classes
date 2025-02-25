package com.himu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

	// Method to create a new product
	public void create(Product product) throws Exception {
		String SQL = "INSERT INTO product_jsf_midexam (name, price, brand) VALUES (?, ?, ?)";
		try (Connection connection = DatabaseConfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setString(1, product.getName());
			preparedStatement.setDouble(2, product.getPrice());
			preparedStatement.setString(3, product.getBrand());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error while adding product!", e);
		}
	}

	// Method to fetch all products
	public List<Product> readAll() throws Exception {
		List<Product> products = new ArrayList<>();
		String SQL = "SELECT * FROM product_jsf_midexam ORDER BY id";
		try (Connection connection = DatabaseConfig.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL)) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Double price = resultSet.getDouble("price");
				String brand = resultSet.getString("brand");

				products.add(new Product(id, name, price, brand));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error while fetching records.", e);
		}
		return products;
	}

	// Method to update a product
	public void update(Product product) throws Exception {
		String SQL = "UPDATE product_jsf_midexam SET name=?, price=?, brand=? WHERE id=?";
		try (Connection connection = DatabaseConfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setString(1, product.getName());
			preparedStatement.setDouble(2, product.getPrice());
			preparedStatement.setString(3, product.getBrand());
			preparedStatement.setInt(4, product.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error while updating product!", e);
		}
	}

	// Method to delete a product
	public void delete(int id) throws Exception {
		String SQL = "DELETE FROM product_jsf_midexam WHERE id=?";
		try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error while deleting product!", e);
		}
	}
}