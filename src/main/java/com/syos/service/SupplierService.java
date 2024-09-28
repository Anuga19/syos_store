package com.syos.service;

import com.syos.dao.SupplierDAO;
import com.syos.model.Supplier;

import java.sql.SQLException;
import java.util.List;

public class SupplierService {
    private final SupplierDAO supplierDAO;

    public SupplierService(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

    // Add a new supplier
    public void addSupplier(String supplierName, String contactDetails) throws SQLException {
        Supplier supplier = new Supplier(supplierName, contactDetails);
        supplierDAO.saveSupplier(supplier);
    }

    // Retrieve supplier by ID
    public Supplier getSupplierById(int supplierId) throws SQLException {
        return supplierDAO.getSupplierById(supplierId);
    }

    // Retrieve all suppliers
    public List<Supplier> getAllSuppliers() throws SQLException {
        return supplierDAO.getAllSuppliers();
    }

    // Update supplier details
    public void updateSupplier(Supplier supplier) throws SQLException {
        supplierDAO.updateSupplier(supplier);
    }

    // Delete supplier
    public void deleteSupplier(int supplierId) throws SQLException {
        supplierDAO.deleteSupplier(supplierId);
    }
}