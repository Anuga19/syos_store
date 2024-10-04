//package com.syos;
//
//import com.syos.cli.CheckoutCLI;
//import com.syos.config.DatabaseConfig;
//import com.syos.dao.*;
//import com.syos.factory.BillServiceFactory;
//import com.syos.factory.ServiceFactory;
//import com.syos.factory.StockServiceFactory;
//import com.syos.interfaces.IBillDAO;
//import com.syos.service.*;
//import com.syos.utilities.BillCalculator;
//
//import java.sql.Connection;
//
//public class Main {
//    public static void main(String[] args) {
//        try {
//            // Initialize database connection
//            Connection connection = DatabaseConfig.getConnection();
//            System.out.println("Connected to the database!");
//
//            // Initialize DAOs
//            ProductDAO productDAO = new ProductDAO(connection);  // Initialize ProductDAO
//            OrderDAO orderDAO = new OrderDAO(connection);        // Initialize OrderDAO
//            PaymentDAO paymentDAO = new PaymentDAO(connection);  // Initialize PaymentDAO
//            StockDAO stockDAO = new StockDAO(connection);        // Initialize StockDAO
//            ShelfStockDAO shelfStockDAO = new ShelfStockDAO(connection);  // Initialize ShelfStockDAO
//            BatchDAO batchDAO = new BatchDAO(connection);        // Initialize BatchDAO
//            OrderItemsDAO orderItemsDAO = new OrderItemsDAO(connection);  // Initialize OrderItemsDAO
//            IBillDAO billDAO = new BillDAO(connection);          // Initialize BillDAO
//            BillCalculator billCalculator = new BillCalculator(); // Initialize BillCalculator
//
//            // Initialize Services using the factory pattern
//            StockService stockService = StockServiceFactory.createStockService(stockDAO, shelfStockDAO, batchDAO);  // Use StockServiceFactory
//            ProductService productService = new ProductService(productDAO, stockService);  // Pass ProductDAO and StockService
//            OrderService orderService = ServiceFactory.createOrderService();  // Initialize OrderService using ServiceFactory
//            BillService billService = BillServiceFactory.createBillService(connection);  // Initialize BillService using BillServiceFactory
//            PaymentService paymentService = new PaymentService(paymentDAO);  // Initialize PaymentService
//
//            // Initialize CLI for checkout
//            CheckoutCLI checkoutCLI = new CheckoutCLI(productService, orderService, paymentService, stockService, billService);
//
//            // Start the checkout process
//            checkoutCLI.startCheckout();
//
//            // Close the connection after checkout is complete
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error occurred during checkout: " + e.getMessage());
//        }
//    }
//}

//package com.syos;
//
//import com.syos.cli.CheckoutCLI;
//import com.syos.cli.StockManagementCLI;  // Import the new StockManagementCLI
//import com.syos.config.DatabaseConfig;
//import com.syos.dao.*;
//import com.syos.factory.BillServiceFactory;
//import com.syos.factory.ServiceFactory;
//import com.syos.interfaces.*;
//import com.syos.service.*;
//import com.syos.utilities.BillCalculator;
//
//import java.sql.Connection;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        try {
//            // Initialize database connection
//            Connection connection = DatabaseConfig.getConnection();
//            System.out.println("Connected to the database!");
//
//            // Initialize DAOs
//            IProductDAO productDAO = new ProductDAO(connection);  // Initialize ProductDAO
//            IOrderDAO orderDAO = new OrderDAO(connection);        // Initialize OrderDAO
//            IPaymentDAO paymentDAO = new PaymentDAO(connection);  // Initialize PaymentDAO
//            IStockDAO stockDAO = new StockDAO(connection);        // Initialize StockDAO
//            IShelfStockDAO shelfStockDAO = new ShelfStockDAO(connection);  // Initialize ShelfStockDAO
//            IBatchDAO batchDAO = new BatchDAO(connection);        // Initialize BatchDAO
//            IOrderItemsDAO orderItemsDAO = new OrderItemsDAO(connection);  // Initialize OrderItemsDAO
//            IBillDAO billDAO = new BillDAO(connection);           // Initialize BillDAO
//            BillCalculator billCalculator = new BillCalculator();
//
//            // Initialize Services
//            StockService stockService = new StockService(stockDAO, shelfStockDAO, batchDAO, 20);  // Pass the restock threshold as 20
//            ProductService productService = new ProductService(productDAO, stockService);  // Pass ProductDAO and StockService
//            OrderService orderService = ServiceFactory.createOrderService();  // Using the ServiceFactory for OrderService
//            BillService billService = BillServiceFactory.createBillService(connection);  // Initialize BillService
//            PaymentService paymentService = new PaymentService(paymentDAO);  // Initialize PaymentService
//            BatchService batchService = new BatchService(batchDAO);  // Initialize BatchService
//            ShelfStockService shelfStockService = new ShelfStockService(shelfStockDAO);  // Initialize ShelfStockService
//
//            // CLI options
//            Scanner scanner = new Scanner(System.in);
//            boolean running = true;
//            while (running) {
//                System.out.println("1. Start Checkout Process");
//                System.out.println("2. Start Stock Management Process");
//                System.out.println("3. Exit");
//                System.out.print("Enter your choice: ");
//
//                int choice = scanner.nextInt();
//                switch (choice) {
//                    case 1:
//                        // Initialize and start the checkout process
//                        CheckoutCLI checkoutCLI = new CheckoutCLI(productService, orderService, paymentService, stockService, billService);
//                        checkoutCLI.startCheckout();
//                        break;
//                    case 2:
//                        // Initialize and start the stock management process
//                        StockManagementCLI stockManagementCLI = new StockManagementCLI(productService, batchService, shelfStockService);
//                        stockManagementCLI.startStockManagement();
//                        break;
//                    case 3:
//                        running = false;
//                        break;
//                    default:
//                        System.out.println("Invalid option. Try again.");
//                }
//            }
//
//            // Close the connection after the processes are complete
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error occurred: " + e.getMessage());
//        }
//    }
//}

