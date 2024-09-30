package com.syos;

import com.syos.cli.CheckoutCLI;
import com.syos.config.DatabaseConfig;
import com.syos.dao.*;
import com.syos.service.*;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialize database connection
            Connection connection = DatabaseConfig.getConnection();
            System.out.println("Connected to the database!");

            // Initialize DAOs
            ProductDAO productDAO = new ProductDAO(connection);  // Initialize ProductDAO
            OrderDAO orderDAO = new OrderDAO(connection);        // Initialize OrderDAO
            BillDAO billDAO = new BillDAO(connection);           // Initialize BillDAO
            PaymentDAO paymentDAO = new PaymentDAO(connection);  // Initialize PaymentDAO
            StockDAO stockDAO = new StockDAO(connection);        // Initialize StockDAO
            ShelfStockDAO shelfStockDAO = new ShelfStockDAO(connection);  // Initialize ShelfStockDAO

            // Initialize Services
            StockService stockService = new StockService(stockDAO, shelfStockDAO);  // Pass StockDAO and ShelfStockDAO
            ProductService productService = new ProductService(productDAO, stockService);  // Pass ProductDAO and StockService
            OrderService orderService = new OrderService(orderDAO);  // Initialize OrderService
            BillService billService = new BillService(billDAO);      // Initialize BillService
            PaymentService paymentService = new PaymentService(paymentDAO);  // Initialize PaymentService

            // Initialize CLI for checkout
            CheckoutCLI checkoutCLI = new CheckoutCLI(productService, orderService, paymentService, stockService, billService);

            // Start the checkout process
            checkoutCLI.startCheckout();

            // Close the connection after checkout is complete
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred during checkout: " + e.getMessage());
        }
    }
}