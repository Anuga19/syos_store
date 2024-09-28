package com.syos.service;

import com.syos.dao.StockDAO;
import com.syos.model.Order;
import com.syos.model.OrderItem;

public class StockService {
    private final StockDAO stockDAO;

    public StockService(StockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }

    public void updateStockAfterSale(Order order) {
        for (OrderItem item : order.getOrderItems()) {
            stockDAO.updateStock(item.getProduct().getProductId(), item.getQuantity());
        }
    }
}