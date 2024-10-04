//package com.syos.service;
//
//import com.syos.dao.BillDAO;
//import com.syos.factory.BillFactory;
//import com.syos.model.Bill;
//import com.syos.model.Order;
//import java.math.BigDecimal;
//import java.sql.SQLException;
//
//public class BillService {
//    private final BillDAO billDAO;
//    public static int billSerialNumber = 1;
//
//    public BillService(BillDAO billDAO) {
//        this.billDAO = billDAO;
//    }
//
//    public Bill generateBill(Order order, BigDecimal cashTendered, BigDecimal discount) throws SQLException {
//        // Calculate the total amount and change
//        BigDecimal totalAmount = order.getTotalAmount().subtract(discount);
//        BigDecimal changeAmount = cashTendered.subtract(totalAmount);
//
//        // Use the BillFactory to create the bill
//        Bill bill = BillFactory.createBill(
//                billSerialNumber++, // Increment serial number
//                order.getOrderId(),
//                totalAmount,
//                discount,
//                cashTendered,
//                changeAmount
//        );
//
//        // Save the bill in the database
//        billDAO.saveBill(bill);
//
//        return bill;
//    }
//}
package com.syos.service;

import com.syos.interfaces.IBillDAO;
import com.syos.factory.BillFactory;
import com.syos.model.Bill;
import com.syos.model.Order;
import com.syos.utilities.BillCalculator;  // Hypothetical class for calculations

import java.math.BigDecimal;
import java.sql.SQLException;

public class BillService {
    private final IBillDAO billDAO;  // Use the interface instead of concrete class
    private final BillCalculator billCalculator;  // Hypothetical calculator class
    public static int billSerialNumber = 1;

    public BillService(IBillDAO billDAO, BillCalculator billCalculator) {
        this.billDAO = billDAO;
        this.billCalculator = billCalculator;
    }

    public Bill generateBill(Order order, BigDecimal cashTendered, BigDecimal discount) throws SQLException {
        // Use BillCalculator to calculate the total amount and change
        BigDecimal totalAmount = billCalculator.calculateTotalAmount(order.getTotalAmount(), discount);
        BigDecimal changeAmount = billCalculator.calculateChangeAmount(cashTendered, totalAmount);

        // Use the BillFactory to create the bill
        Bill bill = BillFactory.createBill(
                billSerialNumber++, // Increment serial number
                order.getOrderId(),
                totalAmount,
                discount,
                cashTendered,
                changeAmount
        );

        // Save the bill in the database
        billDAO.saveBill(bill);

        return bill;
    }
}