package com.syos.interfaces;

import com.syos.model.Bill;
import java.sql.SQLException;

public interface IBillDAO {
    void saveBill(Bill bill) throws SQLException;
}