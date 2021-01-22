package com.lera.assistant.model.statistics;

import com.lera.assistant.model.statistics.chart.BarChart;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class YearlyStat {
    private BarChart barChart;
    private BigDecimal total;
    private BigDecimal yearlySalary;
    private BigDecimal averageSalary;
}
