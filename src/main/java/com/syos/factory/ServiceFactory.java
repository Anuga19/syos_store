package com.syos.factory;

import com.syos.dao.OrderDAO;
import com.syos.dao.OrderItemsDAO;
import com.syos.interfaces.IOrderDAO;
import com.syos.interfaces.IOrderItemsDAO;
import com.syos.service.OrderService;
import com.syos.utilities.OrderCalculator;
import com.syos.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

//public class ServiceFactory {
//
//    // Static method to create OrderService with all dependencies
//    public static OrderService createOrderService() {
//        Connection connection = null;
//        try {
//            // Get database connection from DatabaseConfig
//            connection = DatabaseConfig.getConnection();
//
//            if (connection == null) {
//                throw new SQLException("Failed to establish a database connection.");
//            }
//
//            IOrderDAO orderDAO = new OrderDAO(connection);  // Pass connection to OrderDAO
//            IOrderItemsDAO orderItemsDAO = new OrderItemsDAO(connection);  // Pass connection to OrderItemsDAO
//            OrderCalculator orderCalculator = new OrderCalculator();  // Create OrderCalculator
//
//            // Inject all dependencies into OrderService
//            return new OrderService(orderDAO, orderItemsDAO, orderCalculator);
//
//        } catch (SQLException e) {
//            System.out.println("Error creating OrderService: " + e.getMessage());
//            return null;
//        }
//    }
//}

public class ServiceFactory {

    // Static method to create OrderService with all dependencies
    public static OrderService createOrderService() throws SQLException {
        // Get database connection from DatabaseConfig
        Connection connection = DatabaseConfig.getConnection();

        // Create concrete instances of DAOs and utilities
        IOrderDAO orderDAO = new OrderDAO(connection);  // Pass connection to OrderDAO
        IOrderItemsDAO orderItemsDAO = new OrderItemsDAO(connection);  // Pass connection to OrderItemsDAO
        OrderCalculator orderCalculator = new OrderCalculator();  // Create OrderCalculator

        // Return new OrderService with injected dependencies
        return new OrderService(orderDAO, orderItemsDAO, orderCalculator);
    }
}