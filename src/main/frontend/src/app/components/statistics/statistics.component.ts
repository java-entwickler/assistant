import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Client} from "../../shared/client";
import {YearlyStat} from "../../shared/yearly-stat";
import {PieChart} from "../../shared/pie-chart";
import {ClientService} from "../../services/client.service";
import {StatisticsService} from "../../services/statistics.service";

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {
  yearlyStat: YearlyStat = new YearlyStat();
  yearlyStatType: YearlyStatType = YearlyStatType.allClients;

  clients: Client[];
  clientId: number;
  year: number;

  clientStat: PieChart;

  constructor(private clientService: ClientService, private statisticsService: StatisticsService,
              private route: ActivatedRoute) {
    this.year = new Date().getFullYear();
    this.clientService.getAllClients().subscribe(clients => this.clients = clients);
  }

  ngOnInit() {
    if (this.route.snapshot.paramMap.get('id') != null) {
      this.clientId = +this.route.snapshot.paramMap.get('id');
      this.yearlyStatType=YearlyStatType.singleClient;
      this.refreshYearlyStatSingleClient();
    } else {
      this.refreshYearlyStatAllClients();
    }

    this.statisticsService.getAllClientsPieChartStat().subscribe(result => {
      this.clientStat = result.pieChart;
    });
  }

  refreshYearlyStatAllClients() {
    this.statisticsService.getYearlyStatisticsAllClients(this.year)
      .subscribe(result => {
        this.yearlyStat = result;
      });
  }

  refreshYearlyStatSingleClient() {
    this.statisticsService.getYearlyStatisticsSingleClient(this.year, this.clientId)
      .subscribe(result => {
        this.yearlyStat = result;
      });
  }

  changeYear() {
    if (this.yearlyStatType == YearlyStatType.allClients) {
      this.refreshYearlyStatAllClients();
    } else {
      this.refreshYearlyStatSingleClient();
    }
  }

}
