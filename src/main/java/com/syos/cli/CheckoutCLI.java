package com.syos.cli;

import com.syos.model.OrderItem;
import com.syos.model.Product;
import com.syos.model.Order;
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

                boolean isAvailable = productService.checkStockAvailability(product, quantity);
                if (isAvailable) {
                    orderService.addItemToOrder(order, product, quantity);
                    System.out.println(quantity + " " + product.getProductName() + " added to order.");
                } else {
                    System.out.println("Insufficient stock for " + product.getProductName());
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

        System.out.print("Enter Cash Tendered: ");
        BigDecimal cashTendered = scanner.nextBigDecimal();

        // Process payment and generate bill
//        paymentService.processCashPayment(order, cashTendered);
//        stockService.updateStockAfterSale(order);
//        try {
//            billService.generateBill(order, cashTendered, discount);
//        } catch (SQLException e) {
//            System.out.println("Error generating bill: " + e.getMessage());
//            e.printStackTrace();
//        }
//
//        System.out.println("Checkout complete. Bill generated.");

        try {
            orderService.saveOrder(order);  // Ensure order is saved before bill generation
        } catch (SQLException e) {
            System.out.println("Error saving order: " + e.getMessage());
        }

        paymentService.processCashPayment(order, cashTendered);
        stockService.updateStockAfterSale(order);
        try {
            billService.generateBill(order, cashTendered, discount);
            printBill(order, discount, cashTendered);
        } catch (SQLException e) {
            System.out.println("Error generating bill: " + e.getMessage());
        }

        System.out.println("Checkout complete. Bill generated.");
    }

    public void printBill(Order order, BigDecimal discount, BigDecimal cashTendered) {
        System.out.println("-------- SYOS STORE BILL --------");
        System.out.println("Bill Serial Number: " + BillService.billSerialNumber);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Bill Date: " + formatter.format(new Date()));
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