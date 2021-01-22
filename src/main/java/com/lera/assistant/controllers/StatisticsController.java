package com.lera.assistant.controllers;

import com.lera.assistant.model.statistics.AllClientsStat;
import com.lera.assistant.model.statistics.YearlyStat;
import com.lera.assistant.services.StatisticsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/statistics")
public class StatisticsController {
    private StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/yearly/{year}")
    public YearlyStat yearlyStat(@PathVariable int year) {
        return statisticsService.getYearlyBarChartStat(year);
    }

    @GetMapping("/yearly/{year}/client/{id}")
    public YearlyStat singleClientStat(@PathVariable int year, @PathVariable long id) {
        return statisticsService.getSingleClientBarChartStat(year, id);
    }

    @GetMapping("/clients")
    public AllClientsStat allClientsStat() {
        return statisticsService.getAllClientsPieChartStat();
    }

}
