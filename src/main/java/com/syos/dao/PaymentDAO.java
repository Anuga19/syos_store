package com.syos.dao;

import com.syos.model.Payment;
import com.syos.model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAO {
    private final Connection connection;

    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }

    // Save a new payment
    public void savePayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO payments (bill_id, payment_method, amount_paid, payment_date) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, payment.getBillId());
            statement.setString(2, payment.getPaymentMethod());
            statement.setBigDecimal(3, payment.getAmountPaid());
            statement.executeUpdate();
        }
    }
}