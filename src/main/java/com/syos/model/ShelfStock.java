//package com.syos.model;
//
//public class ShelfStock {
//    private int shelfStockId;
//    private int productId;
//    private int quantityOnShelf;
//
//    // Constructor
//    public ShelfStock(int shelfStockId, int productId, int quantityOnShelf) {
//        this.shelfStockId = shelfStockId;
//        this.productId = productId;
//        this.quantityOnShelf = quantityOnShelf;
//    }
//
//    // Getters and Setters
//    public int getShelfStockId() {
//        return shelfStockId;
//    }
//
//    public void setShelfStockId(int shelfStockId) {
//        this.shelfStockId = shelfStockId;
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
//    public int getQuantityOnShelf() {
//        return quantityOnShelf;
//    }
//
//    public void setQuantityOnShelf(int quantityOnShelf) {
//        this.quantityOnShelf = quantityOnShelf;
//    }
//}
package com.syos.model;

public class ShelfStock {
    private final int shelfStockId;  // Immutable field
    private final int productId;     // Immutable field
    private int quantityOnShelf;     // Mutable with validation

    // Constructor to initialize immutable fields
    public ShelfStock(int shelfStockId, int productId, int quantityOnShelf) {
        if (shelfStockId <= 0) {
            throw new IllegalArgumentException("Shelf Stock ID must be positive.");
        }
        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID must be positive.");
        }
        if (quantityOnShelf < 0) {
            throw new IllegalArgumentException("Quantity on shelf cannot be negative.");
        }

        this.shelfStockId = shelfStockId;
        this.productId = productId;
        this.quantityOnShelf = quantityOnShelf;
    }

    // Getters for immutable fields
    public int getShelfStockId() {
        return shelfStockId;
    }

    public int getProductId() {
        return productId;
    }

    // Getter for quantity on shelf
    public int getQuantityOnShelf() {
        return quantityOnShelf;
    }

    // Setter for quantity with validation
    public void setQuantityOnShelf(int quantityOnShelf) {
        if (quantityOnShelf < 0) {
            throw new IllegalArgumentException("Quantity on shelf cannot be negative.");
        }
        this.quantityOnShelf = quantityOnShelf;
    }

    // Additional methods can be added in a service or utility class for business logic
}