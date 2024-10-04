package com.syos.interfaces;

import com.syos.model.Order;
import java.sql.SQLException;
import java.util.List;

public interface IOrderDAO {
    void saveOrder(Order order) throws SQLException;
    Order getOrderById(int orderId) throws SQLException;
    List<Order> getAllOrders() throws SQLException;
}