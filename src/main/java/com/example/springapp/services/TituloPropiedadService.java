package com.example.springapp.services;

import com.example.springapp.entities.Propiedad;
import com.example.springapp.entities.Propietario;
import com.example.springapp.entities.TituloPropiedad;
import com.example.springapp.repositories.PropiedadRepository;
import com.example.springapp.repositories.TituloPropiedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TituloPropiedadService {
    @Autowired
    private TituloPropiedadRepository tituloPropiedadRepository;
    @Autowired
    private PropiedadService propiedadService;
    @Autowired
    private PropietarioService propietarioService;

    public List<TituloPropiedad> findAll() { return tituloPropiedadRepository.findAll();}

    public TituloPropiedad findById(Integer id) {
        Optional<TituloPropiedad> optional = tituloPropiedadRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }


    public TituloPropiedad save(TituloPropiedad tituloPropiedad){
        if(tituloPropiedad.getPropietario() != null && tituloPropiedad.getPropiedad() != null){
            Propietario propietario = propietarioService.findById(tituloPropiedad.getPropietario().getId());
            Propiedad propiedad = propiedadService.findById(tituloPropiedad.getPropiedad().getId());

            tituloPropiedad.setPropiedad(propiedad);
            tituloPropiedad.setPropietario(propietario);
        }
        //el tituloPropiedad se crea sin importar si tiene o no propiedad y propietario
        return tituloPropiedadRepository.save(tituloPropiedad);
    }

    public Propietario getPropietario(TituloPropiedad tituloPropiedad){
        return tituloPropiedad.getPropietario();
    }

    public void delete( TituloPropiedad tituloPropiedad){
        tituloPropiedadRepository.delete(tituloPropiedad);
    }
}