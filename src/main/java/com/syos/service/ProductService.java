//package com.syos.service;
//
//import com.syos.dao.ProductDAO;
//import com.syos.model.Product;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class ProductService {
//
//    private final ProductDAO productDAO;
//    private final StockService stockService;  // Add StockService dependency
//
//    public ProductService(ProductDAO productDAO, StockService stockService) {
//        this.productDAO = productDAO;
//        this.stockService = stockService;  // Initialize the StockService instance
//    }
//
//    // Add a new product
//    public void addProduct(Product product) throws SQLException {
//        productDAO.addProduct(product);
//    }
//
//    // Get a product by ID
//    public Product getProductById(int productId) throws SQLException {
//        return productDAO.getProductById(productId);
//    }
//
//    // Update an existing product
//    public void updateProduct(Product product) throws SQLException {
//        productDAO.updateProduct(product);
//    }
//
//    // Delete a product by ID
//    public void deleteProduct(int productId) throws SQLException {
//        productDAO.deleteProduct(productId);
//    }
//
//    // Get all products
//    public List<Product> getAllProducts() throws SQLException {
//        return productDAO.getAllProducts();
//    }
//
//    public Product getProductByCode(String productCode) {
//        return productDAO.findByProductCode(productCode);
//    }
//
//    // Use the StockService instance to check stock availability
//    public boolean checkStockAvailability(Product product, int quantity) throws SQLException {
//        return stockService.checkStockForProduct(product.getProductId(), quantity);
//    }
//}
 //// ------------------//

//package com.syos.service;
//
//import com.syos.interfaces.IProductDAO;  // Use interface instead of concrete class
//import com.syos.model.Product;
//
//import java.math.BigDecimal;
//import java.sql.SQLException;
//import java.util.List;
//
//public class ProductService {
//
//    private final IProductDAO productDAO;  // Depend on IProductDAO interface
//    private final StockService stockService;  // Add StockService dependency
//
//    public ProductService(IProductDAO productDAO, StockService stockService) {
//        this.productDAO = productDAO;
//        this.stockService = stockService;  // Initialize the StockService instance
//    }
//
//    // Add a new product
//    public void addProduct(Product product) throws SQLException {
//        productDAO.addProduct(product);
//    }
//
//    // Get a product by ID
//    public Product getProductById(int productId) throws SQLException {
//        return productDAO.getProductById(productId);
//    }
//
//    // Update an existing product
//    public void updateProduct(Product product) throws SQLException {
//        productDAO.updateProduct(product);
//    }
//
//    // Delete a product by ID
//    public void deleteProduct(int productId) throws SQLException {
//        productDAO.deleteProduct(productId);
//    }
//
//    // Get all products
//    public List<Product> getAllProducts() throws SQLException {
//        return productDAO.getAllProducts();
//    }
//
//    // Find a product by product code
//    public Product getProductByCode(String productCode) throws SQLException {
//        return productDAO.findByProductCode(productCode);
//    }
//
//    // Use the StockService instance to check stock availability
//    public boolean checkStockAvailability(Product product, int quantity) throws SQLException {
//        return stockService.checkStockForProduct(product.getProductId(), quantity);
//    }
//
//    // Adding new product
//    public void addNewProduct(String productCode, String productName, BigDecimal price) throws Exception {
//        // Business logic for adding a new product can be placed here
//        productDAO.addProduct(new Product(productCode, productName, price));
//    }
//}

/// refacto 2nd time --/

package com.syos.service;

import com.syos.interfaces.IProductDAO;  // Use interface instead of concrete class
import com.syos.model.Product;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class ProductService {

    private final IProductDAO productDAO;  // Depend on IProductDAO interface
    private final StockService stockService;  // Add StockService dependency

    // Constructor to inject IProductDAO and StockService dependencies
    public ProductService(IProductDAO productDAO, StockService stockService) {
        this.productDAO = productDAO;
        this.stockService = stockService;
    }

    // Add a new product to the database
    public void addProduct(Product product) throws SQLException {
        productDAO.addProduct(product);
    }

    // Get a product by its ID
    public Product getProductById(int productId) throws SQLException {
        return productDAO.getProductById(productId);
    }

    // Update an existing product in the database
    public void updateProduct(Product product) throws SQLException {
        productDAO.updateProduct(product);
    }

    // Delete a product by its ID
    public void deleteProduct(int productId) throws SQLException {
        productDAO.deleteProduct(productId);
    }

    // Get a list of all products
    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }

    // Find a product by its product code
    public Product getProductByCode(String productCode) throws SQLException {
        return productDAO.findByProductCode(productCode);
    }

    // Use the StockService to check stock availability for a product
    public boolean checkStockAvailability(String productCode, int quantity) throws SQLException {
        Product product = productDAO.findByProductCode(productCode);
        if (product != null) {
            return stockService.checkStockForProduct(product.getProductId(), quantity);
        }
        return false;
    }

    // Adding a new product by productCode, productName, and price
    public void addNewProduct(String productCode, String productName, BigDecimal price) throws SQLException {
        // Perform necessary validations
        if (productCode == null || productCode.isEmpty() || productName == null || productName.isEmpty() || price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid product details.");
        }

        // Create a new product and delegate the actual addition to productDAO
        Product newProduct = new Product(productCode, productName, price);
        productDAO.addProduct(newProduct);
    }
}