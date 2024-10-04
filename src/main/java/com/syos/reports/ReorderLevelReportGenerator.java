//package com.syos.reports;
//
//import com.syos.dao.ReportDAO;
//import com.syos.interfaces.IReportGenerator;
//
//import java.sql.SQLException;
//
//public class ReorderLevelReportGenerator implements IReportGenerator {
//    private final ReportDAO reportDAO;
//
//    public ReorderLevelReportGenerator(ReportDAO reportDAO) {
//        this.reportDAO = reportDAO;
//    }
//
//    @Override
//    public void generateReport() {
//        System.out.println("Generating Reorder Level Report...");
//        try {
//            reportDAO.getReorderLevelReport();  // Fetch data from DAO
//        } catch (SQLException e) {
//            System.out.println("Error generating reorder level report: " + e.getMessage());
//        }
//    }
//}

package com.syos.reports;

import com.syos.dao.ReportDAO;
import com.syos.exceptions.ReportGenerationException;
import com.syos.interfaces.IReportGenerator;

public class ReorderLevelReportGenerator implements IReportGenerator {
    private final ReportDAO reportDAO;

    public ReorderLevelReportGenerator(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    @Override
    public void generateReport() {
        System.out.println("Generating Reorder Level Report...");
        try {
            reportDAO.getReorderLevelReport();  // Fetch data from DAO
        } catch (ReportGenerationException e) {
            System.out.println("Error generating reorder level report: " + e.getMessage());
        }
    }
}