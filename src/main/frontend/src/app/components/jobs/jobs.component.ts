import {Component, OnInit} from '@angular/core';
import {Job} from "../../shared/job";
import {JobService} from "../../services/job.service";
import {EditJobComponent} from "./edit-job/edit-job.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.css']
})
export class JobsComponent implements OnInit {

  static selectedJob: Job = null;
  static allJobs: Job[] = [];
  selectedRow: number;

  constructor(private jobService: JobService, private router: Router) {
  }

  ngOnInit() {
    this.jobService.getAllJobs().subscribe(data => {
      JobsComponent.allJobs = data;
    });
  }

  get allJobs() {
    return JobsComponent.allJobs;
  }

  selectJob(jobId: number, index: number) {
    if (index == this.selectedRow) {
      this.selectedRow = null;
      JobsComponent.selectedJob = null;
      EditJobComponent.job = null;
    } else {
      this.selectedRow = index;
      this.jobService.getJobById(jobId).subscribe(data => {
          JobsComponent.selectedJob = data;
          EditJobComponent.job = data;
        }
      );
    }
  }

  deleteJob() {
    if (JobsComponent.selectedJob != null) {
      this.jobService.deleteJob(JobsComponent.selectedJob.jobId).subscribe(result => {
        this.jobService.getAllJobs().subscribe(jobs => {
          JobsComponent.allJobs = jobs;
          JobsComponent.selectedJob = null;
          EditJobComponent.job = null;
          this.selectedRow = null;
        });
      });
    }
  }

  navigateToClientStat(clientId: number) {
    this.router.navigate(['/statistics/' + clientId]);
  }
}
