package com.syos.factory;

import com.syos.dao.BatchDAO;
import com.syos.dao.ShelfStockDAO;
import com.syos.dao.StockDAO;
import com.syos.interfaces.IStockDAO;
import com.syos.interfaces.IShelfStockDAO;
import com.syos.interfaces.IBatchDAO;
import com.syos.service.StockService;

public class StockServiceFactory {

    // Static method to create StockService with all dependencies
    public static StockService createStockService(IStockDAO stockDAO, IShelfStockDAO shelfStockDAO, IBatchDAO batchDAO) {
        int restockThreshold = 20;  // The default or configurable restock threshold
        return new StockService(stockDAO, shelfStockDAO, batchDAO, restockThreshold);
    }
}