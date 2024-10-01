package com.syos.dao;

import com.syos.model.Batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchDAO {
    private final Connection connection;

    public BatchDAO(Connection connection) {
        this.connection = connection;
    }

    // Retrieve batches for a product
    public List<Batch> getBatchesForProduct(int productId) throws SQLException {
        List<Batch> batches = new ArrayList<>();
        String sql = "SELECT * FROM batches WHERE product_id = ? ORDER BY date_received ASC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                batches.add(new Batch(
                        resultSet.getInt("batch_id"),
                        resultSet.getInt("product_id"),
                        resultSet.getString("batch_number"),
                        resultSet.getDate("date_received"),
                        resultSet.getDate("expiry_date"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("supplier_id")
                ));
            }
        }
        return batches;
    }

    // Update batch quantity after a sale
//    public void updateBatchQuantity(int batchId, int quantitySold) throws SQLException {
//        String sql = "UPDATE batches SET quantity = quantity - ? WHERE batch_id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, quantitySold);
//            statement.setInt(2, batchId);
//            statement.executeUpdate();
//        }
//    }

    public void updateBatchQuantity(int batchId, int newQuantity) throws SQLException {
        String sql = "UPDATE batches SET quantity = ? WHERE batch_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newQuantity);
            statement.setInt(2, batchId);
            statement.executeUpdate();
        }
    }
}