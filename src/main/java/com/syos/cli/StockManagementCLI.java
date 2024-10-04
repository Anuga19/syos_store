package com.syos.cli;

import com.syos.model.Product;
import com.syos.service.ProductService;
import com.syos.service.BatchService;
import com.syos.service.ShelfStockService;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Scanner;

public class StockManagementCLI {

    private final ProductService productService;
    private final BatchService batchService;
    private final ShelfStockService shelfStockService;

    // Constructor to inject the services (ProductService, BatchService, ShelfStockService)
    public StockManagementCLI(ProductService productService, BatchService batchService, ShelfStockService shelfStockService) {
        this.productService = productService;
        this.batchService = batchService;
        this.shelfStockService = shelfStockService;
    }

    public void startStockManagement() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Add New Product");
            System.out.println("2. Update Batch Information");
            System.out.println("3. Manually Restock Shelves");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addNewProduct(scanner);
                    break;
                case 2:
                    updateBatchInformation(scanner);
                    break;
                case 3:
                    restockShelves(scanner);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Adding new product using the ProductService
    private void addNewProduct(Scanner scanner) {
        System.out.print("Enter product code: ");
        String productCode = scanner.next();

        System.out.print("Enter product name: ");
        String productName = scanner.next();

        System.out.print("Enter product price: ");
        BigDecimal price = scanner.nextBigDecimal();

        // Create new product and add to the system
        Product newProduct = new Product( productCode, productName, price);
        try {
            productService.addProduct(newProduct);
            System.out.println("Product added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    // Updating batch information using the BatchService
    private void updateBatchInformation(Scanner scanner) {
        System.out.print("Enter product ID to update batches for: ");
        int productId = scanner.nextInt();

        System.out.print("Enter batch number: ");
        String batchNumber = scanner.next();

        System.out.print("Enter quantity in batch: ");
        int quantity = scanner.nextInt();

        System.out.print("Enter expiry date (yyyy-mm-dd): ");
        String expiryDateStr = scanner.next();
        Date expiryDate = Date.valueOf(expiryDateStr); // Convert string to SQL Date

        // Add or update batch
        try {
            batchService.addOrUpdateBatch(productId, batchNumber, quantity, expiryDate);
            System.out.println("Batch updated successfully!");
        } catch (Exception e) {
            System.out.println("Error updating batch: " + e.getMessage());
        }
    }

    // Manually restock shelves using the ShelfStockService
//    private void restockShelves(Scanner scanner) {
//        System.out.print("Enter product ID to restock: ");
//        int productId = scanner.nextInt();
//
//        System.out.print("Enter quantity to restock: ");
//        int quantityToRestock = scanner.nextInt();
//
//        // Update shelf stock
//        try {
//            shelfStockService.restockShelf(productId, quantityToRestock);
//            System.out.println("Shelf restocked successfully!");
//        } catch (Exception e) {
//            System.out.println("Error restocking shelf: " + e.getMessage());
//        }
//    }

    // Manually restock shelves using the ShelfStockService
    private void restockShelves(Scanner scanner) {
        System.out.print("Enter product ID to restock: ");
        int productId = scanner.nextInt();

        System.out.print("Enter quantity to restock: ");
        int quantityToRestock = scanner.nextInt();

        // Update shelf stock and stock quantity
        try {
            shelfStockService.restockShelf(productId, quantityToRestock);
        } catch (Exception e) {
            System.out.println("Error restocking shelf: " + e.getMessage());
        }
    }
}