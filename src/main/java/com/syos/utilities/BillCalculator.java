package com.syos.utilities;

import java.math.BigDecimal;

public class BillCalculator {

    // Calculate the total amount after applying the discount
    public BigDecimal calculateTotalAmount(BigDecimal totalAmount, BigDecimal discount) {
        if (totalAmount == null || discount == null) {
            throw new IllegalArgumentException("Total amount and discount must not be null");
        }

        return totalAmount.subtract(discount);  // Subtract the discount from the total amount
    }

    // Calculate the change amount to be returned to the customer
    public BigDecimal calculateChangeAmount(BigDecimal cashTendered, BigDecimal totalAmount) {
        if (cashTendered == null || totalAmount == null) {
            throw new IllegalArgumentException("Cash tendered and total amount must not be null");
        }

        return cashTendered.subtract(totalAmount);  // Subtract the total amount from cash tendered to get the change
    }

    // Additional helper method to calculate the discount based on percentage
    public BigDecimal calculateDiscount(BigDecimal totalAmount, double discountPercentage) {
        if (totalAmount == null || discountPercentage < 0) {
            throw new IllegalArgumentException("Total amount must not be null and discount percentage must be non-negative");
        }

        return totalAmount.multiply(BigDecimal.valueOf(discountPercentage / 100));
    }
}