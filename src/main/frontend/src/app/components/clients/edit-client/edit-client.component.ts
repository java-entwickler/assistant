import {Component} from '@angular/core';
import {Client} from "../../../shared/client";
import {ClientsComponent} from "../clients.component";
import {ClientService} from "../../../services/client.service";

@Component({
  selector: 'app-edit-client',
  templateUrl: './edit-client.component.html',
  styleUrls: ['./edit-client.component.css']
})
export class EditClientComponent {
  static selectedClient: Client;
  displayDialog: boolean = false;
  error: boolean = false;

  constructor(private clientService: ClientService) {
  }

  get selectedClient() {
    return EditClientComponent.selectedClient;
  }

  openDialog() {
    this.displayDialog = true;
  }

  closeDialog() {
    this.displayDialog = false;
    this.error = false;
  }

  onSubmit() {
    if (EditClientComponent.selectedClient.name == "" || EditClientComponent.selectedClient.name == null) {
      this.error = true;
    } else {
      this.clientService.editClient(EditClientComponent.selectedClient).subscribe(result => {
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
