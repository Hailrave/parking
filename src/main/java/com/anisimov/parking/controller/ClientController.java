package com.anisimov.parking.controller;

import com.anisimov.parking.entity.Client;
import com.anisimov.parking.entity.Order;
import com.anisimov.parking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveClient(@RequestBody Client client) {
        Integer id = clientService.saveClient(client);
        return new ResponseEntity<String>("Client with " + id + " has been saved", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Integer id) {
        Client client = clientService.getClientById(id);
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @GetMapping("/getOrders/{id}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable("id") Integer id) {
        Client client = clientService.getClientById(id);
        List<Order> orderList = client.getOrders();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateClient(@PathVariable("id") Integer id,
                                               @RequestBody Client client) {
        Client clFrDb = clientService.getClientById(id);
        clFrDb.setName(client.getName());
        clFrDb.setAuto(client.getAuto());
        Integer ide = clientService.saveClient(clFrDb);
        return new ResponseEntity<String>("Client with " + ide + " has been updated", HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Integer id) {
        clientService.deleteClient(id);
        return new ResponseEntity<String>("Client with " + id + " has been deleted", HttpStatus.OK);
    }

}
