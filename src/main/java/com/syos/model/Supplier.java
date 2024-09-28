package com.syos.model;

public class Supplier {
    private int supplierId;
    private String supplierName;
    private String contactDetails;

    // Constructor for creating new Supplier
    public Supplier(String supplierName, String contactDetails) {
        this.supplierName = supplierName;
        this.contactDetails = contactDetails;
    }

    // Constructor for retrieving Supplier from DB
    public Supplier(int supplierId, String supplierName, String contactDetails) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.contactDetails = contactDetails;
    }

    // Getters and Setters
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
}