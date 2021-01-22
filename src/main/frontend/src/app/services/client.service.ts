import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Client} from "../shared/client";

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private url = "http://localhost:8181/api/clients";

  constructor(private http: HttpClient) {
  }

  getAllClients(): Observable<any> {
    return this.http.get(this.url);
  }

  getClientById(clientId: number): Observable<Client> {
    return this.http.get<Client>(this.url + '/' + clientId);
  }

  editClient(client: Client): Observable<Client> {
    return this.http.put<Client>(this.url + '/' + client.clientId, client);
  }

  addClient(client: Client): Observable<Client> {
    return this.http.post<Client>(this.url, client);
  }

  deleteClient(clientId: number): Observable<any> {
    return this.http.delete(this.url + '/' + clientId);
  }

}
