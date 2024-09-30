package com.syos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StockDAO {
    private final Connection connection;

    public StockDAO(Connection connection) {
        this.connection = connection;
    }

    public void updateStockQuantity(int productId, int quantitySold) throws SQLException {
        String sql = "UPDATE stock SET quantity = quantity - ? WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantitySold);
            statement.setInt(2, productId);
            statement.executeUpdate();
        }
    }
}