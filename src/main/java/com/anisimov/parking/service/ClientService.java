package com.anisimov.parking.service;

import com.anisimov.parking.entity.Client;
import com.anisimov.parking.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getClientById(Integer id) {
        return clientRepository.findById(id).get();
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Integer saveClient(Client client) {
        return clientRepository.save(client).getId();
    }

    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }
}
