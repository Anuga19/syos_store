//package com.syos.model;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//public class Payment {
//    private int paymentId;
//    private int billId;
//    private String paymentMethod;  // "CASH" or "ONLINE"
//    private BigDecimal amountPaid;
//    private Date paymentDate;
//
//    // Constructor
//    public Payment(int billId, String paymentMethod, BigDecimal amountPaid) {
//        this.billId = billId;
//        this.paymentMethod = paymentMethod;
//        this.amountPaid = amountPaid;
//    }
//
//    // Overloaded constructor to include paymentId and paymentDate
//    public Payment(int paymentId, int billId, String paymentMethod, BigDecimal amountPaid, Date paymentDate) {
//        this.paymentId = paymentId;
//        this.billId = billId;
//        this.paymentMethod = paymentMethod;
//        this.amountPaid = amountPaid;
//        this.paymentDate = paymentDate;
//    }
//
//    // Getters and setters
//    public int getPaymentId() {
//        return paymentId;
//    }
//
//    public void setPaymentId(int paymentId) {
//        this.paymentId = paymentId;
//    }
//
//    public int getBillId() {
//        return billId;
//    }
//
//    public void setBillId(int billId) {
//        this.billId = billId;
//    }
//
//    public String getPaymentMethod() {
//        return paymentMethod;
//    }
//
//    public void setPaymentMethod(String paymentMethod) {
//        this.paymentMethod = paymentMethod;
//    }
//
//    public BigDecimal getAmountPaid() {
//        return amountPaid;
//    }
//
//    public void setAmountPaid(BigDecimal amountPaid) {
//        this.amountPaid = amountPaid;
//    }
//
//    public Date getPaymentDate() {
//        return paymentDate;
//    }
//
//    public void setPaymentDate(Date paymentDate) {
//        this.paymentDate = paymentDate;
//    }
//}

package com.syos.model;

import java.math.BigDecimal;
import java.util.Date;

public class Payment {
    private int paymentId;
    private int billId;
    private PaymentMethod paymentMethod;  // Enum to represent payment methods
    private BigDecimal amountPaid;
    private Date paymentDate;

    // Enum to represent payment methods (CASH or ONLINE)
    public enum PaymentMethod {
        CASH, ONLINE
    }

    // Constructor for creating a new payment without a paymentId
    public Payment(int billId, PaymentMethod paymentMethod, BigDecimal amountPaid) {
        this.billId = billId;
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
        this.paymentDate = new Date();  // Set current date as default
    }

    // Overloaded constructor to include paymentId and paymentDate (e.g., when loading from the database)
    public Payment(int paymentId, int billId, PaymentMethod paymentMethod, BigDecimal amountPaid, Date paymentDate) {
        this.paymentId = paymentId;
        this.billId = billId;
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
        this.paymentDate = new Date(paymentDate.getTime());  // Defensive copy to avoid external modification
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getPaymentDate() {
        return new Date(paymentDate.getTime());  // Return a defensive copy
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = new Date(paymentDate.getTime());  // Store a defensive copy
    }
}