package com.syos.utilities;

import com.syos.model.Order;
import com.syos.model.OrderItem;
import java.math.BigDecimal;
import java.util.List;

// OrderCalculator handles the business logic of calculating the total amount
public class OrderCalculator {

    // Modify the method to accept List<OrderItem> instead of Order
    public BigDecimal calculateTotal(List<OrderItem> orderItems) {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : orderItems) {
            total = total.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return total;
    }

    // Method to calculate discount
    public BigDecimal calculateDiscount(BigDecimal totalAmount, double discountPercentage) {
        return totalAmount.multiply(BigDecimal.valueOf(discountPercentage / 100));
    }
}