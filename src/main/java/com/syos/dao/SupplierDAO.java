package com.syos.dao;

import com.syos.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {
    private final Connection connection;

    public SupplierDAO(Connection connection) {
        this.connection = connection;
    }

    // Save a new supplier
    public void saveSupplier(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO suppliers (supplier_name, contact_details) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, supplier.getSupplierName());
            statement.setString(2, supplier.getContactDetails());
            statement.executeUpdate();
        }
    }

    // Get a supplier by ID
    public Supplier getSupplierById(int supplierId) throws SQLException {
        String sql = "SELECT * FROM suppliers WHERE supplier_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, supplierId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Supplier(
                        resultSet.getInt("supplier_id"),
                        resultSet.getString("supplier_name"),
                        resultSet.getString("contact_details")
                );
            }
        }
        return null;
    }

    // Retrieve all suppliers
    public List<Supplier> getAllSuppliers() throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                suppliers.add(new Supplier(
                        resultSet.getInt("supplier_id"),
                        resultSet.getString("supplier_name"),
                        resultSet.getString("contact_details")
                ));
            }
        }
        return suppliers;
    }

    // Update supplier details
    public void updateSupplier(Supplier supplier) throws SQLException {
        String sql = "UPDATE suppliers SET supplier_name = ?, contact_details = ? WHERE supplier_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, supplier.getSupplierName());
            statement.setString(2, supplier.getContactDetails());
            statement.setInt(3, supplier.getSupplierId());
            statement.executeUpdate();
        }
    }

    // Delete supplier by ID
    public void deleteSupplier(int supplierId) throws SQLException {
        String sql = "DELETE FROM suppliers WHERE supplier_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, supplierId);
            statement.executeUpdate();
        }
    }
}