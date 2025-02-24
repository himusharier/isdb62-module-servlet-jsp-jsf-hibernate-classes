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
        String SQL = "INSERT INTO product_jsf_crud (name, phone, email, address) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getPhone());
            preparedStatement.setString(3, product.getEmail());
            preparedStatement.setString(4, product.getAddress());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while adding product!", e);
        }
    }

    // Method to fetch all products
    public List<Product> readAll() throws Exception {
        List<Product> products = new ArrayList<>();
        String SQL = "SELECT * FROM product_jsf_crud ORDER BY id";
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                products.add(new Product(id, name, phone, email, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while fetching records.", e);
        }
        return products;
    }

    // Method to update a product
    public void update(Product product) throws Exception {
        String SQL = "UPDATE product_jsf_crud SET name=?, phone=?, email=?, address=? WHERE id=?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getPhone());
            preparedStatement.setString(3, product.getEmail());
            preparedStatement.setString(4, product.getAddress());
            preparedStatement.setInt(5, product.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while updating product!", e);
        }
    }

    // Method to delete a product
    public void delete(int id) throws Exception {
        String SQL = "DELETE FROM product_jsf_crud WHERE id=?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while deleting product!", e);
        }
    }
}