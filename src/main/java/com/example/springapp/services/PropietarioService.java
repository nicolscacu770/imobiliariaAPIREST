package com.example.springapp.services;

import com.example.springapp.entities.Propietario;
import com.example.springapp.entities.TituloPropiedad;
import com.example.springapp.repositories.PropietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropietarioService {
    @Autowired
    private PropietarioRepository propietarioRepository;

    public List<Propietario> findAll() { return propietarioRepository.findAll();}

    public Propietario findById(Integer id) {
        Optional<Propietario> optional = propietarioRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }


    public Propietario save(Propietario propietario){

        return propietarioRepository.save(propietario);
    }

    public List<TituloPropiedad> getSales(Propietario propietario){
        return propietario.getTitulosPropiedades();
    }

    public void delete( Propietario propietario){
        propietarioRepository.delete(propietario);
    }
}