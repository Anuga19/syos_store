//package com.syos.reports;
//
//import com.syos.dao.ReportDAO;
//import com.syos.interfaces.IReportGenerator;
//
//public class BillReportGenerator implements IReportGenerator {
//    private final ReportDAO reportDAO;
//
//    public BillReportGenerator(ReportDAO reportDAO) {
//        this.reportDAO = reportDAO;
//    }
//
//    @Override
//    public void generateReport() {
//        System.out.println("Generating Bill Report...");
//        try {
//            reportDAO.getBillReport();  // Assuming this method is in ReportDAO
//        } catch (Exception e) {
//            System.out.println("Error generating bill report: " + e.getMessage());
//        }
//    }
//}

package com.syos.reports;

import com.syos.dao.ReportDAO;
import com.syos.exceptions.ReportGenerationException;
import com.syos.interfaces.IReportGenerator;

public class BillReportGenerator implements IReportGenerator {
    private final ReportDAO reportDAO;

    public BillReportGenerator(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    @Override
    public void generateReport() {
        System.out.println("Generating Bill Report...");
        try {
            reportDAO.getBillReport();  // Assuming this method is in ReportDAO
        } catch (ReportGenerationException e) {
            System.out.println("Error generating bill report: " + e.getMessage());
        }
    }
}