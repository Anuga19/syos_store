package com.syos.cli;

import com.syos.model.OrderItem;
import com.syos.model.Product;
import com.syos.model.Order;
import com.syos.model.Bill;
import com.syos.service.*;
import java.sql.SQLException;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CheckoutCLI {
    private final ProductService productService;
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final StockService stockService;
    private final BillService billService;

    public CheckoutCLI(ProductService productService, OrderService orderService, PaymentService paymentService, StockService stockService, BillService billService) {
        this.productService = productService;
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.stockService = stockService;
        this.billService = billService;
    }

    public void startCheckout() {
        Scanner scanner = new Scanner(System.in);
        Order order = new Order();

        boolean moreItems = true;
        while (moreItems) {
            System.out.print("Enter Product Code: ");
            String productCode = scanner.nextLine();
            Product product = productService.getProductByCode(productCode);

            if (product != null) {
                System.out.print("Enter Quantity: ");
                int quantity = scanner.nextInt();

                try {
                    boolean isAvailable = stockService.checkStockForProduct(product.getProductId(), quantity);
                    if (isAvailable) {
                        orderService.addItemToOrder(order, product, quantity);
                        System.out.println(quantity + " " + product.getProductName() + " added to order.");
                    } else {
                        System.out.println("Insufficient stock for " + product.getProductName());
                    }
                } catch (SQLException e) {
                    System.out.println("Error checking stock availability: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid Product Code.");
            }

            System.out.print("Add more items? (y/n): ");
            String addMore = scanner.next();
            moreItems = addMore.equalsIgnoreCase("y");
            scanner.nextLine(); // Consume the newline
        }

        System.out.println("Calculating total...");
        orderService.calculateTotal(order);
        System.out.println("Total amount: " + order.getTotalAmount());

        System.out.print("Enter Discount (if any, %): ");
        double discountPercentage = scanner.nextDouble();
        BigDecimal discount = BigDecimal.ZERO;
        if (discountPercentage > 0) {
            discount = orderService.calculateDiscount(order, discountPercentage);
            System.out.println("Total after discount: " + order.getTotalAmount().subtract(discount));
        }

//        System.out.print("Enter Cash Tendered: ");
//        BigDecimal cashTendered = scanner.nextBigDecimal();
//
//        // Process payment and generate bill
//        try {
//            orderService.saveOrder(order);  // Ensure order is saved before bill generation
//        } catch (SQLException e) {
//            System.out.println("Error saving order: " + e.getMessage());
//        }

////        paymentService.processCashPayment(order, cashTendered);
//        paymentService.processCashPayment(bill, cashTendered);
//
//
//        try {
//            stockService.updateStockAfterSale(order);  // Handle SQLException for stock update
//        } catch (SQLException e) {
//            System.out.println("Error updating stock after sale: " + e.getMessage());
//        }
//
//        try {
//            Bill bill = billService.generateBill(order, cashTendered, discount);  // Capture the generated bill
//            printBill(order, bill, discount, cashTendered);  // Pass the Bill object to printBill
//        } catch (SQLException e) {
//            System.out.println("Error generating bill: " + e.getMessage());
//        }
//
//        System.out.println("Checkout complete. Bill generated.");

        System.out.print("Enter Cash Tendered: ");
        BigDecimal cashTendered = scanner.nextBigDecimal();

        // Save the order and generate the bill
        try {
            orderService.saveOrder(order);  // Ensure order is saved before bill generation
        } catch (SQLException e) {
            System.out.println("Error saving order: " + e.getMessage());
        }

        //updating the stock
        try {
            stockService.updateStockAfterSale(order);  // Handle SQLException for stock update
        } catch (SQLException e) {
            System.out.println("Error updating stock after sale: " + e.getMessage());
        }

        // Generate the bill and capture it in a variable
        Bill bill = null;
        try {
            bill = billService.generateBill(order, cashTendered, discount);  // Capture the generated bill
        } catch (SQLException e) {
            System.out.println("Error generating bill: " + e.getMessage());
        }

        // Process payment using the generated Bill object
        if (bill != null) {
            try {
                paymentService.processCashPayment(bill, cashTendered);  // Pass the Bill object, not the Order
            } catch (SQLException e) {
                System.out.println("Error processing payment: " + e.getMessage());
            }
        }

        // Print the bill
        printBill(order, bill, discount, cashTendered);

        System.out.println("Checkout complete. Bill generated.");
    }

    public void printBill(Order order, Bill bill, BigDecimal discount, BigDecimal cashTendered) {
        System.out.println("-------- SYOS STORE BILL --------");
        System.out.println("Bill Serial Number: " + bill.getBillId());  // Use the Bill object to get the bill ID (serial number)
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Bill Date: " + formatter.format(bill.getBillDate()));  // Use the actual bill date from the Bill object
        System.out.println("---------------------------------");

        for (OrderItem item : order.getOrderItems()) {
            BigDecimal itemTotal = item.getItemTotal();
            System.out.println("Item: " + item.getProduct().getProductName());
            System.out.println("Quantity: " + item.getQuantity());
            System.out.println("Item Total: $" + itemTotal);
            System.out.println("---------------------------------");
        }

        BigDecimal totalAmount = order.getTotalAmount().subtract(discount);
        System.out.println("Total Before Discount: $" + order.getTotalAmount());
        System.out.println("Discount: $" + discount);
        System.out.println("Total After Discount: $" + totalAmount);
        System.out.println("Cash Tendered: $" + cashTendered);
        System.out.println("Change: $" + cashTendered.subtract(totalAmount));
        System.out.println("---------------------------------");
    }
}