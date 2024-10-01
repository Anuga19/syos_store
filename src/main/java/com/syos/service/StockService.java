package com.syos.service;

import com.syos.dao.BatchDAO;
import com.syos.dao.ShelfStockDAO;
import com.syos.dao.StockDAO;
import com.syos.model.Batch;
import com.syos.model.Order;
import com.syos.model.OrderItem;
import com.syos.model.ShelfStock;

import java.sql.SQLException;
import java.util.List;

public class StockService {
    private final StockDAO stockDAO;
    private final ShelfStockDAO shelfStockDAO;
    private final BatchDAO batchDAO;  // Add BatchDAO here
    private final int RESTOCK_THRESHOLD = 20;  // Define the shelf stock threshold for restocking

    public StockService(StockDAO stockDAO, ShelfStockDAO shelfStockDAO, BatchDAO batchDAO) {
        this.stockDAO = stockDAO;
        this.shelfStockDAO = shelfStockDAO;
        this.batchDAO = batchDAO;
    }

    // Method to check if restocking is needed and perform restocking if required
    public void checkAndRestockShelf(int productId) throws SQLException {
        int currentShelfStock = shelfStockDAO.getShelfStockForProduct(productId);
        if (currentShelfStock < RESTOCK_THRESHOLD) {
            restockShelfFromStock(productId, RESTOCK_THRESHOLD - currentShelfStock);
        }
    }

    // Restocking logic: move stock from storage (Batch) to shelf (Shelf Stock)
    public void restockShelfFromStock(int productId, int neededQuantity) throws SQLException {
        // Fetch batches sorted by expiry date and date received (FIFO)
        List<Batch> batches = batchDAO.getBatchesForProduct(productId);

        int remainingQuantity = neededQuantity;  // Amount needed to restock shelf

        // Loop through the batches to move stock to shelf
        for (Batch batch : batches) {
            if (remainingQuantity <= 0) {
                break;  // Stop once we've fulfilled the needed quantity
            }

            int availableInBatch = batch.getQuantity();  // Available quantity in the batch

            if (availableInBatch > 0) {
                // Determine how much to move from this batch
                int quantityToMove = Math.min(availableInBatch, remainingQuantity);

                // Update batch to reduce the quantity in storage
                batchDAO.updateBatchQuantity(batch.getBatchId(), availableInBatch - quantityToMove);

                // Update the shelf stock to increase the quantity
                shelfStockDAO.addShelfStock(productId, quantityToMove);

                // Subtract the moved quantity from remaining quantity to be restocked
                remainingQuantity -= quantityToMove;
            }
        }

        // If remainingQuantity > 0, not enough stock was available in storage to fully restock
        if (remainingQuantity > 0) {
            System.out.println("Not enough stock in storage to fully restock the shelf.");
        }
    }


    // Check if enough stock is available on the shelf for a product
    public boolean checkStockForProduct(int productId, int quantity) throws SQLException {
        ShelfStock shelfStock = shelfStockDAO.getShelfStockByProductId(productId);
        return shelfStock != null && shelfStock.getQuantityOnShelf() >= quantity;
    }

    // Update the stock after a sale
    public void updateStockAfterSale(Order order) throws SQLException {
        for (OrderItem item : order.getOrderItems()) {
            shelfStockDAO.reduceShelfStock(item.getProduct().getProductId(), item.getQuantity());
            //stockDAO.updateStockQuantity(item.getProduct().getProductId(), item.getQuantity());
        }
    }
}