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
            ProductDAO productDAO = new ProductDAO(connection);
            OrderDAO orderDAO = new OrderDAO(connection);
            BillDAO billDAO = new BillDAO(connection);
            PaymentDAO paymentDAO = new PaymentDAO(connection);
            StockDAO stockDAO = new StockDAO(connection);

            // Initialize Services
            ProductService productService = new ProductService(productDAO);
            OrderService orderService = new OrderService(orderDAO);
            BillService billService = new BillService(billDAO);
            PaymentService paymentService = new PaymentService(paymentDAO);
            StockService stockService = new StockService(stockDAO);

            // Initialize CLI for checkout
            CheckoutCLI checkoutCLI = new CheckoutCLI(productService, orderService, paymentService, stockService, billService);

            // Start the checkout process
            checkoutCLI.startCheckout();

            connection.close();  // Close the connection after checkout is complete
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred during checkout: " + e.getMessage());
        }
    }
}