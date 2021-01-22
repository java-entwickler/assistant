package com.lera.assistant.model.statistics.chart;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BarChartDataset {
    private String label;
    private String backgroundColor;
    private String borderColor;
    private List<BigDecimal> data;
}
