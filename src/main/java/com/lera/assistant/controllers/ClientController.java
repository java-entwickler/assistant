package com.lera.assistant.controllers;

import com.lera.assistant.exceptions.ClientNotFoundException;
import com.lera.assistant.model.Client;
import com.lera.assistant.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/clients")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable Long clientId) {
        try {
            Client client = clientService.getClientById(clientId);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (ClientNotFoundException exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> editClient(@PathVariable Long clientId, @RequestBody Client client) {
        try {
            Client modifiedClient = clientService.editClient(clientId, client);
            return new ResponseEntity<>(modifiedClient, HttpStatus.OK);
        } catch (ClientNotFoundException exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long clientId) {
        clientService.deleteClient(clientId);
    }

}
