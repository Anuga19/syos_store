package com.syos.service;

import com.syos.dao.OrderDAO;
import com.syos.dao.OrderItemsDAO;
import com.syos.model.Order;
import com.syos.model.Product;
import com.syos.model.OrderItem;
import java.sql.SQLException;

import java.math.BigDecimal;

public class OrderService {

    private final OrderDAO orderDAO;
    private final OrderItemsDAO orderItemsDAO;

    public OrderService(OrderDAO orderDAO, OrderItemsDAO orderItemsDAO) {
        this.orderDAO = orderDAO;
        this.orderItemsDAO = orderItemsDAO;  // Initialize OrderItemsDAO
    }

    public void addItemToOrder(Order order, Product product, int quantity) {
        order.addItem(product, quantity);
    }

    public void calculateTotal(Order order) {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : order.getOrderItems()) {
            total = total.add(item.getItemTotal());
        }
        order.setTotalAmount(total);
    }

    public void saveOrder(Order order) throws SQLException {
        orderDAO.saveOrder(order);  // Save order and generate order_id
        // Now save all order items to the order_items table
        orderItemsDAO.saveOrderItems(order.getOrderId(), order.getOrderItems());  // Pass order_id and list of order items
    }

//    public BigDecimal calculateDiscount(Order order, double discountPercentage) {
//        BigDecimal totalAmount = order.getTotalAmount();
//        BigDecimal discount = totalAmount.multiply(BigDecimal.valueOf(discountPercentage / 100));
//        return discount;
//    }

    public BigDecimal calculateDiscount(Order order, double discountPercentage) {
        BigDecimal totalAmount = order.getTotalAmount();
        return totalAmount.multiply(BigDecimal.valueOf(discountPercentage / 100));
    }


}