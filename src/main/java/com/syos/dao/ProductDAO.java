package com.syos.dao;

import com.syos.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class ProductDAO {

    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    // Create or Add a new product
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products (product_code, product_name, price, quantity_available) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getProductCode());
            statement.setString(2, product.getProductName());
            statement.setBigDecimal(3, product.getPrice());
            statement.setInt(4, product.getQuantityAvailable());
            statement.executeUpdate();
        }
    }

    // Read a product by ID
    public Product getProductById(int productId) throws SQLException {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Product(
                            resultSet.getInt("product_id"),
                            resultSet.getString("product_code"),
                            resultSet.getString("product_name"),
                            resultSet.getBigDecimal("price"),
                            resultSet.getInt("quantity_available")
                    );
                }
            }
        }
        return null;
    }

    // Update an existing product
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE products SET product_code = ?, product_name = ?, price = ?, quantity_available = ? WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getProductCode());
            statement.setString(2, product.getProductName());
            statement.setBigDecimal(3, product.getPrice());
            statement.setInt(4, product.getQuantityAvailable());
            statement.setInt(5, product.getProductId());
            statement.executeUpdate();
        }
    }

    // Delete a product by ID
    public void deleteProduct(int productId) throws SQLException {
        String sql = "DELETE FROM products WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            statement.executeUpdate();
        }
    }

    // Get all products
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_code"),
                        resultSet.getString("product_name"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getInt("quantity_available")
                ));
            }
        }
        return products;
    }

    public Product findByProductCode(String productCode) {
        String sql = "SELECT * FROM products WHERE product_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, productCode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_code"),
                        rs.getString("product_name"),
                        rs.getBigDecimal("price"),
                        rs.getInt("quantity_available")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}