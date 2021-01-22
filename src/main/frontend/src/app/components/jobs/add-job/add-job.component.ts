import {Component, OnInit} from '@angular/core';
import {Job} from "../../../shared/job";
import {Client} from "../../../shared/client";
import {ClientService} from "../../../services/client.service";
import {JobService} from "../../../services/job.service";
import {JobsComponent} from "../jobs.component";
import {Payment} from "../../../shared/payment";

@Component({
  selector: 'app-add-job',
  templateUrl: './add-job.component.html',
  styleUrls: ['./add-job.component.css']
})
export class AddJobComponent implements OnInit {
  job: Job = new Job();

  availableClients: Client[] = [];
  availableWorkers: Worker[] = [];

  displayDialog: boolean = false;
  error: boolean = false;

  constructor(private clientService: ClientService, private jobService: JobService) {
  }

  ngOnInit() {
    this.job.payment = new Payment();
    this.job.payment.fee = 10;
    this.job.client = new Client();

    this.clientService.getAllClients().subscribe(clients => {
      this.availableClients = clients;
    });
  }

  openDialog() {
    this.displayDialog = true;
    this.error = false;
  }

  closeDialog() {
    this.displayDialog = false;
    this.error = false;
  }

  onSubmit() {
    if (this.formIsValid()) {
      this.countPrice();
      this.jobService.addJob(this.job).subscribe(result => {
        this.emptyFormFields();
        this.refreshJobsList();
        this.closeDialog();
      });
    } else {
      this.error = true;
    }
  }

  private formIsValid() {
    return this.job.title != null && this.job.title != "" &&
      this.job.client.clientId != null && this.job.payment.priceWithoutFee != null;
  }

  private countPrice() {
    this.job.payment.price =
      this.job.payment.priceWithoutFee - this.job.payment.priceWithoutFee / 100 * this.job.payment.fee;
  }

  private emptyFormFields() {
    this.job = new Job();
    this.job.payment = new Payment();
    this.job.payment.fee = 10;
    this.job.client = new Client();
  }

  private refreshJobsList() {
    this.jobService.getAllJobs().subscribe(data => {
      JobsComponent.allJobs = data;
    });
  }

}
