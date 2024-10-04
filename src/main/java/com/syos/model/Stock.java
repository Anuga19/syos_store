package com.syos.model;

public class Stock {
    private final int stockId;      // Unique identifier for the stock
    private final int productId;    // Associated product ID
    private int quantity;           // Available quantity of the product in stock
    private int reorderLevel;       // The level at which the product should be reordered

    // Constructor
    public Stock(int stockId, int productId, int quantity, int reorderLevel) {
        if (stockId <= 0) {
            throw new IllegalArgumentException("Stock ID must be a positive integer.");
        }
        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID must be a positive integer.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        if (reorderLevel < 0) {
            throw new IllegalArgumentException("Reorder level cannot be negative.");
        }

        this.stockId = stockId;
        this.productId = productId;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
    }

    // Getters and Setters

    public int getStockId() {
        return stockId;
    }

    public int getProductId() {
        return productId;
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

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        if (reorderLevel < 0) {
            throw new IllegalArgumentException("Reorder level cannot be negative.");
        }
        this.reorderLevel = reorderLevel;
    }

    // Method to check if stock needs to be reordered
    public boolean needsReordering() {
        return quantity <= reorderLevel;
    }
}