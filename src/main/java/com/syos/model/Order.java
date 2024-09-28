package com.syos.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Order {
    private int orderId;
    private int customerId;  // Added customerId to link an order to a customer
    private String orderStatus;  // Added orderStatus to track order progress
    private List<OrderItem> orderItems = new ArrayList<>();
    private BigDecimal totalAmount;
    private Date orderDate;  // To store the order date

    // Default constructor
    public Order() {
        this.totalAmount = BigDecimal.ZERO;  // Initialize totalAmount to 0
    }

    // Constructor for creating a new order
    public Order(int customerId, String orderStatus) {
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.totalAmount = BigDecimal.ZERO;
    }

    // Constructor for retrieving an existing order from the database
    public Order(int orderId, int customerId, String orderStatus, BigDecimal totalAmount, Date orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    // Method to add items to the order
    public void addItem(Product product, int quantity) {
        OrderItem item = new OrderItem(product, quantity);
        orderItems.add(item);
        calculateTotal();  // Update total amount whenever a new item is added
    }

    // Method to calculate the total amount
    public void calculateTotal() {
        totalAmount = BigDecimal.ZERO;
        for (OrderItem item : orderItems) {
            totalAmount = totalAmount.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
    }
}