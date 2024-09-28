package com.syos.dao;

import com.syos.model.Bill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BillDAO {
    private Connection connection;

    public BillDAO(Connection connection) {
        this.connection = connection;
    }

    // Save a bill to the database
    public void saveBill(Bill bill) throws SQLException {
        String sql = "INSERT INTO bills (order_id, total_amount, discount, cash_tendered, change_amount, bill_date) " +
                "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bill.getOrderId());
            statement.setBigDecimal(2, bill.getTotalAmount());
            statement.setBigDecimal(3, bill.getDiscount());
            statement.setBigDecimal(4, bill.getCashTendered());
            statement.setBigDecimal(5, bill.getChangeAmount());
            statement.executeUpdate();
        }
    }
}