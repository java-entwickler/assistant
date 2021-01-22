package com.lera.assistant.model.statistics;

import com.lera.assistant.model.statistics.chart.PieChart;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AllClientsStat {
    private PieChart pieChart;
    private BigDecimal total;
}
