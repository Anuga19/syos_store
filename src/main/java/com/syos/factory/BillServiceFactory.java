package com.syos.factory;

import com.syos.dao.BillDAO;
import com.syos.interfaces.IBillDAO;
import com.syos.service.BillService;
import com.syos.utilities.BillCalculator;

import java.sql.Connection;

public class BillServiceFactory {

    // This method will create and return an instance of BillService
    public static BillService createBillService(Connection connection) {
        IBillDAO billDAO = new BillDAO(connection);  // Create BillDAO
        BillCalculator billCalculator = new BillCalculator();  // Create BillCalculator

        // Return an instance of BillService with the correct dependencies
        return new BillService(billDAO, billCalculator);
    }
}
