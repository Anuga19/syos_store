package com.syos.service;

import com.syos.dao.ProductDAO;
import com.syos.model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    private final ProductDAO productDAO;
    private final StockService stockService;  // Add StockService dependency

    public ProductService(ProductDAO productDAO, StockService stockService) {
        this.productDAO = productDAO;
        this.stockService = stockService;  // Initialize the StockService instance
    }

    // Add a new product
    public void addProduct(Product product) throws SQLException {
        productDAO.addProduct(product);
    }

    // Get a product by ID
    public Product getProductById(int productId) throws SQLException {
        return productDAO.getProductById(productId);
    }

    // Update an existing product
    public void updateProduct(Product product) throws SQLException {
        productDAO.updateProduct(product);
    }

    // Delete a product by ID
    public void deleteProduct(int productId) throws SQLException {
        productDAO.deleteProduct(productId);
    }

    // Get all products
    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }

    public Product getProductByCode(String productCode) {
        return productDAO.findByProductCode(productCode);
    }

    // Use the StockService instance to check stock availability
    public boolean checkStockAvailability(Product product, int quantity) throws SQLException {
        return stockService.checkStockForProduct(product.getProductId(), quantity);
    }
}