//package com.syos;
//
//import com.syos.cli.CheckoutCLI;
//import com.syos.cli.StockManagementCLI;
//import com.syos.cli.ReportsCLI;  // Import the ReportsCLI
//import com.syos.config.DatabaseConfig;
//import com.syos.dao.*;
//import com.syos.factory.BillServiceFactory;
//import com.syos.factory.ServiceFactory;
//import com.syos.factory.StockServiceFactory;
//import com.syos.interfaces.*;
//import com.syos.reports.TotalSalesReportGenerator;
//import com.syos.reports.ReorderLevelReportGenerator;
//import com.syos.service.*;
//import com.syos.utilities.BillCalculator;
//
//import java.sql.Connection;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        try {
//            // Initialize database connection
//            Connection connection = DatabaseConfig.getConnection();
//            System.out.println("Connected to the database!");
//
//            // Initialize DAOs
//            IProductDAO productDAO = new ProductDAO(connection);
//            IOrderDAO orderDAO = new OrderDAO(connection);
//            IPaymentDAO paymentDAO = new PaymentDAO(connection);
//            IStockDAO stockDAO = new StockDAO(connection);
//            IShelfStockDAO shelfStockDAO = new ShelfStockDAO(connection);
//            IBatchDAO batchDAO = new BatchDAO(connection);
//            IOrderItemsDAO orderItemsDAO = new OrderItemsDAO(connection);
//            IBillDAO billDAO = new BillDAO(connection);
//            ReportDAO reportDAO = new ReportDAO(connection);  // Initialize ReportDAO
//            BillCalculator billCalculator = new BillCalculator();
//
//            // Initialize Services
//            StockService stockService = StockServiceFactory.createStockService(stockDAO, shelfStockDAO, batchDAO);  // Use factory
//            ProductService productService = new ProductService(productDAO, stockService);
//            OrderService orderService = ServiceFactory.createOrderService();
//            BillService billService = BillServiceFactory.createBillService(connection);
//            PaymentService paymentService = new PaymentService(paymentDAO);
//            BatchService batchService = new BatchService(batchDAO);
//            ShelfStockService shelfStockService = new ShelfStockService(shelfStockDAO);
//
//            // Initialize Report Generators and Service
//            TotalSalesReportGenerator totalSalesReport = new TotalSalesReportGenerator(reportDAO);
//            ReorderLevelReportGenerator reorderLevelReport = new ReorderLevelReportGenerator(reportDAO);
//            ReportService reportService = new ReportService(totalSalesReport, reorderLevelReport);
//
//            // CLI options
//            Scanner scanner = new Scanner(System.in);
//            boolean running = true;
//            while (running) {
//                System.out.println("1. Start Checkout Process");
//                System.out.println("2. Start Stock Management Process");
//                System.out.println("3. Generate Reports");
//                System.out.println("4. Exit");
//                System.out.print("Enter your choice: ");
//
//                int choice = scanner.nextInt();
//                switch (choice) {
//                    case 1:
//                        // Start checkout process
//                        CheckoutCLI checkoutCLI = new CheckoutCLI(productService, orderService, paymentService, stockService, billService);
//                        checkoutCLI.startCheckout();
//                        break;
//                    case 2:
//                        // Start stock management process
//                        StockManagementCLI stockManagementCLI = new StockManagementCLI(productService, batchService, shelfStockService);
//                        stockManagementCLI.startStockManagement();
//                        break;
//                    case 3:
//                        // Start report generation process
//                        ReportsCLI reportsCLI = new ReportsCLI(reportService, reportDAO);
//                        reportsCLI.startReportsCLI();
//                        break;
//                    case 4:
//                        running = false;
//                        break;
//                    default:
//                        System.out.println("Invalid option. Try again.");
//                }
//            }
//
//            // Close the connection after processes are complete
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error occurred: " + e.getMessage());
//        }
//    }
//}


