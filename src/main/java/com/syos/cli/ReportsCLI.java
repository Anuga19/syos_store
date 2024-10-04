package com.syos.cli;

import com.syos.service.ReportService;

import java.util.Scanner;

public class ReportsCLI {

    private final ReportService reportService;

    public ReportsCLI(ReportService reportService) {
        this.reportService = reportService;
    }

    public void startReportsCLI() {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("1. Generate Total Sales Report");
            System.out.println("2. Generate Reorder Level Report");
            System.out.println("3. Generate Stock Report");
            System.out.println("4. Generate Bill Report");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    reportService.generateReport("TotalSales");  // Pass report type as a string for total sales
                    break;
                case 2:
                    reportService.generateReport("ReorderLevel");  // Pass report type as a string for reorder level
                    break;
                case 3:
                    reportService.generateReport("StockReport");  // Pass report type as a string for stock report
                    break;
                case 4:
                    reportService.generateReport("BillReport");  // Pass report type as a string for bill report
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}