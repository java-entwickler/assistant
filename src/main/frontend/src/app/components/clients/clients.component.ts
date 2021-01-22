import {Component, OnInit} from '@angular/core';
import {Client} from "../../shared/client";
import {ClientService} from "../../services/client.service";
import {EditClientComponent} from "./edit-client/edit-client.component";

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {
  static allClients: Client[] = [];
  static selectedClient: Client;
  selectedRow: number;

  constructor(private clientService: ClientService) {
  }

  ngOnInit() {
    this.refreshClientsList();
  }

  get allClients() {
    return ClientsComponent.allClients;
  }

  selectClient(clientId: number, index: number) {
    if (index == this.selectedRow) {
      this.setSelectedClientToNull();
    } else {
      this.selectedRow = index;
      this.clientService.getClientById(clientId).subscribe(data => {
          ClientsComponent.selectedClient = data;
          EditClientComponent.selectedClient = data;
        }
      );
    }
  }

  deleteClient() {
    if (ClientsComponent.selectedClient != null) {
      this.clientService.deleteClient(ClientsComponent.selectedClient.clientId).subscribe(result => {
        this.refreshClientsList();
        this.setSelectedClientToNull();
      });
    }
  }

  private refreshClientsList() {
    this.clientService.getAllClients().subscribe(clients => {
      ClientsComponent.allClients = clients;
    });
  }

  private setSelectedClientToNull() {
    this.selectedRow = null;
    ClientsComponent.selectedClient = null;
    EditClientComponent.selectedClient = null;
  }
}
