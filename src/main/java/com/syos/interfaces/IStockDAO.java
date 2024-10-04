package com.syos.interfaces;

import com.syos.model.Stock;
import com.syos.exceptions.StockOperationException;

public interface IStockDAO {
    void reduceStockQuantity(int productId, int quantitySold) throws StockOperationException;
    Stock getStockByProductId(int productId) throws StockOperationException;
    void updateStockQuantity(int productId, int quantity) throws StockOperationException;
}