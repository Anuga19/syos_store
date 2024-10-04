//package com.syos.model;
//
//import java.math.BigDecimal;
//
//public class OrderItem {
//    private Product product;
//    private int quantity;
//    private BigDecimal price;  // Store the price at the time of the order
//
//    public OrderItem(Product product, int quantity) {
//        this.product = product;
//        this.quantity = quantity;
//        this.price = product.getPrice();  // Fetch and store the price from the product
//    }
//
//    // Method to get the total price for the order item (price * quantity)
//    public BigDecimal getItemTotal() {
//        return price.multiply(BigDecimal.valueOf(quantity));
//    }
//
//    // Getter for the product
//    public Product getProduct() {
//        return product;
//    }
//
//    // Getter for the quantity
//    public int getQuantity() {
//        return quantity;
//    }
//
//    // Getter for price
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    // Setter for quantity (if quantity needs to be updated after creation)
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//}

package com.syos.model;

import java.math.BigDecimal;

public class OrderItem {

    private final Product product;  // Make product immutable
    private int quantity;           // Quantity can be updated, but with validation
    private final BigDecimal price; // Make price immutable (represent price at order time)

    // Constructor ensures product and price are immutable, quantity is validated
    public OrderItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice();  // Fetch and store the price from the product at the time of order
    }

    // Method to get the total price for the order item (price * quantity)
    public BigDecimal getItemTotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    // Getter for the product
    public Product getProduct() {
        return product;
    }

    // Getter for the quantity
    public int getQuantity() {
        return quantity;
    }

    // Getter for price (which is immutable after order creation)
    public BigDecimal getPrice() {
        return price;
    }

    // Setter for quantity with validation (quantity can be updated)
    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        this.quantity = quantity;
    }
}