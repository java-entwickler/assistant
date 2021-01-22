package com.lera.assistant.services;

import com.lera.assistant.exceptions.ClientNotFoundException;
import com.lera.assistant.model.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();

    Client getClientById(Long clientId) throws ClientNotFoundException;

    Client addClient(Client client);

    Client editClient(Long clientId, Client clientToModify) throws ClientNotFoundException;

    void deleteClient(Long clientId);
}
