//package com.syos.service;
//
//import com.syos.dao.OrderDAO;
//import com.syos.dao.OrderItemsDAO;
//import com.syos.model.Order;
//import com.syos.model.Product;
//import com.syos.model.OrderItem;
//import java.sql.SQLException;
//
//import java.math.BigDecimal;
//
//public class OrderService {
//
//    private final OrderDAO orderDAO;
//    private final OrderItemsDAO orderItemsDAO;
//
//    public OrderService(OrderDAO orderDAO, OrderItemsDAO orderItemsDAO) {
//        this.orderDAO = orderDAO;
//        this.orderItemsDAO = orderItemsDAO;  // Initialize OrderItemsDAO
//    }
//
//    public void addItemToOrder(Order order, Product product, int quantity) {
//        order.addItem(product, quantity);
//    }
//
//    public void calculateTotal(Order order) {
//        BigDecimal total = BigDecimal.ZERO;
//        for (OrderItem item : order.getOrderItems()) {
//            total = total.add(item.getItemTotal());
//        }
//        order.setTotalAmount(total);
//    }
//
//    public void saveOrder(Order order) throws SQLException {
//        orderDAO.saveOrder(order);  // Save order and generate order_id
//        // Now save all order items to the order_items table
//        orderItemsDAO.saveOrderItems(order.getOrderId(), order.getOrderItems());  // Pass order_id and list of order items
//    }
//
////    public BigDecimal calculateDiscount(Order order, double discountPercentage) {
////        BigDecimal totalAmount = order.getTotalAmount();
////        BigDecimal discount = totalAmount.multiply(BigDecimal.valueOf(discountPercentage / 100));
////        return discount;
////    }
//
//    public BigDecimal calculateDiscount(Order order, double discountPercentage) {
//        BigDecimal totalAmount = order.getTotalAmount();
//        return totalAmount.multiply(BigDecimal.valueOf(discountPercentage / 100));
//    }
//
//
//}

//package com.syos.service;
//
//import com.syos.interfaces.IOrderDAO;
//import com.syos.dao.OrderItemsDAO;
//import com.syos.model.Order;
//import com.syos.model.Product;
//import com.syos.model.OrderItem;
//
//import java.sql.SQLException;
//import java.math.BigDecimal;
//
//public class OrderService {
//
//    private final IOrderDAO orderDAO;  // Depend on IOrderDAO interface
//    private final OrderItemsDAO orderItemsDAO;
//
//    public OrderService(IOrderDAO orderDAO, OrderItemsDAO orderItemsDAO) {
//        this.orderDAO = orderDAO;
//        this.orderItemsDAO = orderItemsDAO;  // Initialize OrderItemsDAO
//    }
//
//    public void addItemToOrder(Order order, Product product, int quantity) {
//        order.addItem(product, quantity);
//    }
//
//    public void calculateTotal(Order order) {
//        BigDecimal total = BigDecimal.ZERO;
//        for (OrderItem item : order.getOrderItems()) {
//            total = total.add(item.getItemTotal());
//        }
//        order.setTotalAmount(total);
//    }
//
//    public void saveOrder(Order order) throws SQLException {
//        orderDAO.saveOrder(order);  // Save order and generate order_id
//        // Now save all order items to the order_items table
//        orderItemsDAO.saveOrderItems(order.getOrderId(), order.getOrderItems());  // Pass order_id and list of order items
//    }
//
//    public BigDecimal calculateDiscount(Order order, double discountPercentage) {
//        BigDecimal totalAmount = order.getTotalAmount();
//        return totalAmount.multiply(BigDecimal.valueOf(discountPercentage / 100));
//    }
//}


package com.syos.service;

import com.syos.interfaces.IOrderDAO;
import com.syos.interfaces.IOrderItemsDAO;
import com.syos.model.Order;
import com.syos.model.Product;
import com.syos.model.OrderItem;
import com.syos.utilities.OrderCalculator;

import java.sql.SQLException;
import java.math.BigDecimal;

public class OrderService {

    private final IOrderDAO orderDAO;  // Depend on IOrderDAO interface
    private final IOrderItemsDAO orderItemsDAO;  // Depend on IOrderItemsDAO interface
    private final OrderCalculator orderCalculator;  // Inject OrderCalculator for total calculation

    public OrderService(IOrderDAO orderDAO, IOrderItemsDAO orderItemsDAO, OrderCalculator orderCalculator) {
        this.orderDAO = orderDAO;
        this.orderItemsDAO = orderItemsDAO;
        this.orderCalculator = orderCalculator;  // Initialize OrderCalculator
    }

    // Add item to order
    public void addItemToOrder(Order order, Product product, int quantity) {
        order.addItem(product, quantity);
    }

    // Calculate total using OrderCalculator
    public void calculateTotal(Order order) {
        BigDecimal total = orderCalculator.calculateTotal(order.getOrderItems());
        order.setTotalAmount(total);  // Set calculated total
    }

    // Save order and items
    public void saveOrder(Order order) throws SQLException {
        orderDAO.saveOrder(order);  // Save order
        orderItemsDAO.saveOrderItems(order.getOrderId(), order.getOrderItems());  // Save order items
    }

    // Calculate discount using OrderCalculator
    public BigDecimal calculateDiscount(Order order, double discountPercentage) {
        return orderCalculator.calculateDiscount(order.getTotalAmount(), discountPercentage);  // Delegate discount calculation
    }
}