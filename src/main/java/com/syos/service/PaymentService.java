package com.syos.service;

import com.syos.dao.PaymentDAO;
import com.syos.model.Bill;
import com.syos.model.Order;
import com.syos.model.Payment;

import java.math.BigDecimal;
import java.sql.SQLException;

public class PaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentService(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

//    public void processCashPayment(Order order, BigDecimal cashTendered) {
//        BigDecimal totalAmount = order.getTotalAmount();
//        if (cashTendered.compareTo(totalAmount) >= 0) {
//            BigDecimal change = cashTendered.subtract(totalAmount);
//            System.out.println("Change to return: " + change);
//
//
//
//        } else {
//            System.out.println("Insufficient cash provided.");
//        }
//    }

    public void processCashPayment(Bill bill, BigDecimal cashTendered) throws SQLException {
        BigDecimal totalAmount = bill.getTotalAmount();
        if (cashTendered.compareTo(totalAmount) >= 0) {
            BigDecimal change = cashTendered.subtract(totalAmount);
            System.out.println("Change to return: " + change);

            // Save payment information to the payments table
            Payment payment = new Payment(
                    bill.getBillId(),        // Linking the payment to the generated bill ID
                    "CASH",                 // Payment method (assuming cash)
                    cashTendered            // Amount paid (cash tendered by the customer)
            );

            paymentDAO.savePayment(payment);  // Save payment to the database

        } else {
            System.out.println("Insufficient cash provided.");
        }
    }
}