package com.syos.interfaces;

import com.syos.model.ShelfStock;

import java.sql.SQLException;

public interface IShelfStockDAO {
    ShelfStock getShelfStockByProductId(int productId) throws SQLException;
    void reduceShelfStock(int productId, int quantitySold) throws SQLException;
//    void addShelfStock(int productId, int quantityToAdd) throws SQLException;
//    int getShelfStockForProduct(int productId) throws SQLException;

    // Add stock to the shelf
    void addShelfStock(int productId, int quantityToAdd) throws SQLException;

    // Update the shelf stock quantity (missing method in your current interface)
    void updateShelfStockQuantity(int productId, int newQuantity) throws SQLException;

    // Get the current quantity of stock on the shelf
    int getShelfStockForProduct(int productId) throws SQLException;


}
