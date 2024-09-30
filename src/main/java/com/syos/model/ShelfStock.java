package com.syos.model;

public class ShelfStock {
    private int shelfStockId;
    private int productId;
    private int quantityOnShelf;

    // Constructor
    public ShelfStock(int shelfStockId, int productId, int quantityOnShelf) {
        this.shelfStockId = shelfStockId;
        this.productId = productId;
        this.quantityOnShelf = quantityOnShelf;
    }

    // Getters and Setters
    public int getShelfStockId() {
        return shelfStockId;
    }

    public void setShelfStockId(int shelfStockId) {
        this.shelfStockId = shelfStockId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantityOnShelf() {
        return quantityOnShelf;
    }

    public void setQuantityOnShelf(int quantityOnShelf) {
        this.quantityOnShelf = quantityOnShelf;
    }
}