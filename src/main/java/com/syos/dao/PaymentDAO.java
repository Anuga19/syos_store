//package com.syos.dao;
//
//import com.syos.model.Payment;
//import com.syos.model.Bill;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class PaymentDAO {
//    private final Connection connection;
//
//    public PaymentDAO(Connection connection) {
//        this.connection = connection;
//    }
//
//    // Save a new payment
//    public void savePayment(Payment payment) throws SQLException {
//        String sql = "INSERT INTO payments (bill_id, payment_method, amount_paid, payment_date) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, payment.getBillId());
//            statement.setString(2, payment.getPaymentMethod());
//            statement.setBigDecimal(3, payment.getAmountPaid());
//            statement.executeUpdate();
//        }
//    }
//}
 // ----------------- //

//package com.syos.dao;
//
//import com.syos.interfaces.IPaymentDAO;
//import com.syos.model.Payment;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class PaymentDAO implements IPaymentDAO {
//    private final Connection connection;
//
//    public PaymentDAO(Connection connection) {
//        this.connection = connection;
//    }
//
//    // Implement savePayment from IPaymentDAO
//    @Override
//    public void savePayment(Payment payment) throws SQLException {
//        String sql = "INSERT INTO payments (bill_id, payment_method, amount_paid, payment_date) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, payment.getBillId());
//            statement.setString(2, payment.getPaymentMethod());
//            statement.setBigDecimal(3, payment.getAmountPaid());
//            statement.executeUpdate();
//        }
//    }
//}


package com.syos.dao;

import com.syos.interfaces.IPaymentDAO;
import com.syos.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAO implements IPaymentDAO {
    private final Connection connection;

    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }

    // Implement savePayment from IPaymentDAO
    @Override
    public void savePayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO payments (bill_id, payment_method, amount_paid, payment_date) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, payment.getBillId());
            // Convert the enum to a string before saving it to the database
            statement.setString(2, payment.getPaymentMethod().name());
            statement.setBigDecimal(3, payment.getAmountPaid());
            statement.executeUpdate();
        }
    }
}