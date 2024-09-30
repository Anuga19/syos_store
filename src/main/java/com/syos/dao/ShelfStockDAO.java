package com.syos.dao;

import com.syos.model.ShelfStock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShelfStockDAO {
    private final Connection connection;

    public ShelfStockDAO(Connection connection) {
        this.connection = connection;
    }

    // Retrieve shelf stock for a product by productId
    public ShelfStock getShelfStockByProductId(int productId) throws SQLException {
        String sql = "SELECT * FROM shelf_stock WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new ShelfStock(
                        resultSet.getInt("shelf_stock_id"),
                        resultSet.getInt("product_id"),
                        resultSet.getInt("quantity")
                );
            }
        }
        return null;
    }

    // Update the shelf stock after a sale
//    public void updateShelfStock(int productId, int quantitySold) throws SQLException {
//        String sql = "UPDATE shelf_stock SET quantity_on_shelf = quantity_on_shelf - ? WHERE product_id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, quantitySold);
//            statement.setInt(2, productId);
//            statement.executeUpdate();
//        }
//    }
    public void updateShelfStock(int productId, int quantitySold) throws SQLException {
        String sql = "UPDATE shelf_stock SET quantity = quantity - ? WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantitySold);
            statement.setInt(2, productId);
            statement.executeUpdate();
        }
    }
}