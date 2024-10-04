//package com.syos.dao;
//
//import com.syos.model.Batch;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BatchDAO {
//    private final Connection connection;
//
//    public BatchDAO(Connection connection) {
//        this.connection = connection;
//    }
//
//    // Retrieve batches for a product
//    public List<Batch> getBatchesForProduct(int productId) throws SQLException {
//        List<Batch> batches = new ArrayList<>();
//        String sql = "SELECT * FROM batches WHERE product_id = ? ORDER BY date_received ASC";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, productId);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                batches.add(new Batch(
//                        resultSet.getInt("batch_id"),
//                        resultSet.getInt("product_id"),
//                        resultSet.getString("batch_number"),
//                        resultSet.getDate("date_received"),
//                        resultSet.getDate("expiry_date"),
//                        resultSet.getInt("quantity"),
//                        resultSet.getInt("supplier_id")
//                ));
//            }
//        }
//        return batches;
//    }
//
//    // Update batch quantity after a sale
////    public void updateBatchQuantity(int batchId, int quantitySold) throws SQLException {
////        String sql = "UPDATE batches SET quantity = quantity - ? WHERE batch_id = ?";
////        try (PreparedStatement statement = connection.prepareStatement(sql)) {
////            statement.setInt(1, quantitySold);
////            statement.setInt(2, batchId);
////            statement.executeUpdate();
////        }
////    }
//
//    public void updateBatchQuantity(int batchId, int newQuantity) throws SQLException {
//        String sql = "UPDATE batches SET quantity = ? WHERE batch_id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, newQuantity);
//            statement.setInt(2, batchId);
//            statement.executeUpdate();
//        }
//    }
//}

//package com.syos.dao;
//
//import com.syos.interfaces.IBatchDAO;
//import com.syos.model.Batch;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BatchDAO implements IBatchDAO {
//
//    private final Connection connection;
//
//    public BatchDAO(Connection connection) {
//        this.connection = connection;
//    }
//
//    // Retrieve batches for a product, ordered by date_received for FIFO
//    @Override
//    public List<Batch> getBatchesForProduct(int productId) throws SQLException {
//        List<Batch> batches = new ArrayList<>();
//        String sql = "SELECT * FROM batches WHERE product_id = ? ORDER BY date_received ASC";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, productId);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    batches.add(new Batch(
//                            resultSet.getInt("batch_id"),
//                            resultSet.getInt("product_id"),
//                            resultSet.getString("batch_number"),
//                            resultSet.getDate("date_received"),
//                            resultSet.getDate("expiry_date"),
//                            resultSet.getInt("quantity"),
//                            resultSet.getInt("supplier_id")
//                    ));
//                }
//            }
//        }
//        return batches;
//    }
//
//    // Update batch quantity after stock is used
//    @Override
//    public void updateBatchQuantity(int batchId, int newQuantity) throws SQLException {
//        String sql = "UPDATE batches SET quantity = ? WHERE batch_id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, newQuantity);
//            statement.setInt(2, batchId);
//            statement.executeUpdate();
//        }
//    }
//}



package com.syos.dao;

import com.syos.interfaces.IBatchDAO;
import com.syos.model.Batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchDAO implements IBatchDAO {

    private final Connection connection;

    // Constructor to inject the database connection
    public BatchDAO(Connection connection) {
        this.connection = connection;
    }

    // Retrieve batches for a product, ordered by date_received for FIFO
    @Override
    public List<Batch> getBatchesForProduct(int productId) throws SQLException {
        List<Batch> batches = new ArrayList<>();
        String sql = "SELECT * FROM batches WHERE product_id = ? ORDER BY expiry_date ASC, date_received ASC";  // Updated for FIFO with expiry date preference
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
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
        }
        return batches;
    }

    // Add a new batch
    @Override
    public void addBatch(Batch batch) throws SQLException {
        String sql = "INSERT INTO batches (product_id, batch_number, date_received, expiry_date, quantity, supplier_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, batch.getProductId());
            statement.setString(2, batch.getBatchNumber());
            statement.setDate(3, new java.sql.Date(batch.getDateReceived().getTime()));
            statement.setDate(4, new java.sql.Date(batch.getExpiryDate().getTime()));
            statement.setInt(5, batch.getQuantity());
            statement.setInt(6, batch.getSupplierId());
            statement.executeUpdate();
        }
    }

    // Update batch quantity after stock is used
    @Override
    public void updateBatchQuantity(int batchId, int newQuantity) throws SQLException {
        String sql = "UPDATE batches SET quantity = ? WHERE batch_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newQuantity);
            statement.setInt(2, batchId);
            statement.executeUpdate();
        }
    }
}