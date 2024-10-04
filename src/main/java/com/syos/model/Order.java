//package com.syos.model;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Date;
//
//public class Order {
//    private int orderId;
//    private int customerId;  // Added customerId to link an order to a customer
//    private String orderStatus;  // Added orderStatus to track order progress
//    private List<OrderItem> orderItems = new ArrayList<>();
//    private BigDecimal totalAmount;
//    private Date orderDate;  // To store the order date
//
//    // Default constructor
//    public Order() {
//        this.totalAmount = BigDecimal.ZERO;  // Initialize totalAmount to 0
//    }
//
//    // Constructor for creating a new order
//    public Order(int customerId, String orderStatus) {
//        this.customerId = customerId;
//        this.orderStatus = orderStatus;
//        this.totalAmount = BigDecimal.ZERO;
//    }
//
//    // Constructor for retrieving an existing order from the database
//    public Order(int orderId, int customerId, String orderStatus, BigDecimal totalAmount, Date orderDate) {
//        this.orderId = orderId;
//        this.customerId = customerId;
//        this.orderStatus = orderStatus;
//        this.totalAmount = totalAmount;
//        this.orderDate = orderDate;
//    }
//
//    // Getters and Setters
//    public int getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(int orderId) {
//        this.orderId = orderId;
//    }
//
//    public int getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(int customerId) {
//        this.customerId = customerId;
//    }
//
//    public String getOrderStatus() {
//        return orderStatus;
//    }
//
//    public void setOrderStatus(String orderStatus) {
//        this.orderStatus = orderStatus;
//    }
//
//    public BigDecimal getTotalAmount() {
//        return totalAmount;
//    }
//
//    public void setTotalAmount(BigDecimal totalAmount) {
//        this.totalAmount = totalAmount;
//    }
//
//    public List<OrderItem> getOrderItems() {
//        return orderItems;
//    }
//
//    public void setOrderItems(List<OrderItem> orderItems) {
//        this.orderItems = orderItems;
//    }
//
//    public Date getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(Date orderDate) {
//        this.orderDate = orderDate;
//    }
//
//    // Method to add items to the order
//    public void addItem(Product product, int quantity) {
//        OrderItem item = new OrderItem(product, quantity);
//        orderItems.add(item);
//        calculateTotal();  // Update total amount whenever a new item is added
//    }
//
//    // Method to calculate the total amount
//    public void calculateTotal() {
//        totalAmount = BigDecimal.ZERO;
//        for (OrderItem item : orderItems) {
//            totalAmount = totalAmount.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
//        }
//    }
//}
//-------------------------------------------

//package com.syos.model;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class Order {
//    private Integer orderId;  // Changed to Integer to handle null values
//    private Integer customerId;  // Changed to Integer to handle null for in-store customers
//    private String orderStatus;
//    private List<OrderItem> orderItems;
//    private BigDecimal totalAmount;
//    private Date orderDate;
//
//    // Default constructor
//    public Order() {
//        this.orderItems = new ArrayList<>();  // Initialize the orderItems list
//        this.totalAmount = BigDecimal.ZERO;  // Initialize totalAmount to 0
//        this.orderDate = new Date();  // Initialize orderDate to current date
//    }
//
//    // Constructor for creating a new order
//    public Order(Integer customerId, String orderStatus) {
//        this();  // Call the default constructor to initialize the fields
//        this.customerId = customerId;
//        this.orderStatus = orderStatus;
//    }
//
//    // Constructor for retrieving an existing order from the database
//    public Order(Integer orderId, Integer customerId, String orderStatus, BigDecimal totalAmount, Date orderDate) {
//        this.orderId = orderId;
//        this.customerId = customerId;
//        this.orderStatus = orderStatus;
//        this.totalAmount = totalAmount;
//        this.orderDate = new Date(orderDate.getTime());  // Defensive copy
//        this.orderItems = new ArrayList<>();
//    }
//
//    // Getters and Setters
//    public Integer getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(Integer orderId) {
//        this.orderId = orderId;
//    }
//
//    public Integer getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(Integer customerId) {
//        this.customerId = customerId;
//    }
//
//    public String getOrderStatus() {
//        return orderStatus;
//    }
//
//    public void setOrderStatus(String orderStatus) {
//        this.orderStatus = orderStatus;
//    }
//
//    public BigDecimal getTotalAmount() {
//        return totalAmount;
//    }
//
//    public void setTotalAmount(BigDecimal totalAmount) {
//        this.totalAmount = totalAmount;
//    }
//
//    public List<OrderItem> getOrderItems() {
//        return orderItems;
//    }
//
//    public void setOrderItems(List<OrderItem> orderItems) {
//        this.orderItems = orderItems;
//        calculateTotal();  // Recalculate total amount whenever items are set
//    }
//
//    public Date getOrderDate() {
//        return new Date(orderDate.getTime());  // Defensive copy to avoid external modification
//    }
//
//    public void setOrderDate(Date orderDate) {
//        this.orderDate = new Date(orderDate.getTime());  // Defensive copy
//    }
//
//    // Method to add items to the order
//    public void addItem(Product product, int quantity) {
//        OrderItem item = new OrderItem(product, quantity);
//        orderItems.add(item);
//        calculateTotal();  // Update total amount whenever a new item is added
//    }
//
//    // Method to calculate the total amount
//    public void calculateTotal() {
//        totalAmount = BigDecimal.ZERO;  // Reset total amount
//        for (OrderItem item : orderItems) {
//            totalAmount = totalAmount.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
//        }
//    }
//}

package com.syos.model;

import com.syos.utilities.OrderCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private Integer orderId;
    private Integer customerId;
    private String orderStatus;
    private List<OrderItem> orderItems;
    private BigDecimal totalAmount;
    private Date orderDate;

    // Default constructor
    public Order() {
        this.orderItems = new ArrayList<>();  // Initialize the orderItems list
        this.totalAmount = BigDecimal.ZERO;  // Initialize totalAmount to 0
        this.orderDate = new Date();  // Initialize orderDate to current date
    }

    // Constructor for creating a new order
    public Order(Integer customerId, String orderStatus) {
        this();  // Call the default constructor to initialize the fields
        this.customerId = customerId;
        this.orderStatus = orderStatus;
    }

    // Constructor for retrieving an existing order from the database
    public Order(Integer orderId, Integer customerId, String orderStatus, BigDecimal totalAmount, Date orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.orderDate = new Date(orderDate.getTime());  // Defensive copy
        this.orderItems = new ArrayList<>();
    }

    // Getters and Setters
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
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

    // Removed the call to `calculateTotal` in this setter to adhere to SRP
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    // Removed the call to `calculateTotal` in this setter to adhere to SRP
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getOrderDate() {
        return new Date(orderDate.getTime());  // Defensive copy to avoid external modification
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = new Date(orderDate.getTime());  // Defensive copy
    }

    // Method to add items to the order
    public void addItem(Product product, int quantity) {
        OrderItem item = new OrderItem(product, quantity);
        orderItems.add(item);
    }

    // Delegate the calculation to OrderCalculator
    public void calculateTotal(OrderCalculator orderCalculator) {
        this.totalAmount = orderCalculator.calculateTotal(orderItems);
    }
}