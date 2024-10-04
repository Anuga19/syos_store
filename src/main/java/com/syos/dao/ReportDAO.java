//package com.syos.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class ReportDAO {
//    private final Connection connection;
//
//    public ReportDAO(Connection connection) {
//        this.connection = connection;
//    }
//
//    public void getTotalSalesForDay() throws SQLException {
//        String sql = "SELECT p.product_name, p.product_code, SUM(oi.quantity) AS total_quantity, SUM(oi.price * oi.quantity) AS total_revenue " +
//                "FROM orders o " +
//                "JOIN order_items oi ON o.order_id = oi.order_id " +
//                "JOIN products p ON oi.product_id = p.product_id " +
//                "WHERE DATE(o.order_date) = CURRENT_DATE " +  // Compare only the date part
//                "GROUP BY p.product_name, p.product_code";
//
//        try (PreparedStatement statement = connection.prepareStatement(sql);
//             ResultSet resultSet = statement.executeQuery()) {
//
//            // Check if any rows are returned
//            if (!resultSet.isBeforeFirst()) {
//                System.out.println("No sales found for today.");
//                return;  // Exit if no data is found
//            }
//
//            // Iterate over the result set and print the report
//            while (resultSet.next()) {
//                System.out.println("Product: " + resultSet.getString("product_name"));
//                System.out.println("Code: " + resultSet.getString("product_code"));
//                System.out.println("Total Quantity: " + resultSet.getInt("total_quantity"));
//                System.out.println("Total Revenue: LKR" + resultSet.getBigDecimal("total_revenue"));
//                System.out.println("--------");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error generating total sales report: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void getReorderLevelReport() throws SQLException {
//        String sql = "SELECT p.product_name, p.product_code, s.quantity, s.reorder_level " +
//                "FROM products p " +
//                "JOIN stock s ON p.product_id = s.product_id " +
//                "WHERE s.quantity < s.reorder_level";
//
//        try (PreparedStatement statement = connection.prepareStatement(sql);
//             ResultSet resultSet = statement.executeQuery()) {
//
//            if (!resultSet.isBeforeFirst()) {
//                System.out.println("No items below reorder level.");
//                return;
//            }
//
//            while (resultSet.next()) {
//                System.out.println("Product: " + resultSet.getString("product_name"));
//                System.out.println("Code: " + resultSet.getString("product_code"));
//                System.out.println("Quantity: " + resultSet.getInt("quantity"));
//                System.out.println("Reorder Level: " + resultSet.getInt("reorder_level"));
//                System.out.println("--------");
//            }
//        }
//    }
//
//    // New method to get the stock report, batch-wise
//    public void getStockReport() throws SQLException {
//        String sql = "SELECT p.product_name, p.product_code, b.batch_number, b.quantity, b.expiry_date, b.date_received " +
//                "FROM products p " +
//                "JOIN batches b ON p.product_id = b.product_id " +
//                "ORDER BY b.expiry_date ASC";
//
//        try (PreparedStatement statement = connection.prepareStatement(sql);
//             ResultSet resultSet = statement.executeQuery()) {
//
//            if (!resultSet.isBeforeFirst()) {
//                System.out.println("No stock data found.");
//                return;
//            }
//
//            while (resultSet.next()) {
//                System.out.println("Product: " + resultSet.getString("product_name"));
//                System.out.println("Code: " + resultSet.getString("product_code"));
//                System.out.println("Batch Number: " + resultSet.getString("batch_number"));
//                System.out.println("Quantity: " + resultSet.getInt("quantity"));
//                System.out.println("Expiry Date: " + resultSet.getDate("expiry_date"));
//                System.out.println("Date Received: " + resultSet.getDate("date_received"));
//                System.out.println("--------");
//            }
//        }
//    }
//
//    // New method to generate the bill report for customer transactions
//
//    public void getBillReport() throws SQLException {
//        String sql = "SELECT b.bill_id, o.customer_id, b.total_amount, p.payment_method, p.payment_date " +
//                "FROM bills b " +
//                "JOIN orders o ON b.order_id = o.order_id " +
//                "JOIN payments p ON b.bill_id = p.bill_id " +
//                "ORDER BY p.payment_date DESC";
//
//        try (PreparedStatement statement = connection.prepareStatement(sql);
//             ResultSet resultSet = statement.executeQuery()) {
//
//            if (!resultSet.isBeforeFirst()) {
//                System.out.println("No customer transactions found.");
//                return;
//            }
//
//            while (resultSet.next()) {
//                System.out.println("Bill ID: " + resultSet.getInt("bill_id"));
//                System.out.println("Customer ID: " + resultSet.getInt("customer_id"));
//                System.out.println("Total Amount: " + resultSet.getBigDecimal("total_amount"));
//                System.out.println("Payment Method: " + resultSet.getString("payment_method"));
//                System.out.println("Payment Date: " + resultSet.getTimestamp("payment_date"));
//                System.out.println("--------");
//            }
//        }
//    }
//
//}


// -----------------------------------------------------

package com.syos.dao;

import com.syos.exceptions.ReportGenerationException;
import com.syos.interfaces.IReportDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDAO implements IReportDAO {
    private final Connection connection;

    public ReportDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void getTotalSalesForDay() throws ReportGenerationException {
        String sql = "SELECT p.product_name, p.product_code, SUM(oi.quantity) AS total_quantity, SUM(oi.price * oi.quantity) AS total_revenue " +
                "FROM orders o " +
                "JOIN order_items oi ON o.order_id = oi.order_id " +
                "JOIN products p ON oi.product_id = p.product_id " +
                "WHERE DATE(o.order_date) = CURRENT_DATE " +
                "GROUP BY p.product_name, p.product_code";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            // Database interaction only, no printing here
        } catch (SQLException e) {
            throw new ReportGenerationException("Error generating total sales report", e);
        }
    }

    @Override
    public void getReorderLevelReport() throws ReportGenerationException {
        String sql = "SELECT p.product_name, p.product_code, s.quantity, s.reorder_level " +
                "FROM products p " +
                "JOIN stock s ON p.product_id = s.product_id " +
                "WHERE s.quantity < s.reorder_level";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            // Database interaction only, no printing here
        } catch (SQLException e) {
            throw new ReportGenerationException("Error generating reorder level report", e);
        }
    }

    @Override
    public void getStockReport() throws ReportGenerationException {
        String sql = "SELECT p.product_name, p.product_code, b.batch_number, b.quantity, b.expiry_date, b.date_received " +
                "FROM products p " +
                "JOIN batches b ON p.product_id = b.product_id " +
                "ORDER BY b.expiry_date ASC";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            // Database interaction only, no printing here
        } catch (SQLException e) {
            throw new ReportGenerationException("Error generating stock report", e);
        }
    }

    @Override
    public void getBillReport() throws ReportGenerationException {
        String sql = "SELECT b.bill_id, o.customer_id, b.total_amount, p.payment_method, p.payment_date " +
                "FROM bills b " +
                "JOIN orders o ON b.order_id = o.order_id " +
                "JOIN payments p ON b.bill_id = p.bill_id " +
                "ORDER BY p.payment_date DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            // Database interaction only, no printing here
        } catch (SQLException e) {
            throw new ReportGenerationException("Error generating bill report", e);
        }
    }
}