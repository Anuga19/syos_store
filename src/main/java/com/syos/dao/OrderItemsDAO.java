package com.syos.dao;

import com.syos.model.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderItemsDAO {
    private final Connection connection;

    public OrderItemsDAO(Connection connection) {
        this.connection = connection;
    }

    // Save order items
    public void saveOrderItems(int orderId, List<OrderItem> orderItems) throws SQLException {
        String sql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (OrderItem item : orderItems) {
                statement.setInt(1, orderId);
                statement.setInt(2, item.getProduct().getProductId());
                statement.setInt(3, item.getQuantity());
                statement.setBigDecimal(4, item.getPrice());
                statement.addBatch();
            }
            statement.executeBatch(); // Execute all inserts as a batch
        }
    }
}