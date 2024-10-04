package com.syos.interfaces;

import com.syos.model.OrderItem;

import java.sql.SQLException;
import java.util.List;

//public interface IOrderItemsDAO {
//    void saveOrderItems(int orderId, List<OrderItem> orderItems) throws SQLException;
//}

public interface IOrderItemsDAO {
    void saveOrderItems(Integer orderId, List<OrderItem> orderItems) throws SQLException;
}