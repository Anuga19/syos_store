//package com.syos.reports;
//
//import com.syos.dao.ReportDAO;
//import com.syos.interfaces.IReportGenerator;
//
//public class StockReportGenerator implements IReportGenerator {
//    private final ReportDAO reportDAO;
//
//    public StockReportGenerator(ReportDAO reportDAO) {
//        this.reportDAO = reportDAO;
//    }
//
//    @Override
//    public void generateReport() {
//        System.out.println("Generating Stock Report...");
//        try {
//            reportDAO.getStockReport();  // Assuming this method is in ReportDAO
//        } catch (Exception e) {
//            System.out.println("Error generating stock report: " + e.getMessage());
//        }
//    }
//}


package com.syos.reports;

import com.syos.dao.ReportDAO;
import com.syos.exceptions.ReportGenerationException;
import com.syos.interfaces.IReportGenerator;

public class StockReportGenerator implements IReportGenerator {
    private final ReportDAO reportDAO;

    public StockReportGenerator(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    @Override
    public void generateReport() {
        System.out.println("Generating Stock Report...");
        try {
            reportDAO.getStockReport();  // Assuming this method is in ReportDAO
        } catch (ReportGenerationException e) {
            System.out.println("Error generating stock report: " + e.getMessage());
        }
    }
}