//package com.syos.model;
//
//import java.util.Date;
//
//public class Batch {
//    private int batchId;
//    private int productId;  // Using product_id as an int
//    private String batchNumber;
//    private Date dateReceived;
//    private Date expiryDate;
//    private int quantity;
//    private int supplierId;
//
//    // Constructor
//    public Batch(int batchId, int productId, String batchNumber, Date dateReceived, Date expiryDate, int quantity, int supplierId) {
//        this.batchId = batchId;
//        this.productId = productId;
//        this.batchNumber = batchNumber;
//        this.dateReceived = dateReceived;
//        this.expiryDate = expiryDate;
//        this.quantity = quantity;
//        this.supplierId = supplierId;
//    }
//
//    // Getters and Setters
//    public int getBatchId() {
//        return batchId;
//    }
//
//    public void setBatchId(int batchId) {
//        this.batchId = batchId;
//    }
//
//    public int getProductId() {
//        return productId;
//    }
//
//    public void setProductId(int productId) {
//        this.productId = productId;
//    }
//
//    public String getBatchNumber() {
//        return batchNumber;
//    }
//
//    public void setBatchNumber(String batchNumber) {
//        this.batchNumber = batchNumber;
//    }
//
//    public Date getDateReceived() {
//        return dateReceived;
//    }
//
//    public void setDateReceived(Date dateReceived) {
//        this.dateReceived = dateReceived;
//    }
//
//    public Date getExpiryDate() {
//        return expiryDate;
//    }
//
//    public void setExpiryDate(Date expiryDate) {
//        this.expiryDate = expiryDate;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//}


package com.syos.model;

import java.util.Date;

public class Batch {

    private final int batchId;      // Immutable field
    private final int productId;    // Immutable field
    private final int supplierId;   // Immutable field

    private String batchNumber;
    private Date dateReceived;
    private Date expiryDate;
    private int quantity;

    // Constructor to initialize immutable fields
    public Batch(int batchId, int productId, String batchNumber, Date dateReceived, Date expiryDate, int quantity, int supplierId) {
        if (batchId <= 0 || productId <= 0 || supplierId <= 0) {
            throw new IllegalArgumentException("Batch ID, Product ID, and Supplier ID must be positive.");
        }
        if (dateReceived == null || expiryDate == null) {
            throw new IllegalArgumentException("Dates cannot be null.");
        }
        if (expiryDate.before(dateReceived)) {
            throw new IllegalArgumentException("Expiry date cannot be before the date received.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        this.batchId = batchId;
        this.productId = productId;
        this.supplierId = supplierId;
        this.batchNumber = batchNumber;
        this.dateReceived = new Date(dateReceived.getTime()); // Defensive copy
        this.expiryDate = new Date(expiryDate.getTime());     // Defensive copy
        this.quantity = quantity;
    }

    // Getters

    public int getBatchId() {
        return batchId;
    }

    public int getProductId() {
        return productId;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        if (batchNumber == null || batchNumber.isEmpty()) {
            throw new IllegalArgumentException("Batch number cannot be null or empty.");
        }
        this.batchNumber = batchNumber;
    }

    public Date getDateReceived() {
        return new Date(dateReceived.getTime());  // Defensive copy to prevent external modification
    }

    public void setDateReceived(Date dateReceived) {
        if (dateReceived == null) {
            throw new IllegalArgumentException("Date received cannot be null.");
        }
        if (expiryDate != null && expiryDate.before(dateReceived)) {
            throw new IllegalArgumentException("Expiry date cannot be before the date received.");
        }
        this.dateReceived = new Date(dateReceived.getTime());  // Defensive copy
    }

    public Date getExpiryDate() {
        return new Date(expiryDate.getTime());  // Defensive copy
    }

    public void setExpiryDate(Date expiryDate) {
        if (expiryDate == null) {
            throw new IllegalArgumentException("Expiry date cannot be null.");
        }
        if (expiryDate.before(dateReceived)) {
            throw new IllegalArgumentException("Expiry date cannot be before the date received.");
        }
        this.expiryDate = new Date(expiryDate.getTime());  // Defensive copy
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantity = quantity;
    }

    public int getSupplierId() {
        return supplierId;
    }
}