package com.syos.dao;

import com.syos.model.Order;
import com.syos.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private final Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

//    // Save a new order
//    public void saveOrder(Order order) throws SQLException {
//        String sql = "INSERT INTO orders (customer_id, order_date, order_status, total_amount) VALUES (?, CURRENT_TIMESTAMP, ?, ?)";
//        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
//            statement.setInt(1, order.getCustomerId());
//            statement.setString(2, order.getOrderStatus());
//            statement.setBigDecimal(3, order.getTotalAmount());
//            statement.executeUpdate();
//
//            // Retrieve the generated order ID
//            ResultSet rs = statement.getGeneratedKeys();
//            if (rs.next()) {
//                order.setOrderId(rs.getInt(1));
//            }
//        }
//    }
public void saveOrder(Order order) throws SQLException {
    String sql = "INSERT INTO orders (customer_id, order_status, total_amount, order_date) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
    try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
        // Assuming customer_id is optional for in-store customers, you can set to null
        statement.setNull(1, java.sql.Types.INTEGER);
        statement.setString(2, order.getOrderStatus());
        statement.setBigDecimal(3, order.getTotalAmount());

        statement.executeUpdate();

        // Retrieve the generated order ID
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            order.setOrderId(rs.getInt(1)); // Set the generated order ID in the Order object
        }
    }
}

    // Get an order by ID
    public Order getOrderById(int orderId) throws SQLException {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Order(
                        resultSet.getInt("order_id"),
                        resultSet.getInt("customer_id"),
                        resultSet.getString("order_status"),
                        resultSet.getBigDecimal("total_amount"),
                        resultSet.getTimestamp("order_date")
                );
            }
        }
        return null;
    }

    // Retrieve all orders
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                orders.add(new Order(
                        resultSet.getInt("order_id"),
                        resultSet.getInt("customer_id"),
                        resultSet.getString("order_status"),
                        resultSet.getBigDecimal("total_amount"),
                        resultSet.getTimestamp("order_date")
                ));
            }
        }
        return orders;
    }
}