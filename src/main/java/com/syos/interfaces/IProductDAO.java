package com.syos.interfaces;

import com.syos.model.Product;
import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    void addProduct(Product product) throws SQLException;
    Product getProductById(int productId) throws SQLException;
    void updateProduct(Product product) throws SQLException;
    void deleteProduct(int productId) throws SQLException;
    List<Product> getAllProducts() throws SQLException;
    Product findByProductCode(String productCode) throws SQLException;
}