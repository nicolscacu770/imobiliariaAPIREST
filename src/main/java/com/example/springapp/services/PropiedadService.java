package com.example.springapp.services;

import com.example.springapp.entities.Cliente;
import com.example.springapp.entities.Propiedad;
import com.example.springapp.entities.Propietario;
import com.example.springapp.entities.TituloPropiedad;
import com.example.springapp.repositories.PropiedadRepository;
import com.example.springapp.repositories.TituloPropiedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropiedadService {
    @Autowired
    private PropiedadRepository propiedadRepository;
    //@Autowired
    //private TituloPropiedadService tituloPropiedadService;
    @Autowired
    private ClienteService clienteService;

    public List<Propiedad> findAll() { return propiedadRepository.findAll();}

    public Propiedad findById(Integer id) {
        Optional<Propiedad> optional = propiedadRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }


    public Propiedad save(Propiedad propiedad,  List<Integer> idClientes){
        /*if(idTituloPropiedad != null){
            TituloPropiedad tituloPropiedad = tituloPropiedadService.findById(idTituloPropiedad);
            if(tituloPropiedad != null){
                propiedad.setTitulo_propiedad(tituloPropiedad);
            }
        }*/

        List<Cliente> clientesInteresados = new ArrayList<>();


        if(idClientes != null){
            for(Integer id:idClientes){
                Cliente cliente = clienteService.findById(id);
                if(cliente != null){
                    clientesInteresados.add(cliente);
                }
            }
            propiedad.setClientes_interesados(clientesInteresados);
        }

        return propiedadRepository.save(propiedad);
    }

    public Propiedad update(Integer id, Propiedad propiedadActualizar){
        Propiedad propiedad = findById(id);
        if(propiedad != null){
            propiedad.setTipo_propiedad(propiedadActualizar.getTipo_propiedad());
            propiedad.setDireccion(propiedadActualizar.getDireccion());
            propiedad.setNum_habitaciones(propiedadActualizar.getNum_habitaciones());
            propiedad.setNum_baños(propiedadActualizar.getNum_baños());
            propiedad.setPrecio(propiedadActualizar.getPrecio());

            return propiedadRepository.save(propiedad);
        }
        return null;
    }


    public List<Cliente> addClientesInteresados(Integer id, List<Integer> idClientesNuevos){
        Propiedad propiedad = findById(id);
        if(propiedad != null){
            List<Cliente> listaClientes = propiedad.getClientes_interesados();

            for(Integer idC:idClientesNuevos){
                Cliente clienteNuevo = clienteService.findById(idC);
                if(clienteNuevo != null){
                    listaClientes.add(clienteNuevo);
                }
            }
            propiedad.setClientes_interesados(listaClientes);
            propiedadRepository.save(propiedad);
            return propiedad.getClientes_interesados();

        }
        return null;
    }

    public void delete( Propiedad propiedad ){
        propiedadRepository.delete(propiedad);
    }
}