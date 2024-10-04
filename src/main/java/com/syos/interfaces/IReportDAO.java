package com.syos.interfaces;

import com.syos.exceptions.ReportGenerationException;

public interface IReportDAO {
    void getTotalSalesForDay() throws ReportGenerationException;
    void getReorderLevelReport() throws ReportGenerationException;
    void getStockReport() throws ReportGenerationException;
    void getBillReport() throws ReportGenerationException;
}