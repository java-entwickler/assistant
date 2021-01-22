import {Component, OnInit} from '@angular/core';
import {Job} from "../../../shared/job";
import {Client} from "../../../shared/client";
import {ClientService} from "../../../services/client.service";
import {JobService} from "../../../services/job.service";
import {JobsComponent} from "../jobs.component";

@Component({
  selector: 'app-edit-job',
  templateUrl: './edit-job.component.html',
  styleUrls: ['./edit-job.component.css']
})
export class EditJobComponent implements OnInit {
  static job: Job;
  availableClients: Client[] = [];
  availableWorkers: Worker[] = [];
  displayDialog: boolean = false;

  constructor(private clientService: ClientService, private jobService: JobService ) { }

  ngOnInit() {
    this.clientService.getAllClients().subscribe(clients => {
      this.availableClients = clients;
    });
  }

  get job() {
    return EditJobComponent.job;
  }

  openDialog() {
    this.displayDialog = true;
  }

  closeDialog() {
    this.displayDialog = false;
  }

  onSubmit() {
    this.countPrice();
    this.jobService.editJob(EditJobComponent.job).subscribe(result => {
      this.jobService.getAllJobs().subscribe(data => {
        JobsComponent.allJobs = data;
        this.displayDialog = false;
      });
    });
  }

  countPrice()  {
    this.job.payment.price =
      this.job.payment.priceWithoutFee - this.job.payment.priceWithoutFee/100*this.job.payment.fee;
  }

}
