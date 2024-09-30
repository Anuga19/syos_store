package com.syos.service;

import com.syos.dao.ShelfStockDAO;
import com.syos.dao.StockDAO;
import com.syos.model.Order;
import com.syos.model.OrderItem;
import com.syos.model.ShelfStock;

import java.sql.SQLException;

public class StockService {
    private final StockDAO stockDAO;
    private final ShelfStockDAO shelfStockDAO;

    public StockService(StockDAO stockDAO, ShelfStockDAO shelfStockDAO) {
        this.stockDAO = stockDAO;
        this.shelfStockDAO = shelfStockDAO;
    }

    // Check if enough stock is available on the shelf for a product
    public boolean checkStockForProduct(int productId, int quantity) throws SQLException {
        ShelfStock shelfStock = shelfStockDAO.getShelfStockByProductId(productId);
        return shelfStock != null && shelfStock.getQuantityOnShelf() >= quantity;
    }

    // Update the stock after a sale
    public void updateStockAfterSale(Order order) throws SQLException {
        for (OrderItem item : order.getOrderItems()) {
            shelfStockDAO.updateShelfStock(item.getProduct().getProductId(), item.getQuantity());
            stockDAO.updateStockQuantity(item.getProduct().getProductId(), item.getQuantity());
        }
    }
}