package com.syos.model;

import java.math.BigDecimal;

public class Product {

    private int productId;
    private String productCode;  // E.g., "BEV-MIL-24-10"
    private String productName;
    private BigDecimal price;

    // Constructor
    public Product(int productId, String productCode, String productName, BigDecimal price) {
        this.productId = productId;
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}