package com.syos.service;

import com.syos.dao.BillDAO;
import com.syos.model.Bill;
import com.syos.model.Order;

import java.math.BigDecimal;
import java.sql.SQLException;

public class BillService {
    private final BillDAO billDAO;
    public static int billSerialNumber = 1;

    public BillService(BillDAO billDAO) {
        this.billDAO = billDAO;
    }

//    public void generateBill(Order order, BigDecimal cashTendered, BigDecimal discount) throws SQLException {
//        // Calculate the total amount and change
//        BigDecimal totalAmount = order.getTotalAmount().subtract(discount);  // Apply discount during billing
//        BigDecimal changeAmount = cashTendered.subtract(totalAmount);  // Calculate change
//
//        // Create a Bill object using data from the Order
//        Bill bill = new Bill(
//                order.getOrderId(),
//                totalAmount,
//                discount,
//                cashTendered,
//                changeAmount
//        );
//
//        // Save the bill in the database
//        billDAO.saveBill(bill);
//    }

    public void generateBill(Order order, BigDecimal cashTendered, BigDecimal discount) throws SQLException {
        // Calculate the total amount and change
        BigDecimal totalAmount = order.getTotalAmount().subtract(discount);  // Apply discount during billing
        BigDecimal changeAmount = cashTendered.subtract(totalAmount);  // Calculate change

        // Create a Bill object using data from the Order
        Bill bill = new Bill(
                billSerialNumber++,  // Use and increment the serial number
                order.getOrderId(),
                totalAmount,
                discount,
                cashTendered,
                changeAmount
        );

        // Save the bill in the database
        billDAO.saveBill(bill);

        // Print the generated bill (we'll do this in the CheckoutCLI)
    }
}