import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Job} from "../shared/job";

@Injectable({
  providedIn: 'root'
})
export class JobService {

  url = 'http://localhost:8181/api/jobs';

  constructor(private http: HttpClient) {
  }

  getAllJobs(): Observable<Job[]> {
    return this.http.get<Job[]>(this.url);
  }

  getJobById(jobId: number): Observable<Job> {
    return this.http.get<Job>(this.url + "/" + jobId);
  }

  addJob(job: Job): Observable<Job> {
    return this.http.post<Job>(this.url, job);
  }

  deleteJob(jobId: number): Observable<any> {
    return this.http.delete(this.url + "/" + jobId);
  }

  editJob(job: Job) {
    return this.http.put(this.url, job);
  }
}
