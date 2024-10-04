package com.syos.factory;

import com.syos.dao.OrderDAO;
import com.syos.interfaces.IOrderDAO;

import java.sql.Connection;

public class OrderDAOFactory {

    // This method will create and return an instance of OrderDAO
    public static IOrderDAO createOrderDAO(Connection connection) {
        return new OrderDAO(connection);
    }
}
