package com.lera.assistant.services;


import com.lera.assistant.exceptions.ClientNotFoundException;
import com.lera.assistant.model.Client;
import com.lera.assistant.repositories.ClientRepository;
import com.lera.assistant.repositories.JobRepository;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImp implements ClientService {
    private ClientRepository clientRepository;
    private JobRepository jobRepository;
    private Logger logger = Logger.getLogger(ClientServiceImp.class);

    public ClientServiceImp(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> {
            String message = "Client with id " + clientId + " was not found";
            logger.warn(message);
            return new ClientNotFoundException(message);
        });
    }

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client editClient(Long clientId, Client clientToModify) throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> {
            String message = "Client with id " + clientId + " was not found";
            logger.warn(message);
            return new ClientNotFoundException(message);
        });
        client.setName(clientToModify.getName());
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }
}
