package com.syos.interfaces;

import com.syos.model.Batch;

import java.sql.SQLException;
import java.util.List;

public interface IBatchDAO {
    List<Batch> getBatchesForProduct(int productId) throws SQLException;
    void updateBatchQuantity(int batchId, int newQuantity) throws SQLException;

    // Add a new batch (missing method in your current interface)
    void addBatch(Batch batch) throws SQLException;
}
