//package com.syos.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class StockDAO {
//    private final Connection connection;
//
//    public StockDAO(Connection connection) {
//        this.connection = connection;
//    }
//
//    public void updateStockQuantity(int productId, int quantitySold) throws SQLException {
//        String sql = "UPDATE stock SET quantity = quantity - ? WHERE product_id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, quantitySold);
//            statement.setInt(2, productId);
//            statement.executeUpdate();
//        }
//    }
//}

// --------------------------------------------------------------------

//package com.syos.dao;
//
//import com.syos.interfaces.IStockDAO;
//import com.syos.model.Stock;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class StockDAO implements IStockDAO {
//    private final Connection connection;
//
//    public StockDAO(Connection connection) {
//        this.connection = connection;
//    }
//
//    // Reduce stock quantity after a sale
//    @Override
//    public void reduceStockQuantity(int productId, int quantitySold) throws SQLException {
//        String sql = "UPDATE stock SET quantity = quantity - ? WHERE product_id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, quantitySold);
//            statement.setInt(2, productId);
//            statement.executeUpdate();
//        }
//    }
//
//    // Retrieve stock details for a product by productId
//    @Override
//    public Stock getStockByProductId(int productId) throws SQLException {
//        String query = "SELECT * FROM stock WHERE product_id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, productId);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                int stockId = resultSet.getInt("stock_id");
//                int quantity = resultSet.getInt("quantity");
//                int reorderLevel = resultSet.getInt("reorder_level");
//                return new Stock(stockId, productId, quantity, reorderLevel);
//            }
//        }
//        return null; // Return null if no stock is found for the product
//    }
//
//    // Update the quantity of stock for a product by productId
//    @Override
//    public void updateStockQuantity(int productId, int quantity) throws SQLException {
//        String query = "UPDATE stock SET quantity = ? WHERE product_id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, quantity);
//            statement.setInt(2, productId);
//            int rowsUpdated = statement.executeUpdate();
//            if (rowsUpdated > 0) {
//                System.out.println("Stock quantity updated successfully.");
//            } else {
//                System.out.println("Stock update failed or product not found.");
//            }
//        }
//    }
//}

// --------------------------------------------------------------------

package com.syos.dao;

import com.syos.interfaces.IStockDAO;
import com.syos.model.Stock;
import com.syos.exceptions.StockOperationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockDAO implements IStockDAO {
    private final Connection connection;

    // Constructor to inject the database connection
    public StockDAO(Connection connection) {
        this.connection = connection;
    }

    // Reduce stock quantity after a sale
    @Override
    public void reduceStockQuantity(int productId, int quantitySold) {
        String sql = "UPDATE stock SET quantity = quantity - ? WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantitySold);
            statement.setInt(2, productId);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated == 0) {
                throw new StockOperationException("Stock update failed, product not found.");
            }
        } catch (SQLException e) {
            throw new StockOperationException("Failed to reduce stock quantity.", e);
        }
    }

    // Retrieve stock details for a product by productId
    @Override
    public Stock getStockByProductId(int productId) {
        String query = "SELECT * FROM stock WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int stockId = resultSet.getInt("stock_id");
                int quantity = resultSet.getInt("quantity");
                int reorderLevel = resultSet.getInt("reorder_level");
                return new Stock(stockId, productId, quantity, reorderLevel);
            } else {
                throw new StockOperationException("Stock not found for product ID: " + productId);
            }
        } catch (SQLException e) {
            throw new StockOperationException("Error retrieving stock by product ID.", e);
        }
    }

    // Update the quantity of stock for a product by productId
    @Override
    public void updateStockQuantity(int productId, int quantity) {
        String query = "UPDATE stock SET quantity = ? WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, quantity);
            statement.setInt(2, productId);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated == 0) {
                throw new StockOperationException("Stock update failed, product not found.");
            }
        } catch (SQLException e) {
            throw new StockOperationException("Error updating stock quantity.", e);
        }
    }
}