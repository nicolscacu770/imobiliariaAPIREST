package com.example.springapp.controllers;

import com.example.springapp.entities.Cliente;
import com.example.springapp.entities.Propiedad;
import com.example.springapp.responses.ResponseHandler;
import com.example.springapp.services.PropiedadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/propiedad")
public class PropiedadController {
    @Autowired
    private PropiedadService propiedadService;

    @GetMapping()
    public ResponseEntity<Object> findAll(){
        try{
            List<Propiedad> result = propiedadService.findAll();
            if(result != null){
                return ResponseHandler.generateResponse("Success",HttpStatus.OK,result);
            }else{
                return ResponseHandler.generateResponse("Not found",HttpStatus.NOT_FOUND,result);
            }
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById( @PathVariable Integer id ){
        try {
            Propiedad propiedad = propiedadService.findById( id );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK, propiedad);
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody PropiedadDTO propiedadDTO){
        try{
            Propiedad result = propiedadService.save(propiedadDTO.propiedad,  propiedadDTO.idClientes);

            return ResponseHandler.generateResponse("Succes",HttpStatus.CREATED, result);
        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Propiedad propiedadActualizar){
        try {
            Propiedad propiedadActualizada = propiedadService.update(id, propiedadActualizar);
            return ResponseHandler.generateResponse("Succes",HttpStatus.CREATED, propiedadActualizada);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PutMapping("/addClientes/{idPropiedad}")
    public ResponseEntity<Object> addClientes(@PathVariable Integer idPropiedad, @RequestBody List<Integer> clientesNuevos){
        try {
            List<Cliente> result = propiedadService.addClientesInteresados(idPropiedad, clientesNuevos);
            return ResponseHandler.generateResponse("Succes",HttpStatus.CREATED, result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try {
            Propiedad propiedad = propiedadService.findById(id);
            if(propiedad != null){
                propiedadService.delete(propiedad);

                return ResponseHandler.generateResponse("Succes", HttpStatus.ACCEPTED, propiedad);
            }
            return ResponseHandler.generateResponse("Succes Propiedad",HttpStatus.NOT_FOUND, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    public static class PropiedadDTO {

        private Propiedad propiedad;
        private Integer idTituloPropiedad;
        private List<Integer> idClientes;


        public Propiedad getPropiedad() {
            return propiedad;
        }
        public void setPropiedad(Propiedad propiedad) {
            this.propiedad = propiedad;
        }

        public Integer getIdTituloPropiedad() {
            return idTituloPropiedad;
        }
        public void setIdTituloPropiedad(Integer idTituloPropiedad) {
            this.idTituloPropiedad = idTituloPropiedad;
        }

        public List<Integer> getIdClientes() {
            return idClientes;
        }
        public void setIdClientes(List<Integer> idClientes) {
            this.idClientes = idClientes;
        }
    }
}
