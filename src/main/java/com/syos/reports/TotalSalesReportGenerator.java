//package com.syos.reports;
//
//import com.syos.dao.ReportDAO;
//import com.syos.interfaces.IReportGenerator;
//
//import java.sql.SQLException;
//
//public class TotalSalesReportGenerator implements IReportGenerator {
//    private final ReportDAO reportDAO;
//
//    public TotalSalesReportGenerator(ReportDAO reportDAO) {
//        this.reportDAO = reportDAO;
//    }
//
//    @Override
//    public void generateReport() {
//        System.out.println("Generating Total Sales Report...");
//        try {
//            reportDAO.getTotalSalesForDay();  // Fetch data from DAO
//        } catch (SQLException e) {
//            System.out.println("Error generating total sales report: " + e.getMessage());
//        }
//    }
//}

package com.syos.reports;

import com.syos.dao.ReportDAO;
import com.syos.exceptions.ReportGenerationException;
import com.syos.interfaces.IReportGenerator;

public class TotalSalesReportGenerator implements IReportGenerator {
    private final ReportDAO reportDAO;

    public TotalSalesReportGenerator(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    @Override
    public void generateReport() {
        System.out.println("Generating Total Sales Report...");
        try {
            reportDAO.getTotalSalesForDay();  // Fetch data from DAO
        } catch (ReportGenerationException e) {
            System.out.println("Error generating total sales report: " + e.getMessage());
        }
    }
}