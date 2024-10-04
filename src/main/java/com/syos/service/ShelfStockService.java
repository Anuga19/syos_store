//package com.syos.service;
//
//import com.syos.interfaces.IShelfStockDAO;
//import com.syos.interfaces.IStockDAO;
//import com.syos.model.ShelfStock;
//import com.syos.model.Stock;
//
//import java.sql.SQLException;
//
//public class ShelfStockService {
//
//    private final IShelfStockDAO shelfStockDAO;
//    private final IStockDAO stockDAO;  // Added IStockDAO dependency
//
//    // Constructor to inject IShelfStockDAO and IStockDAO dependencies
//    public ShelfStockService(IShelfStockDAO shelfStockDAO, IStockDAO stockDAO) {
//        this.shelfStockDAO = shelfStockDAO;
//        this.stockDAO = stockDAO;
//    }
//
//    // Manually restock the shelf and deduct from stock
//    public void restockShelf(int productId, int quantityToRestock) throws SQLException {
//        // Check current stock
//        Stock stock = stockDAO.getStockByProductId(productId);
//
//        if (stock != null && stock.getQuantity() >= quantityToRestock) {
//            // Deduct quantity from stock table
//            int updatedStockQuantity = stock.getQuantity() - quantityToRestock;
//            stockDAO.updateStockQuantity(productId, updatedStockQuantity);
//
//            // Update shelf stock
//            ShelfStock existingShelfStock = shelfStockDAO.getShelfStockByProductId(productId);
//            if (existingShelfStock != null) {
//                // Add restocked quantity to existing shelf stock
//                int newQuantity = existingShelfStock.getQuantityOnShelf() + quantityToRestock;
//                shelfStockDAO.updateShelfStockQuantity(productId, newQuantity);
//            } else {
//                // Add new shelf stock entry if none exists
//                shelfStockDAO.addShelfStock(productId, quantityToRestock);
//            }
//
//            System.out.println("Shelf restocked and stock updated successfully!");
//        } else {
//            System.out.println("Insufficient stock to restock shelves.");
//        }
//    }
//
//    // Get the current shelf stock for a product
//    public int getShelfStockForProduct(int productId) throws SQLException {
//        ShelfStock shelfStock = shelfStockDAO.getShelfStockByProductId(productId);
//        return shelfStock != null ? shelfStock.getQuantityOnShelf() : 0;
//    }
//}

package com.syos.service;

import com.syos.interfaces.IShelfStockDAO;
import com.syos.interfaces.IStockDAO;
import com.syos.model.ShelfStock;
import com.syos.model.Stock;

import java.sql.SQLException;

public class ShelfStockService {

    private final IShelfStockDAO shelfStockDAO;
    private final IStockDAO stockDAO;

    // Constructor to inject IShelfStockDAO and IStockDAO dependencies
    public ShelfStockService(IShelfStockDAO shelfStockDAO, IStockDAO stockDAO) {
        this.shelfStockDAO = shelfStockDAO;
        this.stockDAO = stockDAO;
    }

    // Public method to restock shelves; this method delegates tasks to private methods
    public void restockShelf(int productId, int quantityToRestock) throws SQLException {
        if (!isStockAvailable(productId, quantityToRestock)) {
            System.out.println("Insufficient stock to restock shelves.");
            return;
        }
        deductFromStock(productId, quantityToRestock);
        updateShelfStock(productId, quantityToRestock);
        System.out.println("Shelf restocked and stock updated successfully!");
    }

    // Check if enough stock is available for restocking
    private boolean isStockAvailable(int productId, int quantityToRestock) throws SQLException {
        Stock stock = stockDAO.getStockByProductId(productId);
        return stock != null && stock.getQuantity() >= quantityToRestock;
    }

    // Deduct the quantity from the stock in the warehouse
    private void deductFromStock(int productId, int quantityToRestock) throws SQLException {
        Stock stock = stockDAO.getStockByProductId(productId);
        int updatedStockQuantity = stock.getQuantity() - quantityToRestock;
        stockDAO.updateStockQuantity(productId, updatedStockQuantity);
    }

    // Update the shelf stock with the new quantity
    private void updateShelfStock(int productId, int quantityToRestock) throws SQLException {
        ShelfStock existingShelfStock = shelfStockDAO.getShelfStockByProductId(productId);

        if (existingShelfStock != null) {
            int newQuantity = existingShelfStock.getQuantityOnShelf() + quantityToRestock;
            shelfStockDAO.updateShelfStockQuantity(productId, newQuantity);
        } else {
            shelfStockDAO.addShelfStock(productId, quantityToRestock);
        }
    }

    // Get the current shelf stock for a product
    public int getShelfStockForProduct(int productId) throws SQLException {
        ShelfStock shelfStock = shelfStockDAO.getShelfStockByProductId(productId);
        return shelfStock != null ? shelfStock.getQuantityOnShelf() : 0;
    }
}