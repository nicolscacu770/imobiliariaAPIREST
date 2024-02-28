package com.example.springapp.controllers;

import com.example.springapp.entities.Cliente;
import com.example.springapp.entities.Propietario;
import com.example.springapp.entities.TituloPropiedad;
import com.example.springapp.responses.ResponseHandler;
import com.example.springapp.services.TituloPropiedadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("titulosPropiedades")
public class TituloPropiedadController {
    @Autowired
    private TituloPropiedadService tituloPropiedadService;

    @GetMapping()
    public ResponseEntity<Object> findAll(){
        try{
            List<TituloPropiedad> result = tituloPropiedadService.findAll();
            if(result != null){
                return ResponseHandler.generateResponse("Success", HttpStatus.OK,result);
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
            TituloPropiedad tituloPropiedad = tituloPropiedadService.findById( id );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK, tituloPropiedad);
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/propietario/{id}")
    public ResponseEntity<Object> findPropietario( @PathVariable Integer id ){
        try {
            Propietario propietario = tituloPropiedadService.getPropietario( tituloPropiedadService.findById(id) );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK, propietario);
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody TituloPropiedad tituloPropiedad){
        try{
            TituloPropiedad result = tituloPropiedadService.save(tituloPropiedad);

            return ResponseHandler.generateResponse("Succes",HttpStatus.CREATED, result);

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try {
            TituloPropiedad tituloPropiedad = tituloPropiedadService.findById(id);
            if(tituloPropiedad != null){
                tituloPropiedadService.delete(tituloPropiedad);

                return ResponseHandler.generateResponse("Succes", HttpStatus.ACCEPTED, tituloPropiedad);
            }
            return ResponseHandler.generateResponse("Succes Cliente",HttpStatus.NOT_FOUND, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
