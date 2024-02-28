package com.example.springapp.services;

import com.example.springapp.entities.Cliente;
import com.example.springapp.entities.Propiedad;
import com.example.springapp.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() { return clienteRepository.findAll();}

    public Cliente findById(Integer id) {
        Optional<Cliente> optional = clienteRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }


    public Cliente save(Cliente cliente){

        return clienteRepository.save(cliente);
    }

    public List<Propiedad> getPropiedadesCliente(Cliente cliente){
        return cliente.getPropiedades();
    }

    public void delete( Cliente cliente ){
        clienteRepository.delete(cliente);
    }
}