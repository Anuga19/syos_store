package com.syos.interfaces;

import com.syos.model.Payment;
import java.sql.SQLException;

public interface IPaymentDAO {
    void savePayment(Payment payment) throws SQLException;
}