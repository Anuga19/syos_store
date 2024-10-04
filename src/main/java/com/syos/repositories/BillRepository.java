package com.syos.repositories;

import com.syos.interfaces.IBillDAO;
import com.syos.model.Bill;
import java.sql.SQLException;

public class BillRepository {

    private final IBillDAO billDAO;

    // Constructor to inject the DAO
    public BillRepository(IBillDAO billDAO) {
        this.billDAO = billDAO;
    }

    // Save a bill using the DAO
    public void saveBill(Bill bill) throws SQLException {
        billDAO.saveBill(bill);
    }
}
