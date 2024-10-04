//package com.syos.service;
//
//import com.syos.dao.PaymentDAO;
//import com.syos.model.Bill;
//import com.syos.model.Order;
//import com.syos.model.Payment;
//
//import java.math.BigDecimal;
//import java.sql.SQLException;
//
//public class PaymentService {
//
//    private final PaymentDAO paymentDAO;
//
//    public PaymentService(PaymentDAO paymentDAO) {
//        this.paymentDAO = paymentDAO;
//    }
//
//    public void processCashPayment(Bill bill, BigDecimal cashTendered) throws SQLException {
//        BigDecimal totalAmount = bill.getTotalAmount();
//        if (cashTendered.compareTo(totalAmount) >= 0) {
//            BigDecimal change = cashTendered.subtract(totalAmount);
//            System.out.println("Change to return: " + change);
//
//            // Save payment information to the payments table
//            Payment payment = new Payment(
//                    bill.getBillId(),        // Linking the payment to the generated bill ID
//                    "CASH",                 // Payment method (assuming cash)
//                    cashTendered            // Amount paid (cash tendered by the customer)
//            );
//
//            paymentDAO.savePayment(payment);  // Save payment to the database
//
//        } else {
//            System.out.println("Insufficient cash provided.");
//        }
//    }
//}

//------------------//

//package com.syos.service;
//
//import com.syos.interfaces.IPaymentDAO;
//import com.syos.model.Bill;
//import com.syos.model.Payment;
//
//import java.math.BigDecimal;
//import java.sql.SQLException;
//
//public class PaymentService {
//
//    private final IPaymentDAO paymentDAO;  // Depend on the IPaymentDAO interface, adhering to DIP
//
//    public PaymentService(IPaymentDAO paymentDAO) {
//        this.paymentDAO = paymentDAO;
//    }
//
//    // Process cash payment and save the payment details
//    public void processCashPayment(Bill bill, BigDecimal cashTendered) throws SQLException {
//        BigDecimal totalAmount = bill.getTotalAmount();
//
//        if (cashTendered.compareTo(totalAmount) >= 0) {
//            BigDecimal change = cashTendered.subtract(totalAmount);
//            System.out.println("Change to return: " + change);
//
//            // Create a Payment object
//            Payment payment = new Payment(
//                    bill.getBillId(),         // Linking the payment to the bill ID
//                    "CASH",                  // Payment method
//                    totalAmount              // Amount paid (use the total bill amount)
//            );
//
//            // Save payment using the DAO
//            paymentDAO.savePayment(payment);
//        } else {
//            System.out.println("Insufficient cash provided.");
//        }
//    }
//}


package com.syos.service;

import com.syos.interfaces.IPaymentDAO;
import com.syos.model.Bill;
import com.syos.model.Payment;
import com.syos.model.Payment.PaymentMethod;  // Import the PaymentMethod enum

import java.math.BigDecimal;
import java.sql.SQLException;

public class PaymentService {

    private final IPaymentDAO paymentDAO;  // Depend on the IPaymentDAO interface, adhering to DIP

    public PaymentService(IPaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    // Process cash payment and save the payment details
    public void processCashPayment(Bill bill, BigDecimal cashTendered) throws SQLException {
        BigDecimal totalAmount = bill.getTotalAmount();

        if (cashTendered.compareTo(totalAmount) >= 0) {
            BigDecimal change = cashTendered.subtract(totalAmount);
            System.out.println("Change to return: " + change);

            // Create a Payment object using the PaymentMethod enum
            Payment payment = new Payment(
                    bill.getBillId(),         // Linking the payment to the bill ID
                    PaymentMethod.CASH,       // Payment method (now an enum)
                    totalAmount              // Amount paid (use the total bill amount)
            );

            // Save payment using the DAO
            paymentDAO.savePayment(payment);
        } else {
            System.out.println("Insufficient cash provided.");
        }
    }
}