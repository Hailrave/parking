package com.anisimov.parking.controller;

import com.anisimov.parking.entity.Client;
import com.anisimov.parking.entity.Order;
import com.anisimov.parking.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/save")
    public String saveClient(@RequestBody Client client) {
        Integer id = clientService.saveClient(client);
        return "Client with " + id + " has been saved";
    }

    @GetMapping("/get-all")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/get/{id}")
    public Client getClientById(@PathVariable("id") Integer id) {
        return clientService.getClientById(id);
    }

    @GetMapping("/get-orders/{id}")
    public List<Order> getOrders(@PathVariable("id") Integer id) {
        Client client = clientService.getClientById(id);
        return client.getOrders();
    }

    @PutMapping("/update/{id}")
    public String updateClient(@PathVariable("id") Integer id,
                                               @RequestBody Client client) {
        Client clFrDb = clientService.getClientById(id);
        clFrDb.setName(client.getName());
        clFrDb.setAuto(client.getAuto());
        Integer ide = clientService.saveClient(clFrDb);
        return "Client with " + ide + " has been updated";
    }

    @DeleteMapping("delete/{id}")
    public String deleteClient(@PathVariable("id") Integer id) {
        clientService.deleteClient(id);
        return "Client with " + id + " has been deleted";
    }

}
