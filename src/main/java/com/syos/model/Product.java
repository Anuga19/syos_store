//package com.syos.model;
//
//import java.math.BigDecimal;
//
//public class Product {
//
//    private int productId;
//    private String productCode;  // E.g., "BEV-MIL-24-10"
//    private String productName;
//    private BigDecimal price;
//
//    // Constructor
//    public Product(int productId, String productCode, String productName, BigDecimal price) {
//        this.productId = productId;
//        this.productCode = productCode;
//        this.productName = productName;
//        this.price = price;
//    }
//
//    // Getters and Setters
//    public int getProductId() {
//        return productId;
//    }
//
//    public void setProductId(int productId) {
//        this.productId = productId;
//    }
//
//    public String getProductCode() {
//        return productCode;
//    }
//
//    public void setProductCode(String productCode) {
//        this.productCode = productCode;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//
//}


package com.syos.model;

import java.math.BigDecimal;

public class Product {

    private int productId;  // Immutable field (final)
    private String productCode;   // E.g., "BEV-MIL-24-10"
    private String productName;
    private BigDecimal price;

    // Constructor to initialize immutable fields
    public Product( String productCode, String productName, BigDecimal price) {

//        this.productId = productId;
        this.setProductCode(productCode);  // Use setter for validation
        this.setProductName(productName);  // Use setter for validation
        this.setPrice(price);  // Use setter for validation
    }

    // Getters and setters

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID must be positive.");
        }
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    // Perform validation for the product code (if necessary)
    public void setProductCode(String productCode) {
        if (productCode == null || productCode.isEmpty()) {
            throw new IllegalArgumentException("Product code cannot be null or empty.");
        }
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    // Ensure the product name is valid
    public void setProductName(String productName) {
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    // Ensure the price is positive and valid
    public void setPrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be a positive value.");
        }
        this.price = price;
    }
}