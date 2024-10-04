package com.syos.service;

import com.syos.interfaces.IBatchDAO;
import com.syos.model.Batch;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class BatchService {

    private final IBatchDAO batchDAO;  // Depend on IBatchDAO interface

    // Constructor to inject IBatchDAO dependency
    public BatchService(IBatchDAO batchDAO) {
        this.batchDAO = batchDAO;
    }

    // Add or update a batch
    public void addOrUpdateBatch(int productId, String batchNumber, int quantity, Date expiryDate) throws SQLException {
        List<Batch> existingBatches = batchDAO.getBatchesForProduct(productId);

        // If the batch already exists, update it; otherwise, create a new one
        Batch existingBatch = existingBatches.stream()
                .filter(batch -> batch.getBatchNumber().equals(batchNumber))
                .findFirst()
                .orElse(null);

        if (existingBatch != null) {
            // Update existing batch
            int newQuantity = existingBatch.getQuantity() + quantity;
            batchDAO.updateBatchQuantity(existingBatch.getBatchId(), newQuantity);
        } else {
            // Add new batch
            Batch newBatch = new Batch(0, productId, batchNumber, new Date(System.currentTimeMillis()), expiryDate, quantity, 0); // Assume supplier ID is 0 for now
            batchDAO.addBatch(newBatch);
        }
    }

    // Retrieve all batches for a product
    public List<Batch> getBatchesForProduct(int productId) throws SQLException {
        return batchDAO.getBatchesForProduct(productId);
    }
}