package com.syos;

import com.syos.cli.CheckoutCLI;
import com.syos.cli.StockManagementCLI;
import com.syos.cli.ReportsCLI;  // Import the ReportsCLI
import com.syos.config.DatabaseConfig;
import com.syos.dao.*;
import com.syos.factory.BillServiceFactory;
import com.syos.factory.ServiceFactory;
import com.syos.factory.StockServiceFactory;
import com.syos.interfaces.*;
import com.syos.reports.TotalSalesReportGenerator;
import com.syos.reports.ReorderLevelReportGenerator;
import com.syos.reports.StockReportGenerator;  // Import StockReportGenerator
import com.syos.reports.BillReportGenerator;   // Import BillReportGenerator
import com.syos.service.*;
import com.syos.utilities.BillCalculator;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialize database connection
            Connection connection = DatabaseConfig.getConnection();
            System.out.println("Connected to the database!");

            // Initialize DAOs
            IProductDAO productDAO = new ProductDAO(connection);
            IOrderDAO orderDAO = new OrderDAO(connection);
            IPaymentDAO paymentDAO = new PaymentDAO(connection);
            IStockDAO stockDAO = new StockDAO(connection);
            IShelfStockDAO shelfStockDAO = new ShelfStockDAO(connection);
            IBatchDAO batchDAO = new BatchDAO(connection);
            IOrderItemsDAO orderItemsDAO = new OrderItemsDAO(connection);
            IBillDAO billDAO = new BillDAO(connection);
            ReportDAO reportDAO = new ReportDAO(connection);  // Initialize ReportDAO
            BillCalculator billCalculator = new BillCalculator();

            // Initialize Services
            StockService stockService = StockServiceFactory.createStockService(stockDAO, shelfStockDAO, batchDAO);  // Use factory
            ProductService productService = new ProductService(productDAO, stockService);
            OrderService orderService = ServiceFactory.createOrderService();
            BillService billService = BillServiceFactory.createBillService(connection);
            PaymentService paymentService = new PaymentService(paymentDAO);
            BatchService batchService = new BatchService(batchDAO);
            ShelfStockService shelfStockService = new ShelfStockService(shelfStockDAO, stockDAO);

            // Initialize Report Generators
            TotalSalesReportGenerator totalSalesReport = new TotalSalesReportGenerator(reportDAO);
            ReorderLevelReportGenerator reorderLevelReport = new ReorderLevelReportGenerator(reportDAO);
            StockReportGenerator stockReport = new StockReportGenerator(reportDAO);  // New report generator
            BillReportGenerator billReport = new BillReportGenerator(reportDAO);     // New report generator

            // Create a map of report generators
            Map<String, IReportGenerator> reportGenerators = new HashMap<>();
            reportGenerators.put("TotalSales", totalSalesReport);
            reportGenerators.put("ReorderLevel", reorderLevelReport);
            reportGenerators.put("StockReport", stockReport);  // Add StockReport
            reportGenerators.put("BillReport", billReport);    // Add BillReport

            // Initialize ReportService with the map of report generators
            ReportService reportService = new ReportService(reportGenerators);

            // CLI options
            Scanner scanner = new Scanner(System.in);
            boolean running = true;
            while (running) {
                System.out.println("1. Start Checkout Process");
                System.out.println("2. Start Stock Management Process");
                System.out.println("3. Generate Reports");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        // Start checkout process
                        CheckoutCLI checkoutCLI = new CheckoutCLI(productService, orderService, paymentService, stockService, billService);
                        checkoutCLI.startCheckout();
                        break;
                    case 2:
                        // Start stock management process
                        StockManagementCLI stockManagementCLI = new StockManagementCLI(productService, batchService, shelfStockService);
                        stockManagementCLI.startStockManagement();
                        break;
                    case 3:
                        // Start report generation process
                        ReportsCLI reportsCLI = new ReportsCLI(reportService);
                        reportsCLI.startReportsCLI();
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }

            // Close the connection after processes are complete
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}