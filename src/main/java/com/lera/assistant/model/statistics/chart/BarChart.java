package com.lera.assistant.model.statistics.chart;

import lombok.Data;

import java.util.List;

@Data
public class BarChart {
    private List<String> labels;
    private List<BarChartDataset> datasets;
}
