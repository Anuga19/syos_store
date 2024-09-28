package com.syos.model;

import java.math.BigDecimal;

public class OrderItem {
    private Product product;
    private int quantity;
    private BigDecimal price;  // Store the price at the time of the order

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice();  // Fetch and store the price from the product
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

    // Getter for price
    public BigDecimal getPrice() {
        return price;
    }

    // Setter for quantity (if quantity needs to be updated after creation)
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}