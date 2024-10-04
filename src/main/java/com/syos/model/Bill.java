//package com.syos.model;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//public class Bill {
//    private int billId;
//    private int orderId;
//    private BigDecimal totalAmount;
//    private BigDecimal discount;
//    private BigDecimal cashTendered;
//    private BigDecimal changeAmount;
//    private Date billDate;
//    private int billSerialNumber;
//
//    public Bill(int billSerialNumber, int orderId, BigDecimal totalAmount, BigDecimal discount, BigDecimal cashTendered, BigDecimal changeAmount) {
//        this.billSerialNumber = billSerialNumber;
//        this.orderId = orderId;
//        this.totalAmount = totalAmount;
//        this.discount = discount;
//        this.cashTendered = cashTendered;
//        this.changeAmount = changeAmount;
//        this.billDate = new Date(); // Set the current date when the bill is created
//    }
//
//    public int getBillSerialNumber() {
//        return billSerialNumber;
//    }
//
//    public void setBillSerialNumber(int billSerialNumber) {
//        this.billSerialNumber = billSerialNumber;
//    }
//
//    // Getters and setters
//    public int getBillId() {
//        return billId;
//    }
//
//    public void setBillId(int billId) {
//        this.billId = billId;
//    }
//
//    public int getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(int orderId) {
//        this.orderId = orderId;
//    }
//
//    public BigDecimal getTotalAmount() {
//        return totalAmount;
//    }
//
//    public void setTotalAmount(BigDecimal totalAmount) {
//        this.totalAmount = totalAmount;
//    }
//
//    public BigDecimal getDiscount() {
//        return discount;
//    }
//
//    public void setDiscount(BigDecimal discount) {
//        this.discount = discount;
//    }
//
//    public BigDecimal getCashTendered() {
//        return cashTendered;
//    }
//
//    public void setCashTendered(BigDecimal cashTendered) {
//        this.cashTendered = cashTendered;
//    }
//
//    public BigDecimal getChangeAmount() {
//        return changeAmount;
//    }
//
//    public void setChangeAmount(BigDecimal changeAmount) {
//        this.changeAmount = changeAmount;
//    }
//
//    public Date getBillDate() {
//        return billDate;
//    }
//
//    public void setBillDate(Date billDate) {
//        this.billDate = billDate;
//    }
//}

package com.syos.model;

import java.math.BigDecimal;
import java.util.Date;

public class Bill {
    private int billId; // Bill ID should be final once set
    private final int orderId;
    private final BigDecimal totalAmount;
    private final BigDecimal discount;
    private final BigDecimal cashTendered;
    private final BigDecimal changeAmount;
    private final Date billDate;
    private final int billSerialNumber;

    // Constructor with final fields, moving the logic of serial number and date handling outside
    public Bill(int billSerialNumber, int orderId, BigDecimal totalAmount, BigDecimal discount, BigDecimal cashTendered, BigDecimal changeAmount, Date billDate) {
        if (totalAmount == null || cashTendered == null || discount == null || changeAmount == null || billDate == null) {
            throw new IllegalArgumentException("Null values are not allowed.");
        }

        this.billSerialNumber = billSerialNumber;
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.cashTendered = cashTendered;
        this.changeAmount = changeAmount;
        this.billDate = new Date(billDate.getTime()); // Defensive copy to avoid external modifications
        this.billId = 0;  // Will be set when persisting the bill to the database
    }

    // Getters only, since we want to ensure immutability of the Bill object
    public int getBillSerialNumber() {
        return billSerialNumber;
    }

    public int getBillId() {
        return billId;
    }

    // Add a setter for billId
    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getOrderId() {
        return orderId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getCashTendered() {
        return cashTendered;
    }

    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    public Date getBillDate() {
        return new Date(billDate.getTime()); // Return defensive copy to avoid external modification
    }
}