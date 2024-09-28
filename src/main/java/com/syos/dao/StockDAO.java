package com.syos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StockDAO {
    private final Connection connection;

    public StockDAO(Connection connection) {
        this.connection = connection;
    }

    public void updateStock(int productId, int quantitySold) {
        String sql = "UPDATE stock SET quantity = quantity - ? WHERE product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantitySold);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}