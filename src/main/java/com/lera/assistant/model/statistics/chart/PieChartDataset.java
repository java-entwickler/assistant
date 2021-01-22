package com.lera.assistant.model.statistics.chart;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PieChartDataset {
    private List<BigDecimal> data;
    private List<String> backgroundColor;
    private List<String> hoverBackgroundColor;
}
