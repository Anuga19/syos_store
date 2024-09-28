package com.syos.model;

import java.math.BigDecimal;
import java.util.Date;

public class Payment {
    private int paymentId;
    private int billId;
    private String paymentMethod;  // "CASH" or "ONLINE"
    private BigDecimal amountPaid;
    private Date paymentDate;

    // Constructor
    public Payment(int billId, String paymentMethod, BigDecimal amountPaid) {
        this.billId = billId;
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
    }

    // Overloaded constructor to include paymentId and paymentDate
    public Payment(int paymentId, int billId, String paymentMethod, BigDecimal amountPaid, Date paymentDate) {
        this.paymentId = paymentId;
        this.billId = billId;
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
    }

    // Getters and setters
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}