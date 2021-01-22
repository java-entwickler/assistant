import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {YearlyStat} from "../shared/yearly-stat";
import {AllClientsStat} from "../shared/all-clients-stat";

@Injectable({
  providedIn: 'root'
})
export class StatisticsService {
  private url = 'http://localhost:8181/api/statistics';

  constructor(private http: HttpClient) {
  }

  public getYearlyStatisticsAllClients(year: number): Observable<YearlyStat> {
    return this.http.get<YearlyStat>(this.url + '/yearly/' + year);
  }

  public getYearlyStatisticsSingleClient(year: number, clientId: number): Observable<YearlyStat> {
    return this.http.get<YearlyStat>(this.url + '/yearly/' + year + '/client/' + clientId);
  }

  public getAllClientsPieChartStat(): Observable<AllClientsStat> {
    return this.http.get<AllClientsStat>(this.url + '/clients');
  }
}
