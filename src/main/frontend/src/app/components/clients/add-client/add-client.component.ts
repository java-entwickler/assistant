import {Component} from '@angular/core';
import {Client} from "../../../shared/client";
import {ClientService} from "../../../services/client.service";
import {ClientsComponent} from "../clients.component";

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent {

  client: Client = new Client();
  displayDialog: boolean = false;
  error: boolean = false;

  constructor(private clientService: ClientService) {
  }

  openDialog() {
    this.displayDialog = true;
    this.error=false;
  }

  closeDialog() {
    this.displayDialog = false;
    this.client = new Client();
    this.error=false;
  }

  onSubmit() {
    if (this.client.name == "" || this.client.name == null) {
      this.error = true;
    } else {
      this.clientService.addClient(this.client).subscribe(result => {
        this.refreshClientsList();
      });
      this.closeDialog();
    }
  }

  private refreshClientsList() {
    this.clientService.getAllClients().subscribe(clients => {
      ClientsComponent.allClients = clients;
    });
  }

}
