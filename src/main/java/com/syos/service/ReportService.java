//package com.syos.service;
//
//import com.syos.interfaces.IReportGenerator;
//
//import java.util.Map;
//
//public class ReportService {
//
//    private final Map<String, IReportGenerator> reportGenerators;
//
//    // Constructor to inject a map of report generators
//    public ReportService(Map<String, IReportGenerator> reportGenerators) {
//        this.reportGenerators = reportGenerators;
//    }
//
//    // Method to generate a specific report by type
//    public void generateReport(String reportType) {
//        IReportGenerator reportGenerator = reportGenerators.get(reportType);
//        if (reportGenerator != null) {
//            reportGenerator.generateReport();
//        } else {
//            System.out.println("Invalid report type: " + reportType);
//        }
//    }
//
//    // Method to generate all reports
//    public void generateAllReports() {
//        for (IReportGenerator reportGenerator : reportGenerators.values()) {
//            reportGenerator.generateReport();
//        }
//    }
//
//    // Method to list available report types
//    public void listAvailableReports() {
//        System.out.println("Available Reports:");
//        for (String reportType : reportGenerators.keySet()) {
//            System.out.println("- " + reportType);
//        }
//    }
//}

// --------------------------------------------

package com.syos.service;

import com.syos.exceptions.InvalidReportTypeException;
import com.syos.interfaces.IReportGenerator;

import java.util.Map;

public class ReportService {

    private final Map<String, IReportGenerator> reportGenerators;

    // Constructor to inject a map of report generators
    public ReportService(Map<String, IReportGenerator> reportGenerators) {
        this.reportGenerators = reportGenerators;
    }

    // Method to generate a specific report by type
    public void generateReport(String reportType) throws InvalidReportTypeException {
        IReportGenerator reportGenerator = reportGenerators.get(reportType);
        if (reportGenerator != null) {
            try {
                reportGenerator.generateReport();  // Handle potential errors here
            } catch (Exception e) {
                throw new InvalidReportTypeException("Error generating report: " + e.getMessage());
            }
        } else {
            throw new InvalidReportTypeException("Invalid report type: " + reportType);
        }
    }

    // Method to generate all reports
    public void generateAllReports() {
        for (IReportGenerator reportGenerator : reportGenerators.values()) {
            try {
                reportGenerator.generateReport();
            } catch (Exception e) {
                System.out.println("Error generating report: " + e.getMessage());
            }
        }
    }

    // Method to list available report types
    public void listAvailableReports() {
        System.out.println("Available Reports:");
        for (String reportType : reportGenerators.keySet()) {
            System.out.println("- " + reportType);
        }
    }
}