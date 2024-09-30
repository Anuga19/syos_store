package com.syos.service;

import com.syos.dao.PaymentDAO;
import com.syos.model.Order;

import java.math.BigDecimal;

public class PaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentService(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    public void processCashPayment(Order order, BigDecimal cashTendered) {
        BigDecimal totalAmount = order.getTotalAmount();
        if (cashTendered.compareTo(totalAmount) >= 0) {
            BigDecimal change = cashTendered.subtract(totalAmount);
            System.out.println("Change to return: " + change);



        } else {
            System.out.println("Insufficient cash provided.");
        }
    }
}