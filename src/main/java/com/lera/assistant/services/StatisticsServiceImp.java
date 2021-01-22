package com.lera.assistant.services;

import com.lera.assistant.model.statistics.AllClientsStat;
import com.lera.assistant.model.statistics.ClientStat;
import com.lera.assistant.model.statistics.YearlyStat;
import com.lera.assistant.model.statistics.chart.BarChart;
import com.lera.assistant.model.statistics.chart.BarChartDataset;
import com.lera.assistant.model.statistics.chart.PieChart;
import com.lera.assistant.model.statistics.chart.PieChartDataset;
import com.lera.assistant.repositories.ClientRepository;
import com.lera.assistant.repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StatisticsServiceImp implements StatisticsService {

    private JobRepository jobRepository;
    private ClientRepository clientRepository;

    public StatisticsServiceImp(JobRepository jobRepository, ClientRepository clientRepository) {
        this.jobRepository = jobRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public YearlyStat getYearlyBarChartStat(int year) {
        List<String> months = new ArrayList<>(Arrays.asList("January", "February", "March",
                "April", "May", "June", "July", "August",
                "September", "October", "November", "December"));

        YearlyStat yearlyStat = new YearlyStat();

        BigDecimal total = jobRepository.getTotalSalaryEver();
        if (total == null) {
            total = new BigDecimal(0);
        }
        yearlyStat.setTotal(total);

        BigDecimal yearlySalary = jobRepository.getYearlySalary(year);
        if (yearlySalary == null) {
            yearlySalary = new BigDecimal(0);
        }
        yearlyStat.setYearlySalary(yearlySalary);

        BigDecimal averageSalary = jobRepository.getAverageSalary(year);
        if (averageSalary == null) {
            averageSalary = new BigDecimal(0);
        }
        yearlyStat.setAverageSalary(averageSalary);

        BarChart barChart = new BarChart();
        barChart.setLabels(months);

        BarChartDataset barChartDataset = new BarChartDataset();
        barChartDataset.setLabel("Income in " + year);
        barChartDataset.setBackgroundColor("#42A5F5");
        barChartDataset.setBorderColor("#1E88E5");
        barChartDataset.setData(getYearlyStatData(months, year));

        barChart.setDatasets(Arrays.asList(barChartDataset));
        yearlyStat.setBarChart(barChart);
        return yearlyStat;
    }

    @Override
    public AllClientsStat getAllClientsPieChartStat() {
        List<String> colors = new ArrayList<>(Arrays.asList(
                "#E91E63", "#00BCD4", "#CDDC39", "#FFC107", "#3F51B5", "#8BC34A"
        ));

        List<ClientStat> clientStats = jobRepository.getAllClientsStat();
        if (clientStats.size() > 5) {
            BigDecimal others = new BigDecimal(0);
            for (int i = clientStats.size() - 1; i != 4; i--) {
                others = others.add(clientStats.get(i).getAmountPaid());
                clientStats.remove(i);
            }
            clientStats.add(new ClientStat("Others", others));
        }

        List<String> labels = new ArrayList<>();
        List<BigDecimal> data = new ArrayList<>();
        for (ClientStat clientStat : clientStats) {
            labels.add(clientStat.getName());
            data.add(clientStat.getAmountPaid());
        }

        PieChartDataset pieChartDataset = new PieChartDataset();

        pieChartDataset.setData(data);
        pieChartDataset.setBackgroundColor(colors);
        pieChartDataset.setHoverBackgroundColor(colors);

        PieChart pieChart = new PieChart();

        pieChart.setLabels(labels);
        pieChart.setDatasets(Arrays.asList(pieChartDataset));

        AllClientsStat allClientsStat = new AllClientsStat();
        allClientsStat.setTotal(jobRepository.getTotalSalaryEver());
        allClientsStat.setPieChart(pieChart);

        return allClientsStat;
    }

    @Override
    public YearlyStat getSingleClientBarChartStat(int year, long clientId) {
        List<String> months = new ArrayList<>(Arrays.asList("January", "February", "March",
                "April", "May", "June", "July", "August",
                "September", "October", "November", "December"));

        YearlyStat yearlyStat = new YearlyStat();

        BigDecimal total = jobRepository.getTotalEverByClient(clientId);
        if (total == null) {
            total = new BigDecimal(0);
        }
        yearlyStat.setTotal(total);

        BigDecimal yearlySalary = jobRepository.getYearlySalaryByClient(clientId, year);
        if (yearlySalary == null) {
            yearlySalary = new BigDecimal(0);
        }
        yearlyStat.setYearlySalary(yearlySalary);

        BigDecimal average = jobRepository.getAverageSalaryByClient(year, clientId);
        if (average == null) {
            average = new BigDecimal(0);
        }
        yearlyStat.setAverageSalary(average);

        BarChart barChart = new BarChart();
        barChart.setLabels(months);

        BarChartDataset barChartDataset = new BarChartDataset();
        barChartDataset.setLabel(clientRepository.getOne(clientId).getName() + " in " + year);
        barChartDataset.setBackgroundColor("#9CCC65");
        barChartDataset.setBorderColor("#7CB342");
        barChartDataset.setData(getClientYearlyStatData(months, year, clientId));

        barChart.setDatasets(Arrays.asList(barChartDataset));
        yearlyStat.setBarChart(barChart);
        return yearlyStat;
    }

    private List<BigDecimal> getYearlyStatData(List<String> months, int year) {
        List<BigDecimal> data = new ArrayList<>();
        for (String month : months) {
            BigDecimal monthlySalary = jobRepository.getMonthlySalary(month, year);
            if (monthlySalary == null) {
                monthlySalary = new BigDecimal(0);
            }
            data.add(monthlySalary);
        }
        return data;
    }

    private List<BigDecimal> getClientYearlyStatData(List<String> months, int year, long clientId) {
        List<BigDecimal> data = new ArrayList<>();
        for (String month : months) {
            BigDecimal monthlySalary = jobRepository.getMonthlySalaryByClient(month, year, clientId);
            if (monthlySalary == null) {
                monthlySalary = new BigDecimal(0);
            }
            data.add(monthlySalary);
        }
        return data;
    }
}
