package com.syos.service;

import com.syos.dao.OrderDAO;
import com.syos.model.Order;
import com.syos.model.Product;
import com.syos.model.OrderItem;
import java.sql.SQLException;

import java.math.BigDecimal;

public class OrderService {

    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
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