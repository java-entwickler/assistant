package com.lera.assistant.services;


import com.lera.assistant.model.statistics.AllClientsStat;
import com.lera.assistant.model.statistics.YearlyStat;

public interface StatisticsService {
    YearlyStat getYearlyBarChartStat(int year);
    YearlyStat getSingleClientBarChartStat(int year, long clientId);
    AllClientsStat getAllClientsPieChartStat();
}
