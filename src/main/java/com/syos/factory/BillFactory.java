package com.syos.factory;

import com.syos.model.Bill;
import java.math.BigDecimal;
import java.util.Date;

public class BillFactory {

    public static Bill createBill(int billSerialNumber, int orderId, BigDecimal totalAmount, BigDecimal discount, BigDecimal cashTendered, BigDecimal changeAmount) {
        // Create the bill with the current date
        Date billDate = new Date();
        return new Bill(billSerialNumber, orderId, totalAmount, discount, cashTendered, changeAmount, billDate);
    }
}