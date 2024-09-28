package com.syos.model;

import java.util.Date;

public class Batch {
    private int batchId;
    private int productId;  // Using product_id as an int
    private String batchNumber;
    private Date dateReceived;
    private Date expiryDate;
    private int quantity;
    private int supplierId;

    // Constructor
    public Batch(int batchId, int productId, String batchNumber, Date dateReceived, Date expiryDate, int quantity, int supplierId) {
        this.batchId = batchId;
        this.productId = productId;
        this.batchNumber = batchNumber;
        this.dateReceived = dateReceived;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.supplierId = supplierId;
    }

    // Getters and Setters
    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}